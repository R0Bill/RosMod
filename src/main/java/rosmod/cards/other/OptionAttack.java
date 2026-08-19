package rosmod.cards.other;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AbstractRosBlade;
import rosmod.orbs.AssaultBlade;
import rosmod.util.CardStats;

public class OptionAttack extends BaseCard {

    public static final String ID = makeID("OptionAttack");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            AbstractCard.CardRarity.SPECIAL,
            AbstractCard.CardTarget.NONE,
            -2
    );

    public OptionAttack() {
        super(ID, info);
    }

    public void onChoseThisOption() {
        AbstractRosBlade.ensureSlot();
        addToBot(new ChannelAction(new AssaultBlade()));
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
