package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Intimidate extends BaseCard {
    public static final String ID = makeID("Intimidate");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            3
    );

    public Intimidate() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, abstractPlayer, new VulnerablePower(mo, 99, false), 99, true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, abstractPlayer, new WeakPower(mo, 99, false), 99, true));
        }
    }
}
