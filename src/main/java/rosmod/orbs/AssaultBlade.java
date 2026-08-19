package rosmod.orbs;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import static rosmod.BasicMod.makeID;

/** 突剑：被动回合随机敌伤害，激发大额随机敌伤害。 */
public class AssaultBlade extends AbstractRosBlade {

    public static final String ID = makeID("AssaultBlade");

    public AssaultBlade() {
        super(ID, CardCrawlGame.languagePack.getOrbString(ID).NAME, 3, 9);
    }

    @Override
    public void triggerPassiveEffect() {
        dealToRandom(this.passiveAmount);
    }

    @Override
    public void triggerEvokeEffect() {
        dealToRandom(this.evokeAmount);
    }

    private void dealToRandom(int dmg) {
        AbstractMonster mo = AbstractDungeon.getRandomMonster();
        if (mo != null) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,
                    new DamageInfo(AbstractDungeon.player, dmg, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new AssaultBlade();
    }
}
