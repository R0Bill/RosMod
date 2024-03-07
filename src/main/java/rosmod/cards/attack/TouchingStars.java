package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class TouchingStars extends BaseCard {//bug
    public static final String ID = makeID("TouchingStars"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            10 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private boolean firstTurn = true;

    public TouchingStars() {
        super(ID, info);
        setDamage(188);
        setSelfRetain(true);
        setCostUpgrade(8);
        setExhaust(true);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (this.freeToPlay()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

    }
//
//    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        boolean powerExists = false;
//        for (AbstractPower pow : AbstractDungeon.player.powers) {
//            if (pow.ID.equals("Skill1Power") || pow.ID.equals("Skill2Power") || pow.ID.equals("Skill3Power")) {
//                powerExists = true;
//                break;
//            }
//        }
//        if (!powerExists)
//            return true;
//        else
//            return false;
//    }

    public void atTurnStart() {
        if (this.firstTurn) {
            this.firstTurn = false;
        } else {
            addToBot((AbstractGameAction) new ReduceCostAction((AbstractCard) this));
        }
    }
    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.firstTurn)
            this.firstTurn = false;
    }
}
