package rosmod.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static rosmod.BasicMod.makeID;
import static rosmod.BasicMod.relicPath;

public class Terminal extends CustomRelic implements ClickableRelic {

    private static final String NAME = "Terminal"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public Terminal() {
        super(ID, ImageMaster.loadImage(relicPath("example.png")), ImageMaster.loadImage(relicPath("example.png")), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
        this.counter = -1;
    }

    public void onRightClick() {
        if (AbstractDungeon.player.energy.energy >= 1) {
            if (!AbstractDungeon.player.exhaustPile.isEmpty()) {
                AbstractCard card = AbstractDungeon.player.exhaustPile.getTopCard();
                if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {

                    addToBot((AbstractGameAction) new MakeTempCardInHandAction(card, 1));
                    //AbstractDungeon.player.hand.addToHand(card);
                    AbstractDungeon.player.exhaustPile.removeCard(card);
                }
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
            AbstractDungeon.player.energy.use(1);
        }
    }

}
