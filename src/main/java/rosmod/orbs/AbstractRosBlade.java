package rosmod.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.relics.BaseRelic;
import rosmod.relics.SwordTassel;

import static rosmod.util.GeneralUtils.removePrefix;

/**
 * 悬浮剑基类：以充能球形式悬浮的源石剑。
 * 被动（每回合开始触发）与激发两段效果。
 */
public abstract class AbstractRosBlade extends CustomOrb {

    public AbstractRosBlade(String id, String name, int passive, int evoke) {
        super(id, name, passive, evoke,
                CardCrawlGame.languagePack.getOrbString(id).DESCRIPTION[0],
                CardCrawlGame.languagePack.getOrbString(id).DESCRIPTION[1],
                "rosmod/images/orbs/" + removePrefix(id) + ".png");
    }

    // 悬浮剑不是充能球：不吃 集中（Focus）
    @Override
    public void applyFocus() {
        this.passiveAmount = this.basePassiveAmount;
        this.evokeAmount = this.baseEvokeAmount;
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = this.passiveDescription + " NL " + this.evokeDescription;
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_FROST_CHANNEL", 0.1F);
        // 通知持有者的遗物（AnnE 荆棘、未来扩展）
        if (AbstractDungeon.player != null) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof BaseRelic)
                    ((BaseRelic) r).onSwordChanneled(this);
            }
        }
    }

    /** 被动效果本体（每回合开始触发；持有「剑穗」时激发也会触发）。 */
    public abstract void triggerPassiveEffect();

    /** 激发效果本体（onEvoke 与「剑雨」/「暴走」共用）。 */
    public abstract void triggerEvokeEffect();

    /** 激发：效果 + 剑穗联动（若持有剑穗，同时触发被动）。 */
    public final void evoke() {
        triggerEvokeEffect();
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(SwordTassel.ID))
            triggerPassiveEffect();
    }

    @Override
    public void onStartOfTurn() {
        triggerPassiveEffect();
    }

    @Override
    public void onEvoke() {
        evoke();
    }

    /** 御剑前确保至少有一个剑槽（充能球槽）。 */
    public static void ensureSlot() {
        if (AbstractDungeon.player.maxOrbs <= 0)
            AbstractDungeon.player.increaseMaxOrbSlots(1, true);
    }

    /** 当前悬浮的剑数量。 */
    public static int countBlades() {
        if (AbstractDungeon.player == null)
            return 0;
        int n = 0;
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof AbstractRosBlade)
                n++;
        }
        return n;
    }
}
