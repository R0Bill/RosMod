package rosmod.relics;

import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class BadGuy extends BaseRelic {

    private static final String NAME = "BadGuy"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    private boolean CANUSE = true;

    public BadGuy() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public int onLoseHpLast(int damageAmount) {
        if (damageAmount >= 10 && CANUSE) {
            flash();
            this.CANUSE = false;
            this.isDone = true;
            return 0;
        }
        return damageAmount;
    }

    @Override
    public void onVictory() {
        this.CANUSE = true;
    }
}
