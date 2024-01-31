package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class DefendRos extends BaseCard {
    public static final String ID = makeID("DefendRos");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;

//    private static final int DEX = 1;
    public DefendRos(){
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setBlock(BLOCK, UPG_BLOCK); //Sets the card's Block and how much it changes when upgraded.
        tags.add(CardTags.STARTER_DEFEND); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
//        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player,DEX)));
    }
}
