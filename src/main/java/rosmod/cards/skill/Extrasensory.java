package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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
        if (p.hasPower("rosmontis:BombPower")) {
            BombPower B = (BombPower) p.getPower("rosmontis:BombPower");
            if (B.IsOnce() && this.upgraded) {
                addToBot(new RemoveSpecificPowerAction(p, p, p.getPower("rosmontis:BombPower")));
            } else {
                B.UPGAmount(9);
            }

        } else {
            addToBot(new ApplyPowerAction(p, p, new BombPower(p, 1, !this.upgraded)));
        }
    }
}
