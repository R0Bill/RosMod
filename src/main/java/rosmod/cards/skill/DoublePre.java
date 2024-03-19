package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.attack.QuickAttack;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class DoublePre extends BaseCard {
    public static final String ID = makeID("DoublePre");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public DoublePre() {
        super(ID, info);
        setCostUpgrade(0);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new Summon(), 1));
        addToBot(new MakeTempCardInHandAction(new QuickAttack(), 1));
    }
}
