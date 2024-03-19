package rosmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.cards.attack.QuickAttack;

public class AddAttackAction extends AbstractGameAction {
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 2).type == AbstractCard.CardType.ATTACK) {
            addToBot(new MakeTempCardInHandAction(new QuickAttack(), 2));
        }
        this.isDone = true;
    }
}
