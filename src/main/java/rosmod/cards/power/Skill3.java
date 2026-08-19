package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.Skill3Power;
import rosmod.util.CardStats;

import java.util.ArrayList;

public class Skill3 extends BaseCard {

    public static final String ID = makeID("Skill3");

    // 原 SPECIAL（仅训练室事件获取）；事件移除后改为 RARE 进入卡牌奖励池
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public Skill3() {
        super(ID, info);
        setInnate(false, true);
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean powerExists = false;
        for (AbstractPower pow : AbstractDungeon.player.powers) {
            if (pow.ID.equals("rosmontis:Skill1Power") || pow.ID.equals("rosmontis:Skill2Power") || pow.ID.equals("rosmontis:Skill3Power")) {
                powerExists = true;
                break;
            }
        }
        return !powerExists;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean powerExists = false;
        for (AbstractPower pow : AbstractDungeon.player.powers) {
            if (pow.ID.equals("rosmontis:Skill1Power") || pow.ID.equals("rosmontis:Skill2Power") || pow.ID.equals("rosmontis:Skill3Power")) {
                powerExists = true;
                break;
            }
        }
        if(!powerExists)
        {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new Skill3Power(abstractPlayer)));

            //cost++
            ArrayList<AbstractCard> groupCopy = new ArrayList<>();//new hand card copy
            for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {//read hand
                if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce && abstractCard.type == CardType.ATTACK) {
                    groupCopy.add(abstractCard);
                }
            }
            for (AbstractCard abstractCard : groupCopy) {
                if (!abstractCard.cardID.equals("rosmontis:TouchingStars") && !abstractCard.cardID.equals("rosmontis:ForgetMeNot")) {
                    int tempa = abstractCard.cost;
                    abstractCard.setCostForTurn(tempa * 2);
                }
            }
        }
    }
}
