package rosmod.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import rosmod.cards.other.Infected;
import rosmod.relics.Terminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static rosmod.BasicMod.makeID;

public class RhodesIslandSafeHouse extends AbstractImageEvent {

    public static final String ID = makeID("RhodesIslandSafeHouse");
    public static final EventStrings eventstring = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventstring.NAME;

    private static final String[] DESCRIPTIONS = eventstring.DESCRIPTIONS;

    private static final String[] OPTIONS = eventstring.OPTIONS;

    private enum CurrentScreen {
        INTRO, ROOM, COMPLETE
    }

    public boolean hasterminal() {
        return AbstractDungeon.player.hasRelic(Terminal.ID);
    }

    private RhodesIslandSafeHouse.CurrentScreen curScreen = RhodesIslandSafeHouse.CurrentScreen.INTRO;

    public RhodesIslandSafeHouse() {
        super(NAME, DESCRIPTIONS[0], "rosmod/images/missing");
        if (hasterminal()) {
            this.imageEventText.setDialogOption(OPTIONS[0]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[1], true, (AbstractRelic) new Terminal());
        }
        this.imageEventText.setDialogOption(OPTIONS[2]);
        this.imageEventText.setDialogOption(OPTIONS[3]);

    }

    private void upgACC() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group)
            if (c.cardID.equals("rosmontis:Accumulation"))
                c.upgrade();

    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.curScreen) {
            case INTRO:
                if (buttonPressed == 0) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                    this.imageEventText.updateDialogOption(1, OPTIONS[5]);
                    this.imageEventText.updateDialogOption(2, OPTIONS[6]);
                    this.curScreen = CurrentScreen.ROOM;
                    break;
                } else if (buttonPressed == 1) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                    this.imageEventText.updateDialogOption(1, OPTIONS[5]);
                    this.imageEventText.updateDialogOption(2, OPTIONS[6]);
                    this.curScreen = CurrentScreen.ROOM;
                    AbstractCard c = new Infected();
                    for (AbstractRelic r : AbstractDungeon.player.relics)
                        r.onObtainCard(c);
                    AbstractDungeon.player.masterDeck.addToTop(c);
                    for (AbstractRelic r : AbstractDungeon.player.relics)
                        r.onMasterDeckChange();
                    break;
                } else if (buttonPressed == 2) {
                    this.imageEventText.removeDialogOption(2);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;
                }
            case COMPLETE:
                this.imageEventText.clearRemainingOptions();
                openMap();
            case ROOM:
                if (buttonPressed == 0 && curScreen == CurrentScreen.ROOM) {
                    AbstractDungeon.player.heal(2 * (AbstractDungeon.player.maxHealth) / 5, true);

                    this.imageEventText.removeDialogOption(2);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;
                } else if (buttonPressed == 1) {
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), getr());

                    this.imageEventText.removeDialogOption(2);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;
                } else if (buttonPressed == 2) {
                    upgradeCards();
                    upgACC();
//                    AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getUpgradableCards(), 1, OPTIONS[6], true, false, false, false);

                    this.imageEventText.removeDialogOption(2);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
//                    AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getUpgradableCards(), 1, OPTIONS[6], true, false, false, false);
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;
                }
        }
    }

    private AbstractRelic getr() {
        return AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
    }

    private void upgradeCards() {
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade())
                upgradableCards.add(c);
        }
        List<String> cardMetrics = new ArrayList<>();
        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty())
            if (upgradableCards.size() == 1) {
                ((AbstractCard) upgradableCards.get(0)).upgrade();
                cardMetrics.add(((AbstractCard) upgradableCards.get(0)).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(0)).makeStatEquivalentCopy()));
            } else {
                ((AbstractCard) upgradableCards.get(0)).upgrade();
                ((AbstractCard) upgradableCards.get(1)).upgrade();
                cardMetrics.add(((AbstractCard) upgradableCards.get(0)).cardID);
                cardMetrics.add(((AbstractCard) upgradableCards.get(1)).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(0)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard) upgradableCards.get(1)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));
            }
    }

}
