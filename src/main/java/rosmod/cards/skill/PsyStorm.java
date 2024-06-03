package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
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
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        abstractPlayer.state.setAnimation(0,"Skill_3_Begin",false);
        abstractPlayer.state.addAnimation(0,"Skill_3_Loop",true,0.3f);
        for(int i = 0 ; i < 1 + this.energyOnUse ; i++){
            int tempDamage = 2;
            for(int j = 0; j<i;j++){
                int Ftemp = tempDamage;
                tempDamage = Ftemp * 2;
            }
//            for(AbstractMonster mo: AbstractDungeon.getCurrRoom().monsters.monsters)
//                addToBot(new DamageAction(mo,new DamageInfo(mo,(tempDamage/monum)+1),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

            addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(tempDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        abstractPlayer.state.addAnimation(0,"Skill_3_End",false,(1+this.energyOnUse)*0.2f);
        abstractPlayer.state.addAnimation(0,"Idle",true,0.3f);
        if(!this.freeToPlayOnce)
           abstractPlayer.energy.use(this.energyOnUse);
    }
}
