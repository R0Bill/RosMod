package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AbstractRosBlade;
import rosmod.orbs.BarrierBlade;
import rosmod.util.CardStats;

public class SwordSheath extends BaseCard {

    public static final String ID = makeID("SwordSheath");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public SwordSheath() {
        super(ID, info);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRosBlade.ensureSlot();
        p.increaseMaxOrbSlots(1, true);
        addToBot(new ChannelAction(new BarrierBlade()));
    }
}
