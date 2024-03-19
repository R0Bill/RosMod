package rosmod.cards.other;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.cards.BaseCard;
import rosmod.util.CardStats;

public class Infected extends BaseCard {
    public static final String ID = makeID("Infected");
    private static final CardStats info = new CardStats(
            CardColor.CURSE,
            CardType.CURSE,
            CardRarity.CURSE,
            CardTarget.NONE,
            -2
    );

    public Infected() {
        super(ID, info);
        setSelfRetain(true);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        return false;
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 1));
    }

    public void triggerWhenDrawn() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }

    public boolean canUpgrade() {
        return false;
    }
}
