package rosmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class Skill1Power extends AbstractPower {
    private static PowerStrings getPowerStrings(String ID)
    {
        return CardCrawlGame.languagePack.getPowerStrings(ID);
    }
    private static final PowerStrings powerStrings;
    public static final String NAME ;
    public static final String[] DESCRIPTIONS;
    private static int magic = 0;

    public Skill1Power(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Skill1Power";
        this.owner = owner;
        this.updateDescription();
        this.loadRegion("doubleTap");
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

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Skill1Power");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
