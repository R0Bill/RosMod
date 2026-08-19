package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class ForgetMeNot extends BaseCard {
    public static final String ID = makeID("ForgetMeNot");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.ENEMY,
            2
    );

    private static final int FLOOR = 8;
    private static final int UPG_FLOOR = 4;
    private static final int RECALL_DIVISOR = 5;
    private static final int RECALL_BONUS = 3;
    private static final int UPG_RECALL_BONUS = 2;

    public ForgetMeNot() {
        super(ID, info);
        setExhaust(true);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int lost = abstractMonster.maxHealth - abstractMonster.currentHealth;
        int percent = this.upgraded ? 50 : 40;
        // 保底伤害，避免满血敌人时打0
        int base = Math.max(this.upgraded ? FLOOR + UPG_FLOOR : FLOOR, lost * percent / 100);
        // 回忆：消耗堆每有5张牌，伤害额外+3(升5)
        int recallBonus = (abstractPlayer.exhaustPile.size() / RECALL_DIVISOR) * (this.upgraded ? RECALL_BONUS + UPG_RECALL_BONUS : RECALL_BONUS);
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, base + recallBonus, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

}
