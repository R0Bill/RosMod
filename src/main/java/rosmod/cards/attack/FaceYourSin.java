package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.FearPower;
import rosmod.util.CardStats;

public class FaceYourSin extends BaseCard {

    public static final String ID = makeID("FaceYourSin"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public FaceYourSin() {
        super(ID, info);
        setCostUpgrade(0);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(abstractMonster.getIntentBaseDmg() >= 0 && !abstractMonster.isDead ? new DamageAction(abstractMonster, new DamageInfo(abstractMonster, abstractMonster.getIntentBaseDmg(), DamageInfo.DamageType.NORMAL)) : new ApplyPowerAction(abstractMonster, abstractPlayer, new FearPower(abstractMonster, this.upgraded ? 3 : 2)));
//        abstractMonster.setMove(cardStrings.EXTENDED_DESCRIPTION[0],(byte) 0, AbstractMonster.Intent.ATTACK,99,1,false);
    }

}
