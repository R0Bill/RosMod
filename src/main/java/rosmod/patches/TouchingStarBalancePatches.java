package rosmod.patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.unique.EnlightenmentAction;
import com.megacrit.cardcrawl.actions.unique.MadnessAction;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import com.megacrit.cardcrawl.relics.MummifiedHand;

import java.util.ArrayList;

public class TouchingStarBalancePatches {

    @SpirePatch(clz = RandomizeHandCostAction.class, method = "update")
    public static class ReplaceRndCostHand {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(RandomizeHandCostAction _inst) {
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card.cost >= 0 && !card.cardID.equals("rosmontis:TouchingStars") && !card.cardID.equals("rosmontis:ForgetMeNot")) {
                    int newCost = AbstractDungeon.cardRandomRng.random(3);
                    if (card.cost != newCost) {
                        card.cost = newCost;
                        card.costForTurn = card.cost;
                        card.isCostModified = true;
                    }
                }
            }
            _inst.isDone = true;
            return SpireReturn.Return();
        }
    }

    @SpirePatch(clz = EnlightenmentAction.class, method = "update")
    public static class ReplaceEnlighten {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(EnlightenmentAction _inst) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.costForTurn > 1 && !c.cardID.equals("rosmontis:TouchingStars") && !c.cardID.equals("rosmontis:ForgetMeNot")) {
                    c.costForTurn = 1;
                    c.isCostModifiedForTurn = true;
                }
            }
            _inst.isDone = true;
            return SpireReturn.Return();
        }
    }

    @SpirePatch(clz = ConfusionPower.class, method = "onCardDraw")
    public static class ReplaceConfuse {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(ConfusionPower _inst, AbstractCard card) {
            if (card.cardID.equals("rosmontis:TouchingStars") || card.cardID.equals("rosmontis:ForgetMeNot"))
                return SpireReturn.Return();
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = MadnessAction.class, method = "findAndModifyCard")
    public static class ReplaceMad {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(MadnessAction _inst, boolean better) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.cardID.equals("rosmontis:TouchingStars") && !c.cardID.equals("rosmontis:ForgetMeNot")) {
                    if (c.costForTurn > 0) {
                        c.cost = 0;
                        c.costForTurn = 0;
                        c.isCostModified = true;
                        c.superFlash(Color.GOLD.cpy());
                    } else if (c.cost > 0) {
                        c.cost = 0;
                        c.costForTurn = 0;
                        c.isCostModified = true;
                        c.superFlash(Color.GOLD.cpy());
                    }
                }
            }
            return SpireReturn.Return();
        }
    }

    @SpirePatch(clz = MummifiedHand.class, method = "onUseCard")
    public static class RelicHandPatch {
        @SpireInsertPatch(rloc = 20, localvars = "groupCopy")
        public static void removeSomeCards(MummifiedHand _instance, AbstractCard card, UseCardAction action, ArrayList<AbstractCard> groupCopy) {
            groupCopy.removeIf((c) -> c.cardID.equals("rosmontis:TouchingStars") || c.cardID.equals("rosmontis:ForgetMeNot"));
        }
    }

    @SpirePatch(clz = DuplicationPower.class, method = "onUseCard")
    public static class StopDupPower {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(DuplicationPower _inst, AbstractCard c, UseCardAction action) {
            if (c.cardID.equals("rosmontis:TouchingStars") || c.cardID.equals("rosmontis:ForgetMeNot"))
                return SpireReturn.Return();
            return SpireReturn.Continue();
        }
    }
}
