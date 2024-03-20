package rosmod.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.Logger;
import rosmod.BasicMod;

import static rosmod.BasicMod.makeID;
import static rosmod.BasicMod.relicPath;

public class Terminal extends CustomRelic implements ClickableRelic {

    private static final String NAME = "Terminal";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private Logger logger = BasicMod.logger;


    public Terminal() {
        super(ID, ImageMaster.loadImage(relicPath("Terminal.png")), ImageMaster.loadImage(relicPath("Terminal.png")), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
        this.counter = -1;
    }

    public void onRightClick() {
//        logger.info(EnergyPanel.getCurrentEnergy());
        if (EnergyPanel.getCurrentEnergy() >= 1 && !AbstractDungeon.player.exhaustPile.isEmpty() && AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
            AbstractCard card = AbstractDungeon.player.exhaustPile.getTopCard();
            addToBot(new MakeTempCardInHandAction(card, 1));
            AbstractDungeon.player.exhaustPile.removeCard(card);
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
            AbstractDungeon.player.energy.use(1);
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

}
