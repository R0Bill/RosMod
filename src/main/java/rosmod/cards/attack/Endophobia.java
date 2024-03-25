package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.cards.other.Infected;
import rosmod.character.Rosmontis;
import rosmod.powers.FearPower;
import rosmod.util.CardStats;

public class Endophobia extends BaseCard {

    public static final String ID = makeID("Endophobia");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    public Endophobia() {
        super(ID, info);
        setDamage(6, 5);
        this.cardsToPreview = new Infected();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new MakeTempCardInDrawPileAction(new Infected(), 1, true, false, false));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
            addToBot(new ApplyPowerAction(mo, abstractPlayer, new FearPower(mo, this.upgraded ? 3 : 2)));
        addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(damage), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

}
