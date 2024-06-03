package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class BombPower extends BasePower {
    public static final String POWER_ID = makeID("BombPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private boolean once = true;

    public BombPower(AbstractCreature owner, int amount, boolean tb) {
        super(POWER_ID, TYPE, true, owner, amount);
        once = tb;
    }

    public void trigger() {
        addToBot(new DamageAction(this.owner, new DamageInfo(null, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SHIELD));
        if (this.once)
            this.amount = 0;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.once ? DESCRIPTIONS[3] : DESCRIPTIONS[4]);
    }
}
