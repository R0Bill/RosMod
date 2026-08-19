package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.InstabilityPower;
import rosmod.util.CardStats;

public class PsyStorm extends BaseCard {
    public static final String ID = makeID("PsyStorm");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            -1
    );
    public PsyStorm(){
        super(ID,info);
        this.isMultiDamage = true;
        setExhaust(true);
        setSelfRetain(false,true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster){
        InstabilityPower.shift(3);
        p.state.setAnimation(0,"Skill_3_Begin",false);
        p.state.addAnimation(0,"Skill_3_Loop",true,0.3f);
        // 每段 3-6-12-24...（3×2^n）真实伤害，段数 = 1 + 消耗能量
        for(int i = 0 ; i < 1 + this.energyOnUse ; i++){
            int tempDamage = 3 << i;
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(tempDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        p.state.addAnimation(0,"Skill_3_End",false,(1+this.energyOnUse)*0.2f);
        p.state.addAnimation(0,"Idle",true,0.3f);
        // X费卡按原版惯例自行扣费（Whirlwind 同款模式）
        if(!this.freeToPlayOnce)
           p.energy.use(this.energyOnUse);
    }
}
