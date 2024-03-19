package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Accumulation extends BaseCard {
    public static final String ID = makeID("Accumulation");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.SELF,
            2
    );

    public Accumulation(int upgrades) {
        super(ID, info);
        this.baseMagicNumber = 3;
        this.timesUpgraded = upgrades;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber));

    }


    public void upgrade() {
        upgradeMagicNumber(1);
        this.timesUpgraded++;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    public boolean canUpgrade() {
        return true;
    }

    public Accumulation() {
        this(0);
    }
}
