package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.actions.ReminiscenceAction;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Reminiscence extends BaseCard {

    public static final String ID = makeID("Reminiscence");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.SELF,
            1
    );

    public Reminiscence() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 过滤自身，防止无限
        CardGroup options = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : p.exhaustPile.group) {
            if (!c.cardID.equals(ID)) {
                options.addToTop(c);
            }
        }
        if (options.isEmpty()) {
            addToBot(new DrawCardAction(p, 1));
        } else {
            addToBot(new ReminiscenceAction(options));
        }
        if (this.upgraded) {
            addToBot(new DrawCardAction(p, 1));
        }
    }
}
