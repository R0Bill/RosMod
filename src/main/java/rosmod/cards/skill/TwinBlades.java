package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AssaultBlade;
import rosmod.orbs.BarrierBlade;
import rosmod.util.CardStats;

public class TwinBlades extends BaseCard {

    public static final String ID = makeID("TwinBlades");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.SELF,
            2
    );

    public TwinBlades() {
        super(ID, info);
        setCostUpgrade(1);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        while (p.maxOrbs < 2)
            p.increaseMaxOrbSlots(1, true);
        addToBot(new ChannelAction(new BarrierBlade()));
        addToBot(new ChannelAction(new AssaultBlade()));
    }
}
