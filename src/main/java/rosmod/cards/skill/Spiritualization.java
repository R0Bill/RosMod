package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Spiritualization extends BaseCard {
    public static final String ID = makeID("Spiritualization");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public Spiritualization() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(0);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
        for (AbstractCard card : abstractPlayer.hand.group) {
            if (card.type == CardType.STATUS || card.type == CardType.CURSE) {
                addToBot(new ExhaustSpecificCardAction(card, abstractPlayer.hand));
                addToBot(new GainEnergyAction(1));
                addToBot(new DrawCardAction(abstractPlayer, 1));
            }
        }
    }

}
