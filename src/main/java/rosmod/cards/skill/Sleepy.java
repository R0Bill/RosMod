package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
            -2
    );

    public Sleepy() {
        super(ID, info);
        setEthereal(true);
        setExhaust(true);
    }

    public boolean canUpgrade() {
        return false;
    }

    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        return false;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    public void triggerOnExhaust() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EnergizedPower(AbstractDungeon.player, 1)));
    }


}
