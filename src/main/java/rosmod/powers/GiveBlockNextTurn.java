package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class GiveBlockNextTurn extends BasePower {


    public static final String POWER_ID = makeID("GiveBlockNextTurn");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GiveBlockNextTurn(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new GainBlockAction(AbstractDungeon.player, null, amount));
            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "GiveBlockNextTurn"));
        }
    }

}
