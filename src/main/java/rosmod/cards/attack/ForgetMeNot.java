package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class ForgetMeNot extends BaseCard {
    public static final String ID = makeID("ForgetMeNot");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.ENEMY,
            3
    );

    public ForgetMeNot() {
        super(ID, info);
        setExhaust(true);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.upgraded ? (4 * (abstractMonster.maxHealth - abstractMonster.currentHealth) / 10) : (3 * (abstractMonster.maxHealth - abstractMonster.currentHealth) / 10), DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

}
