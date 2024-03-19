package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class TargetHand extends BaseRelic {
    private static final String NAME = "TargetHand"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public TargetHand() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, null, new StrengthPower(AbstractDungeon.player, AbstractDungeon.getCurrRoom().monsters.monsters.size() * 2)));
    }
}
