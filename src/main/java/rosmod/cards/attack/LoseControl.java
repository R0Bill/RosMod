package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class LoseControl extends BaseCard {
    public static final String ID = makeID("LoseControl");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.ENEMY,
            1
    );

    public LoseControl() {
        super(ID, info);
        tags.add(AbstractCard.CardTags.STRIKE);
        setDamage(12);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new LoseHPAction(abstractPlayer, abstractPlayer, 2));
        int temp = 12;
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null) {
            temp += strength.amount * 2;
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, temp, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (strength != null) {
            temp += strength.amount;
        }
        if (temp >= 16 && !this.upgraded) {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 1)));
        } else if (temp >= 14 && this.upgraded) {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 2)));
        }
    }
}
