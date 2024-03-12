package rosmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.relics.Oath;

public class RelicPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "damage")
    public static class PlayerDeadPatch {
        @SpireInsertPatch(rloc = 149, localvars = {"damageAmount"})
        public static SpireReturn<Void> Insert(AbstractPlayer _instance, DamageInfo info, @ByRef int[] damageAmount) {
            if (AbstractDungeon.player.hasRelic(Oath.ID)) {
                AbstractDungeon.player.getRelic(Oath.ID).onTrigger();
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
