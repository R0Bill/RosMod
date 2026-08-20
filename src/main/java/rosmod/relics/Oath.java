package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class Oath extends BaseRelic {
    private static final String NAME = "Oath";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public Oath() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            usedUp();
            this.counter = -2;
        }
    }

    public void onTrigger() {
        if (this.usedUp)
            return;
        flash();
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        // The killing blow was already subtracted, so HP can be negative here.
        // heal() has no lower clamp; clamp to 0 first so the revive can't leave us below 0.
        if (AbstractDungeon.player.currentHealth < 0)
            AbstractDungeon.player.currentHealth = 0;
        int healAmt = AbstractDungeon.player.maxHealth / 2;
        if (healAmt < 30)
            healAmt = 30;
        AbstractDungeon.player.heal(healAmt, true);
        AbstractDungeon.player.addBlock(300);
        setCounter(-2);
    }

}
