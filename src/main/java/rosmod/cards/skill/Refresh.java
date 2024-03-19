package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Refresh extends BaseCard {
    public static final String ID = makeID("Refresh");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public Refresh() {
        super(ID, info);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
        addToBot(new RemoveDebuffsAction(abstractPlayer));
    }

}
