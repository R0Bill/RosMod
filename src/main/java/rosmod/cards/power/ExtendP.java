package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.attack.Extend;
import rosmod.character.Rosmontis;
import rosmod.powers.ExtendPower;
import rosmod.util.CardStats;

public class ExtendP extends BaseCard {
    public static final String ID = makeID("ExtendP");

    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.POWER,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public ExtendP() {
        super(ID, info);
        setInnate(false, true);
        setCostUpgrade(0);
        setMagic(1, 1);
        this.cardsToPreview = new Extend();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ExtendPower(abstractPlayer, this.magicNumber)));
        addToBot(new MakeTempCardInHandAction(new Extend(), this.magicNumber));
    }
}
