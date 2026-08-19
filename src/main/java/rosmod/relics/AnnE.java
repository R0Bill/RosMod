package rosmod.relics;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import rosmod.character.Rosmontis;
import rosmod.powers.BombPower;

import static rosmod.BasicMod.makeID;

public class AnnE extends BaseRelic {
    private static final String NAME = "AnnE";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public AnnE() {
        super(ID, NAME, Rosmontis.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.ATTACK){
            flash();
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDead) {
                    int dma = (AbstractDungeon.player.hasPower("rosmontis:Skill2Power") ? (3 + (AbstractDungeon.player.currentBlock / 4)) : 3)
                            + (AbstractDungeon.player.hasPower("rosmontis:BombPower") ? 3 : 0)
                            + (AbstractDungeon.player.hasPower("rosmontis:RampagePower") ? 2 : 0);
                    addToBot(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, dma, DamageInfo.DamageType.THORNS)));
                    if (AbstractDungeon.player.hasPower("rosmontis:BombPower")) {
                        BombPower B = (BombPower) AbstractDungeon.player.getPower("rosmontis:BombPower");
                        B.trigger();
                    }
                }
            }
//            addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(AbstractDungeon.player.hasPower("rosmontis:Skill2") ? (3 + (AbstractDungeon.player.currentBlock / 4)) : 3, false), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        }
    }

    /** 悬浮剑被御剑时触发（由 AbstractRosBlade.playChannelSFX 的遗物遍历调用）。 */
    @Override
    public void onSwordChanneled(AbstractOrb orb) {
        flash();
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDead) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,
                        new DamageInfo(AbstractDungeon.player, 3, DamageInfo.DamageType.THORNS)));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }
}
