package rosmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class EmergencyPower extends BasePower {
    public static final String POWER_ID = makeID("EmergencyPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private boolean ENABLE;
    private boolean CHARGE;
    private boolean ONCHARGE = false;
    private int RECYCLE;

    private final Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F);
    private final Color redColor = new Color(1.0F, 0.0F, 0.0F, 1.0F);
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public EmergencyPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
        this.ENABLE = true;
        this.CHARGE = true;
        this.RECYCLE = 1;
        this.amount++;
    }

    public int onLoseHp(int damageAmount) {
        if (this.ENABLE) {
            this.ENABLE = false;
            this.ONCHARGE = true;
            this.amount++;
            return 0;
        }
        return damageAmount;
    }

    public void atStartOfTurn() {
        if (this.ONCHARGE) {
            this.ONCHARGE = false;
        } else if (!this.ENABLE && this.CHARGE) {
            this.ENABLE = true;
            RECYCLE++;
        }
        if (RECYCLE >= 3)
            this.CHARGE = false;
        this.amount++;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (3 - this.RECYCLE) + DESCRIPTIONS[1] + (this.ENABLE ? DESCRIPTIONS[3] : (this.CHARGE ? DESCRIPTIONS[2] : DESCRIPTIONS[4]));
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        updateDescription();
        AbstractPlayer p = AbstractDungeon.player;
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, this.RECYCLE + "/3", x, y, this.fontScale, c);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, "SHIELD", p.drawX - 20F * Settings.scale, p.drawY + 317.0F * Settings.scale, this.fontScale, c);
        if (this.ENABLE)
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, "ACTIVATED", p.drawX, p.drawY + 300 * Settings.scale, this.fontScale, this.greenColor);
        else if (this.CHARGE)
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, "CHARGING", p.drawX, p.drawY + 300 * Settings.scale, this.fontScale, Settings.GOLD_COLOR);
        else
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, "DISABLED", p.drawX, p.drawY + 300 * Settings.scale, this.fontScale, this.redColor);
    }

}
