package rosmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.relics.Oath;

public class RelicPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "damage")
    public static class PlayerDeadPatch {
        @SpireInsertPatch(rloc = 149, localvars = {"damageAmount"})
        public static SpireReturn<Void> Insert(AbstractPlayer _instance, DamageInfo info, @ByRef int[] damageAmount) {
            // Mark of the Bloom zeroes all healing, so reviving here would leave the player at 0 HP.
            if (AbstractDungeon.player.hasRelic(Oath.ID)
                    && !AbstractDungeon.player.hasRelic("Mark of the Bloom")) {
                AbstractRelic oath = AbstractDungeon.player.getRelic(Oath.ID);
                // usedUp() doesn't remove the relic, so gate on it to keep Oath one-use.
                if (!oath.usedUp) {
                    oath.onTrigger();
                    return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }
    }
}
