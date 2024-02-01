package rosmod.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class AncientCoin extends BaseRelic {
    private static final String NAME = "AncientCoin"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public AncientCoin() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public void onEquip() {
        CardCrawlGame.sound.play("GOLD_GAIN");
        AbstractDungeon.player.gainGold(150);
    }

    public boolean canSpawn() {
        return ((Settings.isEndless || AbstractDungeon.floorNum <= 48) &&
                !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom));
    }

}
