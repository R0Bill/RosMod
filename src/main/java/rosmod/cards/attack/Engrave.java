package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

/**
 * 铭刻：回忆——消耗堆每有 3 张牌，本卡伤害跨战斗永久 +1。
 * 永久增量存于 misc（存档序列化），applyPowers 时重建 damage。
 */
public class Engrave extends BaseCard {

    public static final String ID = makeID("Engrave");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.ENEMY,
            2
    );

    private static final int BASE_DAMAGE = 9;
    private static final int UPG_BASE_DAMAGE = 13;
    private static final int RECALL_DIVISOR = 3;

    public Engrave() {
        super(ID, info);
        setExhaust(true);
        this.misc = 0;
        this.baseDamage = this.damage = BASE_DAMAGE;
    }

    @Override
    public void applyPowers() {
        this.baseDamage = this.damage = (this.upgraded ? UPG_BASE_DAMAGE : BASE_DAMAGE) + this.misc;
        super.applyPowers();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgraded = true;
            this.baseDamage = this.damage = UPG_BASE_DAMAGE + this.misc;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        int gain = AbstractDungeon.player.exhaustPile.size() / RECALL_DIVISOR;
        if (gain > 0) {
            this.misc += gain;
            this.baseDamage = this.damage = (this.upgraded ? UPG_BASE_DAMAGE : BASE_DAMAGE) + this.misc;
            // 同步主牌组中的同源实例（uuid 一致），保证跨战斗/读档持久
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c.uuid.equals(this.uuid)) {
                    c.misc = this.misc;
                    c.baseDamage = this.baseDamage;
                    break;
                }
            }
        }
    }
}
