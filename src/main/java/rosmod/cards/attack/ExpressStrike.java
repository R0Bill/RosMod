package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class ExpressStrike extends BaseCard {

    public static final String ID = makeID("ExpressStrike");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 1;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 4;

    public ExpressStrike() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 1; i <= this.magicNumber; i++)
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

}
