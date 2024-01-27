package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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

    public Skill1Power(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard abstractCard, UseCardAction action){
        if (!abstractCard.purgeOnUse && abstractCard.type == AbstractCard.CardType.ATTACK ) {
            magic++;
            if(magic>=2 && abstractCard.upgraded){
                magic=0;
                if(abstractCard.target==AbstractCard.CardTarget.ALL_ENEMY)
                    addToBot((AbstractGameAction)new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(abstractCard.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                else
                    addToBot((AbstractGameAction)new DamageAction(action.target,new DamageInfo(AbstractDungeon.player,abstractCard.damage,DamageInfo.DamageType.THORNS),AbstractGameAction.AttackEffect.SLASH_HEAVY));
            } else if (magic>=3&& !abstractCard.upgraded) {
                magic=0;
                if(abstractCard.target==AbstractCard.CardTarget.ALL_ENEMY)
                    addToBot((AbstractGameAction)new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(abstractCard.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                else
                    addToBot((AbstractGameAction)new DamageAction(action.target,new DamageInfo(AbstractDungeon.player,abstractCard.damage,DamageInfo.DamageType.THORNS),AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
        }
    }
    @Override
    public void atStartOfTurn(){
        magic=0;
    }

}
