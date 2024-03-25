package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.Skill1Power;
import rosmod.util.CardStats;

public class Skill1 extends BaseCard {
    public static final String ID = makeID("Skill1");

    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    public Skill1() {
        super(ID, info);
        setInnate(false,true);
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean powerExists = false;
        for (AbstractPower pow : AbstractDungeon.player.powers) {
            if (pow.ID.equals("rosmontis:Skill1Power") || pow.ID.equals("rosmontis:Skill2Power") || pow.ID.equals("rosmontis:Skill3Power")) {
                powerExists = true;
                break;
            }
        }
        return !powerExists;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean powerExists = false;
        for (AbstractPower pow : AbstractDungeon.player.powers) {
            if (pow.ID.equals("rosmontis:Skill1Power") || pow.ID.equals("rosmontis:Skill2Power") || pow.ID.equals("rosmontis:Skill3Power")) {
                powerExists = true;
                break;
            }
        }
        if (!powerExists) {
            if (!this.upgraded)
                addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new Skill1Power(abstractPlayer, 3)));
            else
                addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new Skill1Power(abstractPlayer, 2)));
        }
    }
}
