package rosmod.cards.other;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class OptionDefend extends BaseCard {

    public static final String ID = makeID("OptionDefend");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.SPECIAL,
            AbstractCard.CardTarget.NONE,
            -2
    );

    public OptionDefend() {
        super(ID, info);
    }

    public void onChoseThisOption() {
        AbstractPlayer player = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(player, player, new MetallicizePower(player, 4)));
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
