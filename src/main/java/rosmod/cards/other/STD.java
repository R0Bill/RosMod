package rosmod.cards.other;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class STD extends BaseCard {
    public static final String ID = makeID("STD");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.SPECIAL,
            AbstractCard.CardTarget.NONE,
            -2
    );

    public STD() {
        super(ID, info);
    }

    public void onChoseThisOption() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player.hasPower(StrengthPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(player, player, new DexterityPower(player, player.getPower(StrengthPower.POWER_ID).amount)));
            addToBot(new RemoveSpecificPowerAction(player, player, player.getPower(StrengthPower.POWER_ID)));
        }
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
