package rosmod.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.cards.other.Infected;

import static rosmod.BasicMod.makeID;

/** 尖塔拾荒者：搜刮（金币+感染诅咒）/ 安葬（治疗）/ 离开。 */
public class SpireScavenger extends AbstractImageEvent {
    public static final String ID = makeID("SpireScavenger");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private enum CurrentScreen { INTRO, COMPLETE }

    private CurrentScreen curScreen = CurrentScreen.INTRO;

    public SpireScavenger() {
        super(eventStrings.NAME, DESCRIPTIONS[0], "rosmod/images/missing");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.curScreen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0: // 搜刮
                        AbstractDungeon.player.gainGold(50);
                        obtainCard(new Infected());
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        break;
                    case 1: // 安葬
                        AbstractDungeon.player.heal(10);
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
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

    private void obtainCard(AbstractCard c) {
        for (AbstractRelic r : AbstractDungeon.player.relics)
            r.onObtainCard(c);
        AbstractDungeon.player.masterDeck.addToTop(c);
        for (AbstractRelic r : AbstractDungeon.player.relics)
            r.onMasterDeckChange();
    }
}
