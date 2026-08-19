package rosmod.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import rosmod.cards.attack.Echo;
import rosmod.cards.attack.Engrave;
import rosmod.cards.power.Cherish;
import rosmod.cards.skill.Forget;
import rosmod.cards.skill.Reminiscence;
import rosmod.cards.skill.Somniloquy;

import static rosmod.BasicMod.makeID;

/** 遗落的记事本：翻阅（治疗）/ 抄写（记忆体系卡）/ 撕毁（移除牌）。 */
public class LostNotebook extends AbstractImageEvent {
    public static final String ID = makeID("LostNotebook");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private enum CurrentScreen { INTRO, REMOVE, COMPLETE }

    private CurrentScreen curScreen = CurrentScreen.INTRO;

    public LostNotebook() {
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
            this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.curScreen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0: // 翻阅
                        AbstractDungeon.player.heal(12);
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        break;
                    case 1: // 抄写
                        obtainCard(randomMemory());
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        break;
                    case 2: // 撕毁
                        this.curScreen = CurrentScreen.REMOVE;
                        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, OPTIONS[4], false);
                        break;
                }
                break;
            case COMPLETE:
                openMap();
                break;
        }
    }

    private AbstractCard randomMemory() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToTop(new Echo());
        group.addToTop(new Forget());
        group.addToTop(new Somniloquy());
        group.addToTop(new Reminiscence());
        group.addToTop(new Engrave());
        group.addToTop(new Cherish());
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
