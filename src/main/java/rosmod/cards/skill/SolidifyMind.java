package rosmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class SolidifyMind extends BaseCard {
    public static final String ID = makeID("SolidifyMind");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public SolidifyMind() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
        int a = this.upgraded ? abstractPlayer.currentBlock / 2 : abstractPlayer.currentBlock / 3;
        addToBot(new AddTemporaryHPAction(abstractPlayer, abstractPlayer, a));
        addToBot(new LoseBlockAction(abstractPlayer, abstractPlayer, a));
    }
}
