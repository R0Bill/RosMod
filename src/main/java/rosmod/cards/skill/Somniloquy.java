package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Somniloquy extends BaseCard {

    public static final String ID = makeID("Somniloquy");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1
    );

    public Somniloquy() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(0);
        // 格挡 = 消耗堆（记忆）中的牌数，实时计算与变色显示
        setCustomVar("RecallBlock", VariableType.BLOCK, 0, 0,
                (m, baseVal) -> (AbstractDungeon.player != null) ? AbstractDungeon.player.exhaustPile.size() : 0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, customVar("RecallBlock")));
    }
}
