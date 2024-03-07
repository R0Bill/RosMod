package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class MilliaPower extends BasePower {

    public static final String POWER_ID = makeID("MilliaPower");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MilliaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.SKILL) {
            boolean used = false;
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                boolean temp = true;
                for (AbstractPower pow : mo.powers) {
                    if (pow.ID.equals("FearPower")) {
                        temp = false;
                    }
                }
                if (temp && !used) {
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new FearPower((AbstractCreature) mo, this.amount)));
                    break;
                }
            }
            if (!used) {
                AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new FearPower((AbstractCreature) mo, this.amount)));
            }
        }
    }
}
