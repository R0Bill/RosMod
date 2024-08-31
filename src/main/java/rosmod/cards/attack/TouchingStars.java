package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.util.CardStats;

public class TouchingStars extends BaseCard {
    public static final String ID = makeID("TouchingStars");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            8
    );

    private boolean firstTurn = true;

    public TouchingStars() {
        super(ID, info);
        setMagic(7);
        setDamage(999);
        setSelfRetain(true);
        setExhaust(true);
        setInnate(false, true);
        this.dontTriggerOnUseCard = true;
    }

    public void setCostForTurn(int amt) {
        if (amt == this.magicNumber - 1) {
            this.costForTurn = amt;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.energy.energy >= this.energyOnUse) {
            this.glowColor = com.badlogic.gdx.graphics.Color.valueOf("ddeff0");
        } else
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

    }
//
//    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        boolean powerExists = false;
//        for (AbstractPower pow : AbstractDungeon.player.powers) {
//            if (pow.ID.equals("Skill1Power") || pow.ID.equals("Skill2Power") || pow.ID.equals("Skill3Power")) {
//                powerExists = true;
//                break;
//            }
//        }
//        if (!powerExists)
//            return true;
//        else
//            return false;
//    }

    public void atTurnStart() {
        if (this.firstTurn) {
            this.firstTurn = false;
        } else {
            if (this.costForTurn >= 0) {
                magicNumber--;
                addToBot(new ReduceCostAction(this));
            }
        }
    }
    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.firstTurn)
            this.firstTurn = false;
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = this.makeCopy();

        for (int i = 0; i < this.timesUpgraded; ++i) {
            card.upgrade();
        }

        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.freeToPlayOnce = false;
        return card;
    }
}
