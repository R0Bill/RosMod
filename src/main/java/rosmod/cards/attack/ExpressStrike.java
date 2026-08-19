package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AbstractRosBlade;
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

    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;
    private static final int BASE_HITS = 4;

    public ExpressStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        // 段数 = 4 + 悬浮剑数，实时显示
        setCustomVar("ExpressHits", VariableType.MAGIC, BASE_HITS, 0,
                (m, baseVal) -> baseVal + AbstractRosBlade.countBlades());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int hits = BASE_HITS + AbstractRosBlade.countBlades();
        for (int i = 0; i < hits; i++)
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

}
