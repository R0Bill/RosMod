package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class TargetHand extends BaseRelic {
    private static final String NAME = "TargetHand";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;


    public TargetHand() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, null, new StrengthPower(AbstractDungeon.player, AbstractDungeon.getCurrRoom().monsters.monsters.size() * 2)));
    }
}
