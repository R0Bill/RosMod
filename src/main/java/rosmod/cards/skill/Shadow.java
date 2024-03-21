package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.FearPower;
import rosmod.util.CardStats;

public class Shadow extends BaseCard {
    public static final String ID = makeID("Shadow");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    public Shadow() {
        super(ID, info);
        setExhaust(true);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, abstractPlayer, new FearPower(mo, this.upgraded ? 7 : 5)));
        }
    }

}
