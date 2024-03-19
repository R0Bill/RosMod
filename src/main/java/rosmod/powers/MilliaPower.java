package rosmod.powers;

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
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        boolean u = true;
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.SKILL) {
            AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
/*
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                for (AbstractPower pow : mo.powers) {
                    if (pow.ID.equals("rosmontis:FearPower") && !mo.isDead && u) {
                        u = false;
*/
            addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new FearPower(mo, this.amount)));

//                    }    }
        }
/*
            if (u) {
                AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
                while (!mo.isDead)
                    mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new FearPower((AbstractCreature) mo, this.amount)));
            }
*/

    }


/*
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.SKILL) {
            boolean used = false;
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                boolean temp = true;
                for (AbstractPower pow : mo.powers) {
                    if (pow.ID.equals("rosmontis:FearPower")) {
                        temp = false;
                    }
                }
                if (temp && !used) {
                    used =true;
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new FearPower((AbstractCreature) mo, this.amount)));
                    break;
                }
            }
            if (!used) {
                AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new FearPower((AbstractCreature) mo, this.amount)));
            }
        }
*/

}
