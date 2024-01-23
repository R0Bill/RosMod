package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.MyCharacter;
import rosmod.util.CardStats;

public class HeavyAttackRos extends BaseCard {
    public static final String ID = makeID("HeavyAttackRos"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 5;

    public HeavyAttackRos(){
        super(ID,info);
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CardTags.STRIKE); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        p.state.setAnimation(0,"Skill_2",false);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new GainBlockAction(p,p,damage));
        p.state.addAnimation(0,"Idle",true,3.15f);
    }
}
