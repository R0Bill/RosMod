package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.Skill2Power;
import rosmod.util.CardStats;

public class Skill2 extends BaseCard {
    public static final String ID = makeID("Skill2");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            2
    );

    public Skill2() {
        super(ID, info);
        setInnate(false, true);
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
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new Skill2Power(abstractPlayer)));
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new BarricadePower(abstractPlayer)));
        }
    }
}
