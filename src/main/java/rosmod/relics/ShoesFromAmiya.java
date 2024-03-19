package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class ShoesFromAmiya extends BaseRelic{
    private static final String NAME = "ShoesFromAmiya"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public ShoesFromAmiya() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public boolean canSpawn() {
        return (Settings.isEndless || AbstractDungeon.floorNum <= 52);
    }

    @Override
    public void atBattleStart(){
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 3), 3));
    }
}
