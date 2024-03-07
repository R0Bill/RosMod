package rosmod.cards.power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
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
            Rosmontis.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Skill1() {
        super(ID, info);
        setInnate(false,true);
        setCostUpgrade(0);
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
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new Skill1Power((AbstractCreature) abstractPlayer, 3)));
            else
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new Skill1Power((AbstractCreature) abstractPlayer, 2)));
        }
    }
}
