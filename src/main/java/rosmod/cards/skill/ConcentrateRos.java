package rosmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class ConcentrateRos extends BaseCard {//
    public static final String ID = makeID("ConcentrateRos");
    private static  final int Healnumber = 3;
    private static final int UPG_Healnumber = 2;
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    public ConcentrateRos(){
        super(ID,info);
        setExhaust(true,false);
        tags.add(CardTags.HEALING);
    }

    public void HEAL(AbstractPlayer abstractPlayer) {

        addToBot((AbstractGameAction) new AddTemporaryHPAction((AbstractPlayer) abstractPlayer, (AbstractPlayer) abstractPlayer, this.upgraded ? UPG_Healnumber + Healnumber : Healnumber));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        abstractPlayer.draw(1);
//        addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, 1));
        HEAL(abstractPlayer);
        if (abstractPlayer.hand.group.size() == 1) {
            abstractPlayer.hand.moveToExhaustPile(abstractPlayer.hand.getTopCard());
        } else if (abstractPlayer.hand.group.size() > 1) {
            if (this.upgraded) {
                addToBot((AbstractGameAction) new ExhaustAction(1, false));

            } else {
                addToBot((AbstractGameAction) new ExhaustAction(1, true, false, false));
            }

        }
        /*if (abstractPlayer.hand.getTopCard()==null) {
            addToBot((AbstractGameAction) new AddTemporaryHPAction((AbstractPlayer) abstractPlayer, (AbstractPlayer) abstractPlayer, this.upgraded ? UPG_Healnumber + Healnumber : Healnumber));
        } else if (abstractPlayer.hand.size() == 1) {
            abstractPlayer.hand.moveToExhaustPile(abstractPlayer.hand.getBottomCard());
            addToBot((AbstractGameAction) new AddTemporaryHPAction((AbstractPlayer) abstractPlayer, (AbstractPlayer) abstractPlayer, this.upgraded ? UPG_Healnumber + Healnumber : Healnumber));
        }
        else if(abstractPlayer.hand.size()>1){
            if (this.upgraded) {
                addToBot((AbstractGameAction)new ExhaustAction(1, false));

            } else {
                addToBot((AbstractGameAction) new ExhaustAction(1, true, false, false));
            }
            addToBot((AbstractGameAction) new AddTemporaryHPAction((AbstractPlayer) abstractPlayer, (AbstractPlayer) abstractPlayer, this.upgraded ? UPG_Healnumber + Healnumber : Healnumber));
        }*/
    }
}
