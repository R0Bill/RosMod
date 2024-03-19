package rosmod.cards.skill;

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
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

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
        addToBot(new ChooseOneAction(list));
    }


}
