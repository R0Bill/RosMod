package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class ShieldStrike extends BaseCard {
    public static final String ID = makeID("ShieldStrike");

    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public ShieldStrike() {
        super(ID, info);
        tags.add(CardTags.STRIKE);
        setBlock(BLOCK,UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        int temp = 0;
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, block));
        temp += abstractPlayer.currentBlock;
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, temp, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }
}
