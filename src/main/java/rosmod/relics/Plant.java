package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class Plant extends BaseRelic {//回合开始时基于5点护盾

    private static final String NAME = "Plant"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final AbstractRelic.RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.CLINK; //The sound played when the relic is clicked.

    public Plant() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atTurnStart() {
        flash();
        addToBot(new GainBlockAction(AbstractDungeon.player, null, 8));
    }
}
