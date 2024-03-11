package rosmod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;


public class Skill2Power extends BasePower {
    public static final String POWER_ID = makeID("Skill2Power");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public Skill2Power(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }
    @Override
    public float atDamageGive(float d, DamageInfo.DamageType type){
        float temp = (float) AbstractDungeon.player.currentBlock / 3;
        if(type == DamageInfo.DamageType.NORMAL){
            flash();
            temp += d;
            return temp;
        }
        else
            return d;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + AbstractDungeon.player.currentBlock / 3 + DESCRIPTIONS[1];
    }

    @Override
    public float modifyBlockLast(float blockAmount) {
        flash();
        return (float) (blockAmount * 1.5);
    }

    public void onGainedBlock(float blockAmount) {
        updateDescription();
    }
}
