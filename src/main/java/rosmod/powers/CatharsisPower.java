package rosmod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static rosmod.BasicMod.makeID;

/** 宣泄：每当你消耗 失控 时，抽1张牌。 */
public class CatharsisPower extends BasePower {

    public static final String ID = makeID("CatharsisPower");

    public CatharsisPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    /** 由 InstabilityPower.tryConsume 在消耗失控时调用。 */
    public void onInstabilityConsumed(int n) {
        flash();
        addToBot(new DrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
