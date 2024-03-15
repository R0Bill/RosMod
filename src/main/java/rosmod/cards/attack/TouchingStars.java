package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
            8 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    private boolean firstTurn = true;

    public TouchingStars() {
        super(ID, info);
        setMagic(8);
        setDamage(99);
        setSelfRetain(true);
        setExhaust(true);
        setInnate(false, true);
        this.dontTriggerOnUseCard = true;
    }

    public void setCostForTurn(int amt) {
        if (amt == this.magicNumber - 1) {
            this.costForTurn = amt;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.energy.energy >= this.energyOnUse) {
            this.glowColor = com.badlogic.gdx.graphics.Color.valueOf("bacdbaff");
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
            if (this.costForTurn >= 0) {
                magicNumber--;
                addToBot((AbstractGameAction) new ReduceCostAction((AbstractCard) this));
            }
        }
    }
    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.firstTurn)
            this.firstTurn = false;
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = this.makeCopy();

        for (int i = 0; i < this.timesUpgraded; ++i) {
            card.upgrade();
        }

        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.freeToPlayOnce = false;
        return card;
    }
}
