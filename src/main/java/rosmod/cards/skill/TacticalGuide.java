package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class TacticalGuide extends BaseCard {
    public static final String ID = makeID("TacticalGuide");
    private static final CardStats info = new CardStats(Rosmontis.Enums.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);

    public TacticalGuide() {
        super(ID, info);
        setCostUpgrade(0);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new CalculatedGambleAction(true));
    }

}
