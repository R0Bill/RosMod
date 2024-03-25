package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.other.Infected;
import rosmod.character.Rosmontis;
import rosmod.powers.MilliaPower;
import rosmod.util.CardStats;

public class MilliaDisaster extends BaseCard {
    public static final String ID = makeID("MilliaDisaster");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.POWER,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.SELF,
            2
    );

    public MilliaDisaster() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(1);
        this.cardsToPreview = new Infected();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new MilliaPower(abstractPlayer, 2)));
        addToBot(new MakeTempCardInDrawPileAction(new Infected(), 1, true, false, false));
    }
}
