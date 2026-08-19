package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Sleepy extends BaseCard {
    public static final String ID = makeID("Sleepy");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public Sleepy() {
        super(ID, info);
        setEthereal(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 重做：原为不可打出的「诅咒式」孤儿卡。现为0费延迟能量，虚无保留紧张感
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, this.upgraded ? 2 : 1), this.upgraded ? 2 : 1));
    }

}
