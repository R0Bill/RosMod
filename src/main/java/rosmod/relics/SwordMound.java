package rosmod.relics;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.character.Rosmontis;
import rosmod.orbs.AssaultBlade;
import rosmod.orbs.BarrierBlade;

import static rosmod.BasicMod.makeID;

/** 剑冢：战斗开始时获得 2 点剑槽，御剑壁剑与突剑。 */
public class SwordMound extends BaseRelic {

    private static final String NAME = "SwordMound";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.CLINK;

    public SwordMound() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.player.increaseMaxOrbSlots(2, true);
        addToBot(new ChannelAction(new BarrierBlade()));
        addToBot(new ChannelAction(new AssaultBlade()));
    }
}
