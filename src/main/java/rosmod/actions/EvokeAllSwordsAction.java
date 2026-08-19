package rosmod.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import rosmod.orbs.AbstractRosBlade;

import java.util.ArrayList;

/** 激发所有悬浮剑。剑雨（remove=true）与暴走（remove=false）共用。 */
public class EvokeAllSwordsAction extends AbstractGameAction {

    private final boolean remove;

    /** @param remove true=激发并移出剑槽；false=仅触发激发效果、保留剑 */
    public EvokeAllSwordsAction(boolean remove) {
        this.remove = remove;
    }

    @Override
    public void update() {
        ArrayList<AbstractOrb> swords = new ArrayList<>();
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof AbstractRosBlade)
                swords.add(orb);
        }
        for (AbstractOrb sword : swords) {
            if (remove) {
                addToTop(new EvokeSpecificOrbAction(sword));
            } else {
                // 走 evoke()：暴走路径同样享受剑穗联动
                ((AbstractRosBlade) sword).evoke();
            }
        }
        this.isDone = true;
    }
}
