package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class FallOut extends BaseCard {

    public static final String ID = makeID("FallOut");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            3
    );

    public FallOut() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(2);
        setDamage(16, 4);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean i = true;
        int temp = damage;
//        abstractPlayer.state.setAnimation(0, "Skill_2", false);
        while (i) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!i)
                    break;
                if (temp <= 0)
                    i = false;
                if (!mo.isDead) {
                    addToBot(new DamageAction(mo, new DamageInfo(abstractPlayer, temp, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                    temp -= 4;
                }
            }
//            abstractPlayer.state.addAnimation(0, "Idle", true, 3.15f);
        }
    }

}
