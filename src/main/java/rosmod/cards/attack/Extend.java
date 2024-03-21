package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Extend extends BaseCard {

    public static final String ID = makeID("Extend");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );

    public Extend() {
        super(ID, info);
        setExhaust(true);
        setSelfRetain(true);
        setDamage(5);
        setMagic(5);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public void atTurnStart() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.cardID.equals(this.cardID))
                this.magicNumber += 2;
        }
        setDamage(this.magicNumber);
    }


//    public void triggerOnEndOfTurnForPlayingCard() {
//        int temp = 0;
//        ArrayList<AbstractCard> groupCopy = new ArrayList<>();//new hand card copy
//        for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {//read hand
//            if (!abstractCard.freeToPlayOnce && abstractCard.type == CardType.ATTACK) {
//                groupCopy.add(abstractCard);
//                continue;
//            }
//        }
//        for (AbstractCard abstractCard : groupCopy) {
//            if (abstractCard.cardID.equals(this.cardID)) {
//                temp++;
//            }
//        }
//        this.damage += temp * 5;
//    }
}
