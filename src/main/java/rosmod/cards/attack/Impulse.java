package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Impulse extends BaseCard {
    public static final String ID = makeID("Impulse");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 15;

    public Impulse() {
        super(ID, info);
        setDamage(DAMAGE);
        setExhaust(true);
        tags.add(CardTags.STRIKE); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster.currentHealth + abstractMonster.currentBlock <= damage + (abstractPlayer.hasPower("rosmontis:Skill2Power") ? (3 + (abstractPlayer.currentBlock / 4)) : 3) + (abstractMonster.hasPower("rosmontis:BombPower") ? 3 : 0) && !abstractMonster.isDead)
            addToBot(new MakeTempCardInHandAction(this, 1));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }
}
