package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class StealBlock extends BaseCard {
    public static final String ID = makeID("StealBlock");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int BASE = 5;
    private static final int UPG_BASE = 5;

    public StealBlock() {
        super(ID, info);
        tags.add(CardTags.STRIKE);
        setCostUpgrade(0);
        // 伤害 = 目标格挡 + 5(升10)，随目标格挡实时显示
        setCustomVar("StealDamage", VariableType.DAMAGE, BASE, UPG_BASE,
                (m, baseVal) -> baseVal + (m != null ? m.currentBlock : 0));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 修复：原实现伤害=敌格挡会被其格挡全额吸收，回调拿0格挡，「偷格挡」名不副实。
        // 现在伤害=敌格挡+5（保证突破护盾），获得等同于敌当前格挡的格挡。
        int steal = m.currentBlock;
        int dmg = steal + (this.upgraded ? BASE + UPG_BASE : BASE);
        addToBot(new DamageAction(m, new DamageInfo(p, dmg, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (steal > 0)
            addToBot(new GainBlockAction(p, p, steal));
    }
}
