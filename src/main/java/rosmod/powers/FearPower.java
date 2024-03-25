package rosmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rosmod.relics.DarkMask;

import static rosmod.BasicMod.makeID;

public class FearPower extends BasePower {
    public static final String POWER_ID = makeID("FearPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public FearPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        updateDescription();
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return info.type == DamageInfo.DamageType.NORMAL ? damageAmount + this.amount : damageAmount;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage - (float) this.amount : damage;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.player.hasRelic("rosmontis:DarkMask")) {
            if (!isPlayer) {
                if (this.amount > 1)
                    this.amount -= (int) Math.floor((float) this.amount / 2);
                else
                    this.amount = 0;
            }
            updateDescription();
            if (this.amount <= 0)
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            DarkMask d = (DarkMask) AbstractDungeon.player.getRelic("rosmontis:DarkMask");
            d.trigger(this.owner);
        }
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, Color.valueOf("cd2626"));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + (AbstractDungeon.player.hasRelic("rosmontis:DarkMask") ? DESCRIPTIONS[3] : DESCRIPTIONS[2]);
    }

}
