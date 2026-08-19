package rosmod.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import rosmod.cards.attack.BladeDance;
import rosmod.cards.power.SwordArsenal;
import rosmod.cards.skill.SwordChanneling;
import rosmod.cards.skill.SwordSheath;
import rosmod.cards.skill.TwinBlades;

import static rosmod.BasicMod.makeID;

/** 断剑冢：尖塔中的剑冢——拔剑（剑体系卡）/ 葬剑（移除牌）/ 离开。 */
public class BrokenSwordMound extends AbstractImageEvent {
    public static final String ID = makeID("BrokenSwordMound");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private enum CurrentScreen { INTRO, REMOVE, COMPLETE }

    private CurrentScreen curScreen = CurrentScreen.INTRO;

    public BrokenSwordMound() {
        super(eventStrings.NAME, DESCRIPTIONS[0], "rosmod/images/missing");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    public void update() {
        super.update();
        if (this.curScreen == CurrentScreen.REMOVE && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.masterDeck.removeCard(c);
            this.curScreen = CurrentScreen.COMPLETE;
            this.imageEventText.clearAllDialogs();
            this.imageEventText.setDialogOption(OPTIONS[3]);
            this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.curScreen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0: // 拔剑
                        obtainCard(randomSword());
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        break;
                    case 1: // 葬剑
                        this.curScreen = CurrentScreen.REMOVE;
                        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, OPTIONS[4], false);
                        break;
                    case 2: // 离开
                        openMap();
                        break;
                }
                break;
            case COMPLETE:
                openMap();
                break;
        }
    }

    private AbstractCard randomSword() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToTop(new SwordSheath());
        group.addToTop(new SwordChanneling());
        group.addToTop(new TwinBlades());
        group.addToTop(new BladeDance());
        group.addToTop(new SwordArsenal());
        return group.getRandomCard(true);
    }

    private void obtainCard(AbstractCard c) {
        for (AbstractRelic r : AbstractDungeon.player.relics)
            r.onObtainCard(c);
        AbstractDungeon.player.masterDeck.addToTop(c);
        for (AbstractRelic r : AbstractDungeon.player.relics)
            r.onMasterDeckChange();
        AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
    }
}
