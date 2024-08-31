package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.FearPower;
import rosmod.util.CardStats;

public class Deprive extends BaseCard {//

    public static final String ID = makeID("Deprive");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    public Deprive() {
        super(ID, info);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new FearPower(abstractMonster, (this.upgraded ? 5 : 3))));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new WeakPower(abstractMonster, 5, false), 5));
    }
}
