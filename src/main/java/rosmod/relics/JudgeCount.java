package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.character.Rosmontis;

import static rosmod.BasicMod.makeID;

public class JudgeCount extends BaseRelic {
    private static final String NAME = "JudgeCount";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SPECIAL;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public JudgeCount() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
        this.counter = 1;
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.counter / 2)));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.counter / 2)));
    }

    public String getUpdatedDescription() {
//        if(AbstractDungeon.isPlayerInDungeon()==true)
//            return this.DESCRIPTIONS[0]+this.counter+this.DESCRIPTIONS[1]+this.counter/2+this.DESCRIPTIONS[2];
//        else
        return this.DESCRIPTIONS[3];
    }
}
