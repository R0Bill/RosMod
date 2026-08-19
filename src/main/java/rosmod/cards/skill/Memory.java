package rosmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class Memory extends BaseCard {
    public static final String ID = makeID("Memory");
    private static final int RECALL_DIVISOR = 5;
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public Memory(){
        super(ID,info);
        setSelfRetain(false,true);
        // 回忆：消耗堆每有 5 张牌，额外获得 1 点能量
        setCustomVar("RecallEnergy", VariableType.MAGIC, 0, 0,
                (m, baseVal) -> (AbstractDungeon.player != null) ? AbstractDungeon.player.exhaustPile.size() / RECALL_DIVISOR : 0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int bonus = customVar("RecallEnergy");
        int total = 2 + bonus;
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, total), total));
    }

}
