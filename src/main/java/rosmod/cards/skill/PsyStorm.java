package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.MyCharacter;
import rosmod.util.CardStats;

public class PsyStorm extends BaseCard {
    public static final String ID = makeID("PsyStorm");
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    public PsyStorm(){
        super(ID,info);
        this.isMultiDamage = true;
        tags.add(CardTags.STRIKE);
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
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(tempDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        abstractPlayer.state.addAnimation(0,"Skill_3_End",false,(1+this.energyOnUse)*0.2f);
        abstractPlayer.state.addAnimation(0,"Idle",true,0.3f);
        if(!this.freeToPlayOnce)
           abstractPlayer.energy.use(this.energyOnUse);
    }
}
