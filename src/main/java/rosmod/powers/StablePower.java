package rosmod.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rosmod.BasicMod.makeID;

public class StablePower extends BasePower {

    public static final String POWER_ID = makeID("StablePower");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public StablePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    private AbstractCreature target = null;
    private AbstractCreature source = null;
    private AbstractCard.CardTarget cardTarget = null;

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
//        logger.info("target:"+this.target+"  cardt:"+this.cardTarget+"  sou:"+this.source+"  da:"+damage);
        if (this.target != null && !target.isDead && this.cardTarget == AbstractCard.CardTarget.ENEMY && damage != -1) {
            addToBot(new LoseHPAction(target, (AbstractCreature) null, (int) damage / 5));
            this.target = null;
            this.source = null;
            this.cardTarget = null;
        } else if (this.target != null && !target.isDead && this.cardTarget == AbstractCard.CardTarget.ALL_ENEMY && damage != -1) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
                if (!mo.isDead)
                    addToBot(new LoseHPAction(mo, (AbstractCreature) null, (int) damage / 5));
            this.target = null;
            this.source = null;
            this.cardTarget = null;

        }
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard abstractCard, UseCardAction action) {
        this.target = action.target;
        this.cardTarget = abstractCard.target;
        this.source = action.source;
    }
}
