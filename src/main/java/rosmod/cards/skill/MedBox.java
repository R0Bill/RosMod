package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class MedBox extends BaseCard {
    public static final String ID = makeID("MedBox");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -2
    );

    public MedBox() {
        super(ID, info);
        setExhaust(true);
        setMagic(7);
        this.isInAutoplay = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 修复：若经自动打出等非抽牌途径进入手牌被强制打出，空 use() 会白耗
        addToBot(new HealAction(p, p, this.magicNumber));
    }

    public boolean canUpgrade() {
        return false;
    }

    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        return false;
    }

    public void triggerWhenDrawn() {
        addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
        addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

}
