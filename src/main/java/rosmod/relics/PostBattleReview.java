package rosmod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

/** 战后复盘：本场战斗消耗至少 6 张牌时，战斗结束时回复 4 点生命。 */
public class PostBattleReview extends BaseRelic {

    private static final String NAME = "PostBattleReview";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.CLINK;

    private static final int THRESHOLD = 6;
    private static final int HEAL = 4;

    public PostBattleReview() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        this.counter++;
    }

    @Override
    public void onVictory() {
        if (this.counter >= THRESHOLD) {
            flash();
            AbstractDungeon.player.heal(HEAL);
        }
    }
}
