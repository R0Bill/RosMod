package rosmod.orbs;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static rosmod.BasicMod.makeID;

/** 棘剑：被动回合荆棘，激发全体真实伤害。 */
public class ThornBlade extends AbstractRosBlade {

    public static final String ID = makeID("ThornBlade");

    public ThornBlade() {
        super(ID, CardCrawlGame.languagePack.getOrbString(ID).NAME, 1, 5);
    }

    @Override
    public void triggerPassiveEffect() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player,
                new ThornsPower(AbstractDungeon.player, this.passiveAmount), this.passiveAmount));
    }

    @Override
    public void triggerEvokeEffect() {
        // 真实伤害：全体 5 点（不受格挡与力量影响）
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                DamageInfo.createDamageMatrix(this.evokeAmount, true),
                DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new ThornBlade();
    }
}
