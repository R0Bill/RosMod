package rosmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.actions.EvokeAllSwordsAction;
import rosmod.relics.BaseRelic;

import static rosmod.BasicMod.makeID;

/** 失控：打出攻击 +1、技能 -1、消耗牌额外 -1；达到阈值触发暴走。 */
public class InstabilityPower extends BasePower {

    public static final String ID = makeID("InstabilityPower");
    public static final int RAMPAGE_THRESHOLD = 10;

    public InstabilityPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.amount++;
        } else if (card.type == AbstractCard.CardType.SKILL) {
            this.amount = Math.max(0, this.amount - 1);
        }
        if (card.exhaust) {
            this.amount = Math.max(0, this.amount - 1);
        }
        checkThreshold();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        checkThreshold();
    }

    private void checkThreshold() {
        if (this.amount >= RAMPAGE_THRESHOLD) {
            this.amount = 0;
            flash();
            addToTop(new EvokeAllSwordsAction(false));
            addToTop(new ApplyPowerAction(this.owner, this.owner, new RampagePower(this.owner, 2), 2));
            addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 3), 3));
            // 通知持有者的遗物（暴走增幅器等）
            if (AbstractDungeon.player != null) {
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    if (r instanceof BaseRelic)
                        ((BaseRelic) r).onRampageTriggered();
                }
            }
        }
        updateDescription();
    }

    /** 当前失控值。 */
    public static int current() {
        if (AbstractDungeon.player == null)
            return 0;
        InstabilityPower inst = (InstabilityPower) AbstractDungeon.player.getPower(ID);
        return inst != null ? inst.amount : 0;
    }

    /** 「失控 N」词条统一入口：消耗 N 点失控，成功后通知相关能力。 */
    public static boolean tryConsume(int n) {
        if (AbstractDungeon.player == null)
            return false;
        InstabilityPower inst = (InstabilityPower) AbstractDungeon.player.getPower(ID);
        if (inst == null || inst.amount < n)
            return false;
        inst.amount -= n;
        inst.flash();
        inst.updateDescription();
        for (AbstractPower pow : AbstractDungeon.player.powers) {
            if (pow instanceof CatharsisPower)
                ((CatharsisPower) pow).onInstabilityConsumed(n);
        }
        return true;
    }

    /** 卡牌主动改变失控值（正值堆叠、负值消减，不低于 0）。 */
    public static void shift(int amount) {
        if (amount == 0 || AbstractDungeon.player == null)
            return;
        InstabilityPower inst = (InstabilityPower) AbstractDungeon.player.getPower(ID);
        if (inst == null) {
            if (amount > 0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                        AbstractDungeon.player, AbstractDungeon.player,
                        new InstabilityPower(AbstractDungeon.player, amount), amount));
            }
            return;
        }
        if (amount > 0) {
            inst.stackPower(amount);
        } else {
            inst.amount = Math.max(0, inst.amount + amount);
            inst.updateDescription();
        }
        inst.flash();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount, RAMPAGE_THRESHOLD);
    }
}
