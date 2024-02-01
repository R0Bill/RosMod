package rosmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class TheHug extends BaseRelic {
    private static final String NAME = "TheHug"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private final float MODIFIER_AMT = 0.3F;

    public TheHug() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (m.currentHealth > (int) (m.maxHealth * (1.0F - this.MODIFIER_AMT))) {
                m.currentHealth = (int) (m.maxHealth * (1.0F - this.MODIFIER_AMT));
                m.healthBarUpdatedEvent();
            }
        }
        addToTop((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
    }
}
