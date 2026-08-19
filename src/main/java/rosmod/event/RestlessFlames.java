package rosmod.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import rosmod.cards.attack.Frenzy;
import rosmod.cards.attack.Overload;
import rosmod.cards.power.Catharsis;
import rosmod.cards.skill.BreachPoint;
import rosmod.relics.RampageAmplifier;

import static rosmod.BasicMod.makeID;

/** 躁动的火焰：触碰（失控体系卡+失血）/ 取暖（治疗）/ 跃入（暴走增幅器遗物+大失血）。 */
public class RestlessFlames extends AbstractImageEvent {
    public static final String ID = makeID("RestlessFlames");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private enum CurrentScreen { INTRO, COMPLETE }

    private CurrentScreen curScreen = CurrentScreen.INTRO;

    public RestlessFlames() {
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
                    case 0: // 触碰火焰
                        AbstractDungeon.player.damage(new DamageInfo(null, 8, DamageInfo.DamageType.HP_LOSS));
                        obtainCard(randomInstability());
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        break;
                    case 1: // 引火取暖
                        AbstractDungeon.player.heal(10);
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        break;
                    case 2: // 跃入火焰
                        AbstractDungeon.player.damage(new DamageInfo(null, 15, DamageInfo.DamageType.HP_LOSS));
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, new RampageAmplifier());
                        this.curScreen = CurrentScreen.COMPLETE;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        break;
                }
                break;
            case COMPLETE:
                openMap();
                break;
        }
    }

    private AbstractCard randomInstability() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToTop(new Frenzy());
        group.addToTop(new Overload());
        group.addToTop(new BreachPoint());
        group.addToTop(new Catharsis());
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
