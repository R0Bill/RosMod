package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

import java.util.Random;

public class WhoAreYou extends BaseCard {
    public static final String ID = makeID("WhoAreYou");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public WhoAreYou() {
        super(ID, info);
        setInnate(true);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        logger.warn(CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[1]);
        Random r = new Random();
        addToBot(new TalkAction(true, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[r.nextInt(3)], 2.5f, 2.5f));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ConfusionPower(abstractPlayer)));
    }
}
