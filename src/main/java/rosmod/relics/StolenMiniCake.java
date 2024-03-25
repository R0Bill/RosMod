package rosmod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class StolenMiniCake extends BaseRelic{
    private static final String NAME = "StolenMiniCake";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SPECIAL;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public StolenMiniCake() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip(){
            AbstractDungeon.player.energy.energyMaster++;
    }
}
