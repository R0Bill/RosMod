package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.GiveBlockNextTurn;
import rosmod.util.CardStats;

public class GetReady extends BaseCard {
    public static final String ID = makeID("GetReady");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.RARE,
            AbstractCard.CardTarget.SELF,
            3
    );

    public GetReady() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new GiveBlockNextTurn(abstractPlayer, (!this.upgraded ? 10 : 20))));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EnergizedPower(abstractPlayer, (!this.upgraded ? 2 : 3))));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, (!this.upgraded ? 2 : 3))));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DrawCardNextTurnPower(abstractPlayer, (!this.upgraded ? 2 : 3))));
    }

}
