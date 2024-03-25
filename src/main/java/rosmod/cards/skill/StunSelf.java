package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.GiveBlockNextTurn;
import rosmod.util.CardStats;

public class StunSelf extends BaseCard {
    public static final String ID = makeID("StunSelf");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            -1
    );


    public StunSelf() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.energyOnUse * (this.upgraded ? 14 : 10)));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new GiveBlockNextTurn(abstractPlayer, (this.energyOnUse * (this.upgraded ? 14 : 10)) / 2)));
        if (!this.freeToPlayOnce)
            abstractPlayer.energy.use(this.energyOnUse);
        addToBot(new PressEndTurnButtonAction());
    }

}
