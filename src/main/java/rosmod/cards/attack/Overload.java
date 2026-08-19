package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.InstabilityPower;
import rosmod.util.CardStats;

public class Overload extends BaseCard {

    public static final String ID = makeID("Overload");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;

    public Overload() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        // 总伤害 = 基础 + 当前失控值，实时显示
        setCustomVar("OverloadDmg", VariableType.DAMAGE, DAMAGE, UPG_DAMAGE,
                (m, baseVal) -> baseVal + InstabilityPower.current());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg = (this.upgraded ? DAMAGE + UPG_DAMAGE : DAMAGE) + InstabilityPower.current();
        addToBot(new DamageAction(m, new DamageInfo(p, dmg, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }
}
