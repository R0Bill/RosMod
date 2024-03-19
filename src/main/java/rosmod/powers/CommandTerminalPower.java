package rosmod.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class CommandTerminalPower extends BasePower {
    public static final String POWER_ID = makeID("CommandTerminalPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public CommandTerminalPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.player.gainEnergy(1);
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
    }
}
