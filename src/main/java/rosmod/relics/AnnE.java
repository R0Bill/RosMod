package rosmod.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import rosmod.character.MyCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static rosmod.BasicMod.makeID;

public class AnnE extends BaseRelic {
    private static final String NAME = "AnnE"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public AnnE() {
        super(ID, NAME, MyCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.ATTACK){
            flash();
            addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }
}
