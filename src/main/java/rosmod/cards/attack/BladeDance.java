package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AbstractRosBlade;
import rosmod.util.CardStats;

public class BladeDance extends BaseCard {

    public static final String ID = makeID("BladeDance");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int PER_BLADE = 3;
    private static final int UPG_PER_BLADE = 1;

    public BladeDance() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        // 总伤害 = 基础 + 每把剑加成，实时显示
        setCustomVar("BladeDmg", VariableType.DAMAGE, DAMAGE, UPG_DAMAGE,
                (m, baseVal) -> baseVal + AbstractRosBlade.countBlades() * (this.upgraded ? PER_BLADE + UPG_PER_BLADE : PER_BLADE));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg = (this.upgraded ? DAMAGE + UPG_DAMAGE : DAMAGE)
                + AbstractRosBlade.countBlades() * (this.upgraded ? PER_BLADE + UPG_PER_BLADE : PER_BLADE);
        addToBot(new DamageAction(m, new DamageInfo(p, dmg, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }
}
