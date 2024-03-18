package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.other.DTS;
import rosmod.cards.other.STD;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

import java.util.ArrayList;

public class Switching extends BaseCard {
    public static final String ID = makeID("Switching");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Switching() {
        super(ID, info);
        setInnate(false, true);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new STD());
        list.add(new DTS());
        addToBot((AbstractGameAction) new ChooseOneAction(list));
    }


//    private int DEC(AbstractPlayer player) {
//        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
//            switch (card.cardID) {
//                case "rosmontis:Skill1":
//                    return 1;
//                case "rosmontis:Skill2":
//                    return 2;
//                case "rosmontis:Skill3":
//                    return 3;
//            }
//        }
//        if (player.getPower(StrengthPower.POWER_ID).amount == player.getPower(DexterityPower.POWER_ID).amount)
//            return -1;
//        return 0;
//    }
//
//    private void STD(AbstractPlayer player) {
//    }
//
//    private void DTS(AbstractPlayer player) {
//    }
//
//    @Override
//    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        switch (DEC(abstractPlayer)) {
//            case -1:
//                addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 1)));
//                addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1)));
//                break;
//            case 0:
//                if (abstractPlayer.currentHealth <= abstractPlayer.maxHealth / 3)
//                    STD(abstractPlayer);
//                else
//                    DTS(abstractPlayer);
//                break;
//            case 1:
//                int num = 0;
//                int num2 = 0;
//                for (AbstractCard card : abstractPlayer.masterDeck.group) {
//                    if (card.type == CardType.ATTACK)
//                        num++;
//                    else if (card.type == CardType.SKILL)
//                        num2++;
//                }
//                if (num2 == num) {
//                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 1)));
//                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1)));
//                } else if (num > num2)
//                    DTS(abstractPlayer);
//                else
//                    STD(abstractPlayer);
//                break;
//            case 2:
//                STD(abstractPlayer);
//                break;
//            case 3:
//                DTS(abstractPlayer);
//                break;
//        }
//    }
}
