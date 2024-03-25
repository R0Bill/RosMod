package rosmod.relics;

import com.megacrit.cardcrawl.core.AbstractCreature;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class DarkMask extends BaseRelic {


    private static final String NAME = "DarkMask";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public DarkMask() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public void trigger(AbstractCreature creature) {
        flash();
    }
}
