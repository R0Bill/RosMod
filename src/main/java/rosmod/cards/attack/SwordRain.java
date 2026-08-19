package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import rosmod.actions.EvokeAllSwordsAction;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.orbs.AbstractRosBlade;
import rosmod.util.CardStats;

public class SwordRain extends BaseCard {

    public static final String ID = makeID("SwordRain");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.ALL_ENEMY,
            2
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public SwordRain() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractOrb orb : p.orbs) {
            if (orb instanceof AbstractRosBlade)
                count++;
        }
        if (count > 0) {
            // 每激发一把，全体真实伤害
            addToBot(new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(count * this.magicNumber, true),
                    DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            addToBot(new EvokeAllSwordsAction(true));
        }
    }
}
