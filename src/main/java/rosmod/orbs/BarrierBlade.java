package rosmod.orbs;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import static rosmod.BasicMod.makeID;

/** 壁剑：被动回合格挡，激发大额格挡。 */
public class BarrierBlade extends AbstractRosBlade {

    public static final String ID = makeID("BarrierBlade");

    public BarrierBlade() {
        super(ID, CardCrawlGame.languagePack.getOrbString(ID).NAME, 3, 7);
    }

    @Override
    public void triggerPassiveEffect() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.passiveAmount));
    }

    @Override
    public void triggerEvokeEffect() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.evokeAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new BarrierBlade();
    }
}
