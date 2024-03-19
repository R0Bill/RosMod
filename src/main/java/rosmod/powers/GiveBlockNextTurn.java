package rosmod.powers;

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

    public void atStartOfTurn() {
        addToBot(new GainBlockAction(AbstractDungeon.player, null, amount));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "rosmontis:GiveBlockNextTurn"));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

}
