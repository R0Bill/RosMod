package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rosmod.character.Rosmontis;
import rosmod.powers.EmergencyPower;

import static rosmod.BasicMod.makeID;

public class BadGuy extends BaseRelic {

    private static final String NAME = "BadGuy";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private final boolean CANUSE = true;

    public BadGuy() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }
    @Override
    public void atBattleStart() {
        flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EmergencyPower(AbstractDungeon.player)));
    }
}
