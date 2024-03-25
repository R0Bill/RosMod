package rosmod.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class AncientCoin extends BaseRelic {
    private static final String NAME = "AncientCoin";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public AncientCoin() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public void onEquip() {
        CardCrawlGame.sound.play("GOLD_GAIN");
        AbstractDungeon.player.gainGold(130);
    }

    public boolean canSpawn() {
        return ((Settings.isEndless || AbstractDungeon.floorNum <= 48) &&
                !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom));
    }

}
