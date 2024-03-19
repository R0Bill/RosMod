package rosmod.cards.attack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.other.OptionDefend;
import rosmod.cards.other.OptionStrike;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

import java.util.ArrayList;

public class Inheritors extends BaseCard {

    public static final String ID = makeID("Inheritors");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            AbstractCard.CardRarity.SPECIAL,
            AbstractCard.CardTarget.NONE,
            0
    );

    public Inheritors() {
        super(ID, info);
        this.cardsToPreview = new OptionStrike();
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }

    public void renderCardPreview(SpriteBatch sb) {
        if (AbstractDungeon.player == null || !AbstractDungeon.player.isDraggingCard) {
            float tmpScale = this.drawScale * 0.6F;
            if (this.current_x > (float) Settings.WIDTH * 0.75F) {
                this.cardsToPreview.current_x = this.current_x + (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.6f + 16.0F) * this.drawScale;
            } else {
                this.cardsToPreview.current_x = this.current_x - (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.6f + 16.0F) * this.drawScale;
            }

            this.cardsToPreview.current_y = this.current_y + (IMG_HEIGHT / 2.0F - IMG_HEIGHT / 2.0F * 0.6F) * this.drawScale;
            this.cardsToPreview.drawScale = tmpScale;
            this.cardsToPreview.render(sb);
        }
        AbstractCard previewS = new OptionDefend();
        if (AbstractDungeon.player == null || AbstractDungeon.player.isDraggingCard) {
            float tmpScale = this.drawScale * 0.6F;
            if (this.current_x > (float) Settings.WIDTH * 0.75F) {
                previewS.current_x = this.current_x + (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.6F + 16.0F) * this.drawScale;
            } else {
                previewS.current_x = this.current_x - (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.6F + 16.0F) * this.drawScale;
            }
            previewS.current_y = this.current_y + (IMG_HEIGHT / 2.0F + IMG_HEIGHT / 2.0F * 0.6F + 16.0F) * this.drawScale;
            previewS.drawScale = tmpScale;
            previewS.render(sb);
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new OptionDefend());
        list.add(new OptionStrike());
        addToBot(new ChooseOneAction(list));
    }
}
