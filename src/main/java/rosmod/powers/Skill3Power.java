package rosmod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import java.util.ArrayList;

import static rosmod.BasicMod.makeID;

public class Skill3Power extends BasePower {
    public static final String POWER_ID = makeID("Skill3Power");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public Skill3Power(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        // 卡面描述「失去3点敏捷」在此落实（原为 modifyBlock 固定-3，与描述不符）
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -3), -3));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK
                && !card.cardID.equals("rosmontis:TouchingStars")
                && !card.cardID.equals("rosmontis:ForgetMeNot")) {
            this.flash();
            // 攻击视为打出3次（补2次）
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster) action.target;
            }
            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float) Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);

            // 手牌攻击牌费用×2（幂等：始终=基础费用×2）
            ArrayList<AbstractCard> groupCopy = new ArrayList<>();
            for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
                if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce && abstractCard.type == AbstractCard.CardType.ATTACK) {
                    groupCopy.add(abstractCard);
                }
            }
            for (AbstractCard abstractCard : groupCopy) {
                if (!abstractCard.cardID.equals("rosmontis:TouchingStars") && !abstractCard.cardID.equals("rosmontis:ForgetMeNot")) {
                    abstractCard.setCostForTurn(abstractCard.cost * 2);
                }
            }

            // 41% 概率眩晕
            if (Math.random() < 0.41) {
                if (card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        if (!mo.isDead)
                            addToBot(new StunMonsterAction(mo, AbstractDungeon.player));
                    }
                } else if (action.target != null) {
                    addToBot(new StunMonsterAction((AbstractMonster) action.target, AbstractDungeon.player));
                }
            }
        }
    }

    @Override
    public void onCardDraw(AbstractCard notOnUse) {
        // 抽到手的攻击牌费用×2
        ArrayList<AbstractCard> groupCopy = new ArrayList<>();
        for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
            if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce && abstractCard.type == AbstractCard.CardType.ATTACK) {
                groupCopy.add(abstractCard);
            }
        }
        for (AbstractCard abstractCard : groupCopy) {
            if (!abstractCard.cardID.equals("rosmontis:TouchingStars") && !abstractCard.cardID.equals("rosmontis:ForgetMeNot")) {
                abstractCard.setCostForTurn(abstractCard.cost * 2);
            }
        }
    }
}
