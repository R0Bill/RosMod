package rosmod.cards.attack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

import java.util.function.Consumer;

public class StealBlock extends BaseCard {
    public static final String ID = makeID("StealBlock");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public StealBlock() {
        super(ID, info);
        tags.add(CardTags.STRIKE);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        setDamage(Math.max(this.upgraded ? abstractMonster.currentBlock * 2 : abstractMonster.currentBlock, this.upgraded ? 10 : 5));
        Consumer<Integer> CB = (Integer num) -> addToTop(new GainBlockAction(abstractPlayer, abstractPlayer, num));
        addToBot(new DamageCallbackAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY, CB));
    }
}
