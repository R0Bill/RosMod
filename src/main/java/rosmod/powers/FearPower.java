package rosmod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class FearPower extends BasePower {
    public static final String POWER_ID = makeID("FearPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public FearPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return info.type == DamageInfo.DamageType.NORMAL ? damageAmount + this.amount : damageAmount;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage - (float) this.amount : damage;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer)
            this.amount -= Math.floor((float) this.amount / 2);
    }

}
