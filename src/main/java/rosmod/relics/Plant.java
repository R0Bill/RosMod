package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class Plant extends BaseRelic {

    private static final String NAME = "Plant";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.SHOP;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.CLINK;

    public Plant() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atTurnStart() {
        flash();
        addToBot(new GainBlockAction(AbstractDungeon.player, null, 8));
    }
}
