package rosmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import rosmod.orbs.AbstractRosBlade;

import static rosmod.BasicMod.makeID;

/** 剑匣：获得剑槽；每次御剑获得格挡。描述中实时列出当前悬浮剑。 */
public class SwordArsenalPower extends BasePower {

    public static final String ID = makeID("SwordArsenalPower");
    private static final int BLOCK_ON_CHANNEL = 3;

    public SwordArsenalPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
        // 获得剑槽（充能球槽复用）
        AbstractDungeon.player.increaseMaxOrbSlots(amount, true);
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (orb instanceof AbstractRosBlade) {
            flash();
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_ON_CHANNEL));
        }
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
        if (AbstractDungeon.player != null) {
            boolean first = true;
            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof AbstractRosBlade) {
                    this.description += (first ? DESCRIPTIONS[1] : DESCRIPTIONS[2]) + orb.name;
                    first = false;
                }
            }
        }
    }
}
