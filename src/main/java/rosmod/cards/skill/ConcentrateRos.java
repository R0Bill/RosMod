package rosmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
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
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    public ConcentrateRos(){
        super(ID,info);
        setExhaust(true);
        tags.add(CardTags.HEALING);
    }

    public void HEAL(AbstractPlayer abstractPlayer) {

        addToBot(new AddTemporaryHPAction(abstractPlayer, abstractPlayer, this.upgraded ? UPG_Healnumber + Healnumber : Healnumber));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        abstractPlayer.draw(1);
        HEAL(abstractPlayer);
        if (abstractPlayer.hand.group.size() == 1) {
            abstractPlayer.hand.moveToExhaustPile(abstractPlayer.hand.getTopCard());
        } else if (abstractPlayer.hand.group.size() > 1) {
            if (this.upgraded) {
                addToBot(new ExhaustAction(1, false));

            } else {
                addToBot(new ExhaustAction(1, true, false, false));
            }

        }
    }
}
