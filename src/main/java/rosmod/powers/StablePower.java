package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
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
    private AbstractCard.CardTarget cardTarget = null;

    @Override
    public float atDamageGive(float d, DamageInfo.DamageType type) {
        int temp = (int) Math.ceil(d / 5);
        if (target != null && cardTarget != AbstractCard.CardTarget.ALL_ENEMY) {
            target = null;
            cardTarget = null;
            addToBot(new DamageAction(target, new DamageInfo(null, temp, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        } else if (target != null && cardTarget == AbstractCard.CardTarget.ALL_ENEMY) {
            target = null;
            cardTarget = null;
            addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(temp, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        return d;
    }

    @Override
    public void onUseCard(AbstractCard abstractCard, UseCardAction action) {
        if (!abstractCard.purgeOnUse && abstractCard.target != AbstractCard.CardTarget.NONE && abstractCard.target != AbstractCard.CardTarget.SELF && abstractCard.target != AbstractCard.CardTarget.SELF_AND_ENEMY) {
            target = action.target;
            cardTarget = abstractCard.target;
        }

    }
}
