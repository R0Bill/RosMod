package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.FearPower;
import rosmod.util.CardStats;

public class React extends BaseCard {
    public static final String ID = makeID("React");

    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );


    public React() {
        super(ID, info);
        setDamage(10, 5);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster != null && abstractMonster.getIntentBaseDmg() >= 0)
            addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new FearPower(abstractMonster, this.upgraded ? 3 : 2)));
        else
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));

    }
}
