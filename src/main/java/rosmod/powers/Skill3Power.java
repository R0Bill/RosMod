package rosmod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static rosmod.BasicMod.makeID;

public class Skill3Power extends BasePower {
    public static final String POWER_ID = makeID("Skill3Power");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public Skill3Power(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && !card.cardID.equals("rosmontis:TouchingStars") && !card.cardID.equals("rosmontis:ForgetMeNot")) {
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
            for (AbstractCard abstractCard : groupCopy) {
                if (!abstractCard.cardID.equals("rosmontis:TouchingStars") && !abstractCard.cardID.equals("rosmontis:ForgetMeNot")) {
                    int tempa = abstractCard.cost;
                    abstractCard.setCostForTurn(tempa * 2);
                }
            }

//            for (AbstractCard ccard : AbstractDungeon.player.hand.group) {
//                if (ccard.cost > 0 && ccard.costForTurn > 0 && !ccard.freeToPlayOnce && ccard.type == AbstractCard.CardType.ATTACK && !ccard.cardID.equals("rosmontis:TouchingStars")) {
//                    ccard.setCostForTurn(card.cost * 2);
//                }
//            }

            //stun by StSLib
            double Ran = Math.random();
            if (Ran >= 0.59) {
                if (card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                        addToBot(new StunMonsterAction(mo, AbstractDungeon.player));
                    }
                } else {
                    addToBot(new StunMonsterAction((AbstractMonster) action.target, AbstractDungeon.player));
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
    public void onCardDraw(AbstractCard notOnUse) {
//      bullshit code, optimize it
        //cost++
        ArrayList<AbstractCard> groupCopy = new ArrayList<>();//new hand card copy
        for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {//read hand
            if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce && abstractCard.type == AbstractCard.CardType.ATTACK) {
                groupCopy.add(abstractCard);
                continue;
            }
        }
        for (AbstractCard abstractCard : groupCopy) {
            if (!abstractCard.cardID.equals("rosmontis:TouchingStars") && !abstractCard.cardID.equals("rosmontis:ForgetMeNot")) {
                int tempa = abstractCard.cost;
                abstractCard.setCostForTurn(tempa * 2);
            }
        }

//        for (AbstractCard card : AbstractDungeon.player.hand.group)
//            if (card.cost > 0 && card.costForTurn > 0 && !card.freeToPlayOnce && card.type == AbstractCard.CardType.ATTACK && !card.cardID.equals("rosmontis:TouchingStars"))
//                card.setCostForTurn(card.cost * 2);
    }
}
