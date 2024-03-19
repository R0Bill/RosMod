package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.FearPower;
import rosmod.util.CardStats;

public class FaceYourSin extends BaseCard {

    public static final String ID = makeID("FaceYourSin");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public FaceYourSin() {
        super(ID, info);
        setCostUpgrade(0);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(abstractMonster.getIntentBaseDmg() >= 0 && !abstractMonster.isDead ? new DamageAction(abstractMonster, new DamageInfo(abstractMonster, abstractMonster.getIntentBaseDmg(), DamageInfo.DamageType.NORMAL)) : new ApplyPowerAction(abstractMonster, abstractPlayer, new FearPower(abstractMonster, this.upgraded ? 4 : 2)));
//        abstractMonster.setMove(cardStrings.EXTENDED_DESCRIPTION[0],(byte) 0, AbstractMonster.Intent.ATTACK,99,1,false);
    }

}
