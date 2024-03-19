package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Extrasensory extends BaseCard {
    public static final String ID = makeID("Extrasensory");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0
    );

    public Extrasensory() {
        super(ID, info);
        setExhaust(true);
        this.cardsToPreview = new Summon();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int monum = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
            if (!mo.isDead)
                monum++;
        if (this.upgraded) {
            addToBot(new MakeTempCardInHandAction(new Summon(), monum));
        }
        addToBot(new MakeTempCardInHandAction(new Summon(), monum));
    }
}
