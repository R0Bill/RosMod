package rosmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import rosmod.character.MyCharacter;

import static rosmod.BasicMod.makeID;

public class JudgeCount extends BaseRelic {
    private static final String NAME = "JudgeCount"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public JudgeCount() {
        super(ID, NAME, MyCharacter.Enums.CARD_COLOR, RARITY, SOUND);
        this.counter = 2;
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, (AbstractPower) new DexterityPower((AbstractCreature) AbstractDungeon.player, this.counter), this.counter));
    }
}