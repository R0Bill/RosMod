package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rosmod.cards.attack.Extend;

import static rosmod.BasicMod.makeID;

public class ExtendPower extends BasePower {

    public static final String POWER_ID = makeID("ExtendPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public void atStartOfTurn() {
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(new Extend(), this.amount));
    }

    public ExtendPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
