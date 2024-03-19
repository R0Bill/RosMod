package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.attack.QuickAttack;
import rosmod.character.Rosmontis;
import rosmod.powers.AttachPower;
import rosmod.util.CardStats;

public class AfterShock extends BaseCard {

    public static final String ID = makeID("AfterShock");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public AfterShock() {
        super(ID, info);
        setExhaust(true);
        this.cardsToPreview = new QuickAttack();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new AttachPower(abstractPlayer, this.upgraded ? 4 : 2)));
    }


}
