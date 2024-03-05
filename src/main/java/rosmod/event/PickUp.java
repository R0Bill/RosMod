package rosmod.event;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.relics.JessicaWallet;
import rosmod.relics.StolenMiniCake;

import static rosmod.BasicMod.makeID;

public class PickUp extends AbstractImageEvent {
    public static final String ID = makeID("PickUp");
    public static final EventStrings eventstring = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventstring.NAME;

    private static final String[] DESCRIPTIONS = eventstring.DESCRIPTIONS;

    private static final String[] OPTIONS = eventstring.OPTIONS;

    private enum CurrentScreen {
        INTRO, CHOSE1, FURTHER1, COMPLETE
    }

    private CurrentScreen curScreen = CurrentScreen.INTRO;

    public PickUp() {
        super(NAME, DESCRIPTIONS[0], "rosmod/images/missing");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1], new JessicaWallet());
        this.imageEventText.setDialogOption(OPTIONS[2]);

    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.curScreen) {
            case INTRO:
                if (buttonPressed == 0) {
                    this.imageEventText.removeDialogOption(2);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.curScreen = CurrentScreen.FURTHER1;
                    break;
                } else if (buttonPressed == 1) {
                    this.imageEventText.removeDialogOption(2);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[6]);
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F, (AbstractRelic) new JessicaWallet());
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;
                } else if (buttonPressed == 2) {
                    this.imageEventText.removeDialogOption(2);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[6]);
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;
                }
            case CHOSE1:
                if (buttonPressed == 0) {
                    this.imageEventText.updateDialogOption(0, OPTIONS[6]);
                    this.imageEventText.removeDialogOption(1);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                    AbstractDungeon.player.heal(999, true);
                    AbstractDungeon.player.increaseMaxHp(15, true);
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;
                } else if (buttonPressed == 1) {
                    this.imageEventText.updateDialogOption(0, OPTIONS[6]);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                    this.imageEventText.removeDialogOption(1);
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F, (AbstractRelic) new StolenMiniCake());
                    this.curScreen = CurrentScreen.COMPLETE;
                    break;

                }
            case FURTHER1:
                if (buttonPressed == 0) {
                    this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                    this.imageEventText.setDialogOption(OPTIONS[4], new StolenMiniCake());
                    this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                    this.curScreen = CurrentScreen.CHOSE1;
                    break;
                }
            case COMPLETE:
                openMap();
                break;
        }
    }
}
