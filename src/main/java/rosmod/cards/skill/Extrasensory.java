package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.powers.BombPower;
import rosmod.powers.InstabilityPower;
import rosmod.util.CardStats;

public class Extrasensory extends BaseCard {
    public static final String ID = makeID("Extrasensory");
    private static final int BOMB_AMOUNT = 3;
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
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        InstabilityPower.shift(-2);
        BombPower b = (BombPower) p.getPower("rosmontis:BombPower");
        if (b == null) {
            // 未升级：一次性余震强化；升级：持续型（每回合-1）
            addToBot(new ApplyPowerAction(p, p, new BombPower(p, BOMB_AMOUNT, !this.upgraded)));
        } else if (b.IsOnce() && this.upgraded) {
            // 修复：原实现只移除一次性余震而不补持续型，与升级描述不符
            addToBot(new RemoveSpecificPowerAction(p, p, b));
            addToBot(new ApplyPowerAction(p, p, new BombPower(p, BOMB_AMOUNT, false)));
        } else {
            // 修复：原 UPGAmount(9) 笔误
            b.UPGAmount(BOMB_AMOUNT);
            b.flash();
        }
    }
}
