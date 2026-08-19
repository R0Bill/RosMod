package rosmod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

/**
 * 剑穗：你的悬浮剑被激发时，同时触发其被动。
 * 逻辑在 AbstractRosBlade.evoke() 内检查持有，此处仅注册。
 */
public class SwordTassel extends BaseRelic {

    private static final String NAME = "SwordTassel";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.CLINK;

    public SwordTassel() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }
}
