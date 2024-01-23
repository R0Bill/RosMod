package rosmod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class Skill2Power extends AbstractPower {
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Skill2Power");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Skill2Power(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Skill2Power";
        this.owner = owner;
        this.amount=-1;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.loadRegion("doubleTap");
    }
    @Override
    public float atDamageGive(float d, DamageInfo.DamageType type){
        float temp = (float) AbstractDungeon.player.currentBlock /2;
        if(type == DamageInfo.DamageType.NORMAL){
            temp += d;
            return temp;
        }
        else
            return d;
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
