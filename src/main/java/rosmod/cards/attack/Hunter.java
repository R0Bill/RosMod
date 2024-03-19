package rosmod.cards.attack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

import java.util.function.Consumer;

public class Hunter extends BaseCard {
    public static final String ID = makeID("Hunter");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 6;

    public Hunter() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Consumer<Integer> CB = (Integer num) -> {
            addToTop(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, num / 2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        };
        addToBot(new DamageCallbackAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT, CB));
//        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));

    }
}
