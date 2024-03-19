package rosmod.cards.skill;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Backpack extends BaseCard {

    public static final String ID = makeID("Backpack");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0
    );

    public Backpack() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(abstractPlayer, BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size()));
        if (this.upgraded)
            addToBot(new GainEnergyAction(1));
    }
}
