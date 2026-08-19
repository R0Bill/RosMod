package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static rosmod.BasicMod.makeID;

/** 暴走：无法获得格挡；每回合结束受到 2 点真实伤害；结束后 +3 失控。 */
public class RampagePower extends BasePower {

    public static final String ID = makeID("RampagePower");

    public RampagePower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public float modifyBlock(float blockAmount) {
        return 0.0F;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 2, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public void onRemove() {
        addToBot(new ApplyPowerAction(this.owner, this.owner, new InstabilityPower(this.owner, 3), 3));
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
