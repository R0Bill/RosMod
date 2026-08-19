package rosmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static rosmod.BasicMod.makeID;

/** 念旧：每当你打出 消耗 牌，获得格挡。 */
public class CherishPower extends BasePower {

    public static final String ID = makeID("CherishPower");

    public CherishPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.exhaust) {
            flash();
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
