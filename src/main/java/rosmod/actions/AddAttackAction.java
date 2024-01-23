package rosmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import rosmod.cards.attack.QuickAttack;

public class AddAttackAction extends AbstractGameAction {
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 2)).type == AbstractCard.CardType.ATTACK) {
            addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new QuickAttack(), 2));
        }
        this.isDone = true;
    }
}
