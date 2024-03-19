package rosmod.cards.skill;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.attack.Inheritors;
import rosmod.cards.attack.Outriders;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class AMOS extends BaseCard {

    public static final String ID = makeID("AMOS");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public AMOS() {
        super(ID, info);
        setCostUpgrade(0);
        setExhaust(true);
        this.cardsToPreview = new Outriders();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new MakeTempCardInDrawPileAction(new Inheritors(), 2, true, false, false));
        addToBot(new MakeTempCardInDrawPileAction(new Outriders(), 2, true, false, false));
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
        AbstractCard previewS = new Inheritors();
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

}
