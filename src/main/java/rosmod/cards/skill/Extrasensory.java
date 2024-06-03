package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.BombPower;
import rosmod.util.CardStats;

public class Extrasensory extends BaseCard {
    public static final String ID = makeID("Extrasensory");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0
    );

    public Extrasensory() {
        super(ID, info);
        setExhaust(true);
        setMagic(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDead) {
                if (mo.hasPower("rosmontis:BombPower")) {
                    mo.getPower("rosmontis:BombPower").amount += this.magicNumber;
                } else {
                    addToBot(new ApplyPowerAction(mo, p, new BombPower(mo, this.magicNumber, !this.upgraded)));
                }
            }
        }
    }
}
