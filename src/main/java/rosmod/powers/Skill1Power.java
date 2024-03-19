package rosmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;


public class Skill1Power extends BasePower {
    public static final String POWER_ID = makeID("Skill1Power");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    private static int magic = 0;
    private final Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F);
    private final Color redColor = new Color(1.0F, 0.0F, 0.0F, 1.0F);

    public Skill1Power(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        magic = 0;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard abstractCard, UseCardAction action) {
        if (!abstractCard.purgeOnUse && abstractCard.type == AbstractCard.CardType.ATTACK && !abstractCard.cardID.equals("rosmontis.TouchingStars") && !abstractCard.cardID.equals("rosmontis:ForgetMeNot")) {
            magic++;
            if (magic == amount && amount == 2) {
                magic = 0;
                flash();
                AbstractMonster m = null;
                if (action.target != null)
                    m = (AbstractMonster) action.target;
                AbstractDungeon.player.limbo.addToBottom(abstractCard);
                abstractCard.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                abstractCard.target_y = Settings.HEIGHT / 2.0F;
                if (m != null)
                    abstractCard.calculateCardDamage(m);
                abstractCard.purgeOnUse = true;
                if (m != null && m.isDead) m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(abstractCard, m, 0, true, true), true);
            } else if (amount == 3 && magic == 3) {
                magic = 0;
                flash();
                AbstractMonster m = null;
                if (action.target != null)
                    m = (AbstractMonster) action.target;
                AbstractDungeon.player.limbo.addToBottom(abstractCard);
                abstractCard.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                abstractCard.target_y = Settings.HEIGHT / 2.0F;
                if (m != null)
                    abstractCard.calculateCardDamage(m);
                abstractCard.purgeOnUse = true;
                m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(abstractCard, m, 0, true, true), true);
            }
        }
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        if (this.amount > 0) {
            if (!this.isTurnBased) {
                this.greenColor.a = c.a;
                c = this.greenColor;
            }
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, magic + "/" + this.amount, x, y, this.fontScale, c);
        }
    }


    @Override
    public void atStartOfTurn() {
        magic = 0;
    }

}
