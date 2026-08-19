package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

/** 旧日记：每当你消耗一张牌，获得 2 点格挡。 */
public class OldDiary extends BaseRelic {

    private static final String NAME = "OldDiary";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.CLINK;

    public OldDiary() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        flash();
        addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 2));
    }
}
