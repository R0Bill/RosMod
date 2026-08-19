package rosmod.cards.other;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AbstractRosBlade;
import rosmod.orbs.BarrierBlade;
import rosmod.util.CardStats;

public class OptionDefend extends BaseCard {

    public static final String ID = makeID("OptionDefend");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.SPECIAL,
            AbstractCard.CardTarget.NONE,
            -2
    );

    public OptionDefend() {
        super(ID, info);
    }

    public void onChoseThisOption() {
        AbstractPlayer player = AbstractDungeon.player;
        AbstractRosBlade.ensureSlot();
        addToBot(new ChannelAction(new BarrierBlade()));
        addToBot(new GainBlockAction(player, player, 4));
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
