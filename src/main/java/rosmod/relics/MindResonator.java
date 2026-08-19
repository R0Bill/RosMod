package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.character.Rosmontis;
import rosmod.powers.InstabilityPower;

import static rosmod.BasicMod.makeID;

/** 精神共鸣器：战斗开始时获得 3 点失控。 */
public class MindResonator extends BaseRelic {

    private static final String NAME = "MindResonator";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.COMMON;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.CLINK;

    public MindResonator() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new InstabilityPower(AbstractDungeon.player, 3), 3));
    }
}
