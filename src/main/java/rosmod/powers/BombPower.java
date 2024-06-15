package rosmod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class BombPower extends BasePower {
    public static final String POWER_ID = makeID("BombPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private boolean once;

    public BombPower(AbstractCreature owner, int amount, boolean tb) {
        super(POWER_ID, TYPE, true, owner, amount);
        once = tb;
    }

    public void trigger() {
        if (this.once && this.amount == 1)
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
        else if (this.once)
            this.amount--;
    }

    public boolean IsOnce() {
        return once;
    }

    public void UPGAmount(int v) {
        this.amount += v;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (once) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            if (this.amount == 1) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            } else {
                this.amount--;
            }
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.once ? DESCRIPTIONS[1] : DESCRIPTIONS[4]) + this.amount + (this.once ? DESCRIPTIONS[2] : DESCRIPTIONS[3]);
    }
}
