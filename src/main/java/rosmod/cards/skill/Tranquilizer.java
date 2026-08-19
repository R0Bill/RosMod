package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.RampagePower;
import rosmod.util.CardStats;

public class Tranquilizer extends BaseCard {

    public static final String ID = makeID("Tranquilizer");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    private static final int BLOCK = 8;

    public Tranquilizer() {
        super(ID, info);
        setBlock(BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 先移除暴走，再获得格挡（队列顺序保证 modifyBlock 已恢复）
        if (p.hasPower(RampagePower.ID)) {
            addToBot(new RemoveSpecificPowerAction(p, p, RampagePower.ID));
        }
        addToBot(new GainBlockAction(p, p, this.block));
        if (this.upgraded) {
            addToBot(new DrawCardAction(p, 1));
        }
    }
}
