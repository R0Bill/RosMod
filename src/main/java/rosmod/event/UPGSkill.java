package rosmod.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import rosmod.cards.power.Skill1;
import rosmod.cards.power.Skill2;
import rosmod.cards.power.Skill3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static rosmod.BasicMod.makeID;

public class UPGSkill extends AbstractImageEvent {
    public static final String ID = makeID("UPGSkill");
    public static final EventStrings eventstring = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventstring.NAME;
    private static final String[] DESCRIPTIONS = eventstring.DESCRIPTIONS;
    private static final String[] OPTIONS = eventstring.OPTIONS;

    private enum CurrentScreen {
        INTRO, COMPLETE
    }

    private int T = -1;

    private UPGSkill.CurrentScreen curScreen = UPGSkill.CurrentScreen.INTRO;

    private void upgACC() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group)
            if (c.cardID.equals("rosmontis:Accumulation"))
                c.upgrade();

    }

    private int DEC() {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.cardID.equals("rosmontis:Skill1"))
                return 1;
            else if (card.cardID.equals("rosmontis:Skill2") || card.cardID.equals("rosmontis:Skill3")) {
                return 2;
            }
        }
        return 0;
    }

    public UPGSkill() {
        super(NAME, DESCRIPTIONS[0], "rosmod/images/missing");
        T = DEC();
        switch (T) {
            case 0:
                this.imageEventText.setDialogOption(OPTIONS[0], new Skill1());
                this.imageEventText.setDialogOption(OPTIONS[3]);
                break;
            case 1:
                this.imageEventText.setDialogOption(OPTIONS[1], new Skill2());
                this.imageEventText.setDialogOption(OPTIONS[2], new Skill3());
                this.imageEventText.setDialogOption(OPTIONS[3]);
                break;
            case 2:
                this.imageEventText.setDialogOption(OPTIONS[4]);
                this.imageEventText.setDialogOption(OPTIONS[3]);
                break;
        }
    }

    public void buttonEffect(int buttonPressed) {
        switch (this.curScreen) {
            case INTRO:
                switch (T) {
                    case 0:
                        switch (buttonPressed) {
                            case 0:
                                this.imageEventText.removeDialogOption(1);
                                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);

                                AbstractCard c = new Skill1();
                                for (AbstractRelic r : AbstractDungeon.player.relics)
                                    r.onObtainCard(c);
                                AbstractDungeon.player.masterDeck.addToTop(c);
                                for (AbstractRelic r : AbstractDungeon.player.relics)
                                    r.onMasterDeckChange();

                                this.curScreen = CurrentScreen.COMPLETE;
                                break;
                            case 1:
                                this.imageEventText.removeDialogOption(1);
                                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                                this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                                this.curScreen = CurrentScreen.COMPLETE;
                                break;
                        }
                        break;
                    case 1:
                        switch (buttonPressed) {
                            case 0:
                                this.imageEventText.removeDialogOption(2);
                                this.imageEventText.removeDialogOption(1);
                                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                                AbstractCard c = new Skill2();
                                for (AbstractRelic r : AbstractDungeon.player.relics)
                                    r.onObtainCard(c);
                                AbstractDungeon.player.masterDeck.addToTop(c);
                                for (AbstractRelic r : AbstractDungeon.player.relics)
                                    r.onMasterDeckChange();

                                AbstractDungeon.player.masterDeck.removeCard("rosmontis:Skill1");
                                this.curScreen = CurrentScreen.COMPLETE;
                                break;

                            case 1:
                                this.imageEventText.removeDialogOption(2);
                                this.imageEventText.removeDialogOption(1);
                                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                                AbstractCard cc = new Skill3();
                                for (AbstractRelic r : AbstractDungeon.player.relics)
                                    r.onObtainCard(cc);
                                AbstractDungeon.player.masterDeck.addToTop(cc);
                                for (AbstractRelic r : AbstractDungeon.player.relics)
                                    r.onMasterDeckChange();

                                AbstractDungeon.player.masterDeck.removeCard("rosmontis:Skill1");
                                this.curScreen = CurrentScreen.COMPLETE;
                                break;
                            case 2:
                                this.imageEventText.removeDialogOption(2);
                                this.imageEventText.removeDialogOption(1);
                                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                                this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                                this.curScreen = CurrentScreen.COMPLETE;
                                break;
                        }
                        break;
                    case 2:
                        switch (buttonPressed) {
                            case 0:
                                this.imageEventText.removeDialogOption(1);
                                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                                this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                                upgACC();
                                upgradeCards();
                                this.curScreen = CurrentScreen.COMPLETE;
                                break;
                            case 1:
                                this.imageEventText.removeDialogOption(1);
                                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                                this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                                this.curScreen = CurrentScreen.COMPLETE;
                                break;
                        }
                        break;
                }
            case COMPLETE:
                openMap();
        }
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
                upgradableCards.get(0).upgrade();
                cardMetrics.add(upgradableCards.get(0).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy()));
            } else {
                upgradableCards.get(0).upgrade();
                upgradableCards.get(1).upgrade();
                cardMetrics.add(upgradableCards.get(0).cardID);
                cardMetrics.add(upgradableCards.get(1).cardID);
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(0).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradableCards.get(1).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));
            }
    }
}
