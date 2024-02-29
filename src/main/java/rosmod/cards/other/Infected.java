package rosmod.cards.other;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import rosmod.cards.BaseCard;
import rosmod.util.CardStats;

public class Infected extends BaseCard {
    public static final String ID = makeID("Infected");
    private static final CardStats info = new CardStats(
            CardColor.CURSE, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.CURSE, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.CURSE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //    private static final int DEX = 1;
    public Infected() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        return false;
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        addToBot((AbstractGameAction) new LoseHPAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, 1));
    }

    public void triggerWhenDrawn() {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new IntangiblePlayerPower((AbstractCreature) AbstractDungeon.player, 1), 1));

    }
}
