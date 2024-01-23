package rosmod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class Skill3Power extends AbstractPower {
    private static final PowerStrings powerStrings;
    public static final String NAME ;
    public static final String[] DESCRIPTIONS;

    public Skill3Power(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Skill3Power";
        this.owner = owner;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.loadRegion("doubleTap");
    }
    @Override
    public void updateDescription() {
            this.description = DESCRIPTIONS[0];

    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK ) {
            this.flash();
            //attack 3 times
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }
            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);

            //cost++
            ArrayList<AbstractCard> groupCopy = new ArrayList<>();//new hand card copy
            for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {//read hand
                if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce && abstractCard.type == AbstractCard.CardType.ATTACK) {
                    groupCopy.add(abstractCard);
                    continue;
                }
            }
            for (AbstractCard abstractCard : groupCopy){
                int tempa = abstractCard.cost;
                abstractCard.setCostForTurn(tempa*2);
            }
            //stun by StSLib
            double Ran = Math.random();
            if(Ran >= 0.49) {
                if (card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                        addToBot((AbstractGameAction) new StunMonsterAction((AbstractMonster) mo, (AbstractCreature) AbstractDungeon.player));
                    }
                } else {
                    addToBot((AbstractGameAction) new StunMonsterAction((AbstractMonster) action.target, (AbstractCreature) AbstractDungeon.player));
                }
            }
        }
    }

    @Override
    public float modifyBlock(float m){
        //losing Dexterity
        if(m-3 <= 0){
            return 0;
        }
        else
            return m-3;
    }
    @Override
    public void atStartOfTurn(){
        //cost++
        ArrayList<AbstractCard> groupCopy = new ArrayList<>();//new hand card copy
        for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {//read hand
            if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce && abstractCard.type == AbstractCard.CardType.ATTACK) {
                groupCopy.add(abstractCard);
                continue;
            }
        }
        for (AbstractCard abstractCard : groupCopy){
            int tempa = abstractCard.cost;
            abstractCard.setCostForTurn(tempa*2);
        }
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Skill3Power");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
