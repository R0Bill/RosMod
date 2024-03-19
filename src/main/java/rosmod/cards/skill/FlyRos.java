package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class FlyRos extends BaseCard {
    public static final String ID = makeID("FlyRos");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public FlyRos(){
        super(ID,info);
        setExhaust(true);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new IntangiblePlayerPower(abstractPlayer, 1), 1));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 2), 2));
    }
}
