package rosmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rosmod.cards.BaseCard;
import rosmod.character.Rosmontis;
import rosmod.relics.JudgeCount;
import rosmod.util.CardStats;

public class Judgement extends BaseCard {
    public static final String ID = makeID("Judgement");
    private static final CardStats info = new CardStats(
            Rosmontis.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 5;

    public Judgement() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setExhaust(true);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {//斩杀加敏捷
        boolean temp = abstractMonster.currentHealth + abstractMonster.currentBlock <= damage + 3;
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (temp) {
            if (!abstractPlayer.hasRelic(JudgeCount.ID)) {
                JudgeCount judgeCount = new JudgeCount();
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(((float) Settings.WIDTH / 2), ((float) Settings.HEIGHT / 2), judgeCount);
                CardCrawlGame.screenShake.mildRumble(5.0F);
                AbstractRelic jg = AbstractDungeon.player.getRelic(JudgeCount.ID);
                jg.counter += 1;

            } else {
                AbstractRelic jg = AbstractDungeon.player.getRelic(JudgeCount.ID);
                jg.flash();
                jg.counter += 1;
            }
        }
    }

}
