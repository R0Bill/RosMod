package rosmod.cards.other;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class OptionAttack extends BaseCard {

    public static final String ID = makeID("OptionAttack");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            AbstractCard.CardRarity.SPECIAL,
            AbstractCard.CardTarget.NONE,
            -2
    );

    public OptionAttack() {
        super(ID, info);
        setDamage(10);
    }

    public void onChoseThisOption() {
        AbstractPlayer player = AbstractDungeon.player;
        AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        addToBot(new DamageAction(monster, new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL)));
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
