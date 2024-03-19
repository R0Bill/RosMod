package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Memory extends BaseCard {
    public static final String ID = makeID("Memory");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public Memory(){
        super(ID,info);
        setSelfRetain(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 2), 2));
    }

}
