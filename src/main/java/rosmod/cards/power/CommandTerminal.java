package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.CommandTerminalPower;
import rosmod.util.CardStats;

public class CommandTerminal extends BaseCard {
    public static final String ID = makeID("CommandTerminal");

    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2
    );

    public CommandTerminal() {
        super(ID, info);
        setInnate(false, true);
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean powerExists = false;
        for (AbstractPower pow : abstractPlayer.powers) {
            if (pow.ID.equals("rosmontis:CommandTerminal")) {
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
            if (pow.ID.equals("rosmontis:CommandTerminal")) {
                powerExists = true;
                break;
            }
        }
        if (!powerExists)
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new CommandTerminalPower(abstractPlayer)));
    }

}
