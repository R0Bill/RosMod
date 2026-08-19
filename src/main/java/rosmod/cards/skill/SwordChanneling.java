package rosmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.TriggerPassiveAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AbstractRosBlade;
import rosmod.orbs.AssaultBlade;
import rosmod.util.CardStats;

public class SwordChanneling extends BaseCard {

    public static final String ID = makeID("SwordChanneling");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public SwordChanneling() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRosBlade.ensureSlot();
        AssaultBlade blade = new AssaultBlade();
        addToBot(new ChannelAction(blade));
        if (this.upgraded) {
            addToBot(new TriggerPassiveAction(blade));
        }
    }
}
