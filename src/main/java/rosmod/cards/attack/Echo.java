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

public class Echo extends BaseCard {

    public static final String ID = makeID("Echo");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.ENEMY,
            2
    );

    private static final int FLOOR = 5;
    private static final int UPG_FLOOR = 3;

    public Echo() {
        super(ID, info);
        setExhaust(true);
        // 总伤害 = 保底 + 消耗堆牌数，实时显示
        setCustomVar("EchoDmg", VariableType.DAMAGE, FLOOR, UPG_FLOOR,
                (m, baseVal) -> baseVal + (AbstractDungeon.player != null ? AbstractDungeon.player.exhaustPile.size() : 0));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg = (this.upgraded ? FLOOR + UPG_FLOOR : FLOOR) + p.exhaustPile.size();
        addToBot(new DamageAction(m, new DamageInfo(p, dmg, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }
}
