package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Summon extends BaseCard {
    public static final String ID = makeID("Summon");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    public Summon() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, AbstractDungeon.player, new PlatedArmorPower(abstractPlayer, this.upgraded ? 4 : 2)));
    }
}
