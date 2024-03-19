package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Fitting extends BaseCard {
    public static final String ID = makeID("Fitting");

    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );


    public Fitting() {
        super(ID, info);
        setCostUpgrade(1);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster != null && abstractMonster.getIntentBaseDmg() >= 0)
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1)));
        else
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 1)));

    }
}
