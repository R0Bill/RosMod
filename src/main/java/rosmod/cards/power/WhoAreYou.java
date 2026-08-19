package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.InstabilityPower;
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
        // 重做：原「自我混乱」无收益且被 Snecko 完全取代。
        // 现为失控体系启动器：身份错乱 → 精神失控 + 抽牌
        Random r = new Random();
        addToBot(new TalkAction(true, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[r.nextInt(3)], 2.5f, 2.5f));
        addToBot(new DrawCardAction(abstractPlayer, 2));
        InstabilityPower.shift(2);
    }
}
