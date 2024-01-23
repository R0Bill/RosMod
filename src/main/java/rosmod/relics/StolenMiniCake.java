package rosmod.relics;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.character.MyCharacter;

import static rosmod.BasicMod.makeID;

public class StolenMiniCake extends BaseRelic{
    private static final String NAME = "StolenMiniCake"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public StolenMiniCake() {
        super(ID, NAME, MyCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip(){
            AbstractDungeon.player.energy.energyMaster++;
    }
}
