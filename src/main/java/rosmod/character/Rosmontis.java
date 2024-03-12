package rosmod.character;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import rosmod.cards.attack.StrikeRos;
import rosmod.cards.power.Skill1;
import rosmod.cards.skill.DefendRos;
import rosmod.relics.AnnE;
import rosmod.ui.SkinSelectScreen;

import java.util.ArrayList;

import static rosmod.BasicMod.characterPath;
import static rosmod.BasicMod.makeID;

public class Rosmontis extends CustomPlayer {//DES
    //Stats
    public static final int ENERGY_PER_TURN = 3;
    public static final int MAX_HP = 50;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;
    private static boolean ATTACK_BOOL = true;

    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };

    //Strings
    private static final String ID = makeID("RosID"); //This should match whatever you have in the CharacterStrings.json file
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    //Image file paths
    private static final String CORPSE = characterPath("corpse.png"); //Corpse is when you die.
    private static SkinSelectScreen.Skin SKIN = SkinSelectScreen.getSkin();
    private static final String ORB_VFX = "rosmod/images/ui/orb/vfx.png";
    private static final String[] ORB_URL = new String[]{"rosmod/images/ui/orb/layer5.png", "rosmod/images/ui/orb/layer4.png", "rosmod/images/ui/orb/layer3.png", "rosmod/images/ui/orb/layer2.png", "rosmod/images/ui/orb/layer1.png", "rosmod/images/ui/orb/layer6.png", "rosmod/images/ui/orb/layer5d.png", "rosmod/images/ui/orb/layer4d.png", "rosmod/images/ui/orb/layer3d.png", "rosmod/images/ui/orb/layer2d.png", "rosmod/images/ui/orb/layer1d.png"};

    public static class Enums {
        //These are used to identify your character, as well as your character's card color.
        //Library color is basically the same as card color, but you need both because that's how the game was made.
        @SpireEnum
        public static AbstractPlayer.PlayerClass ROSMONTIS;
        @SpireEnum(name = "RBLUE") // These two MUST match. Change it to something unique for your character.
        public static AbstractCard.CardColor CARD_COLOR;
        @SpireEnum(name = "RBLUE") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public Rosmontis() {
        super(NAMES[0], Enums.ROSMONTIS,
                new CustomEnergyOrb(ORB_URL, ORB_VFX, LAYER_SPEED), //Energy Orb
                new SpineAnimation(SKIN.charPath + ".atlas", SKIN.charPath + ".json", 1F)); //Animation

        initializeClass(null,
                SKIN.shoulder,
                SKIN.shoulder,
                CORPSE,
                getLoadout(),
                20.0F, -20.0F, 200.0F, 250.0F, //Character hitbox. x y position, then width and height.
                new EnergyManager(ENERGY_PER_TURN));

        refreshSkin();

        //Location for text bubbles. You can adjust it as necessary later. For most characters, these values are fine.
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);

        //loadAnimation(characterPath(SKIN.charPath+".atlas"),characterPath(SKIN.charPath+".json"),1F);
        this.stateData.setMix("Idle", "Die", 0.1F);
        this.state.setAnimation(0, "Start", false);
        this.state.setAnimation(0, "Idle", true);
    }

    public void refreshSkin() {
        SkinSelectScreen.Skin skin = SkinSelectScreen.getSkin();
        loadAnimation(skin.charPath + ".atlas", skin.charPath + ".json", 0.9F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        e.setTimeScale(1F);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        //List of IDs of cards for your starting deck.
        //If you want multiple of the same card, you have to add it multiple times.
        retVal.add(StrikeRos.ID);
        retVal.add(StrikeRos.ID);
        retVal.add(StrikeRos.ID);
        retVal.add(StrikeRos.ID);
        retVal.add(DefendRos.ID);
        retVal.add(DefendRos.ID);
        retVal.add(DefendRos.ID);
        retVal.add(DefendRos.ID);
        retVal.add(Skill1.ID);

        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        //IDs of starting relics. You can have multiple, but one is recommended.
//        retVal.add(AnnihilationEquipment.ID);
        retVal.add(AnnE.ID);
        return retVal;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        //This card is used for the Gremlin card matching game.
        //It should be a non-strike non-defend starter card, but it doesn't have to be.
        return new Strike_Red();
    }

    /*- Below this is methods that you should *probably* adjust, but don't have to. -*/

    @Override
    public int getAscensionMaxHPLoss() {
        return 4; //Max hp reduction at ascension 14+
    }
    @Override
    public void playDeathAnimation() {
        this.state.setAnimation(0, "Die", false);
    }
    @Override
    public void useFastAttackAnimation() {
        if (ATTACK_BOOL) {
            this.state.setAnimation(0, "Attack_A", false);
            ATTACK_BOOL = false;
        } else {
            this.state.setAnimation(0, "Attack_B", false);
            ATTACK_BOOL = true;
        }
        this.state.addAnimation(0, "Idle", true, 0.0F);
    }
    @Override
    public void useSlowAttackAnimation() {
        this.state.setAnimation(0, "Skill_2", false);
        this.state.addAnimation(0, "Idle", true, 0.0F);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        //These attack effects will be used when you attack the heart.
        return new AbstractGameAction.AttackEffect[] {
                AbstractGameAction.AttackEffect.SLASH_VERTICAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY
        };
    }

    private final Color cardRenderColor = Color.LIGHT_GRAY.cpy(); //Used for some vfx on moving cards (sometimes) (maybe)
    private final Color cardTrailColor = Color.LIGHT_GRAY.cpy(); //Used for card trail vfx during gameplay.
    private final Color slashAttackColor = Color.LIGHT_GRAY.cpy(); //Used for a screen tint effect when you attack the heart.
    @Override
    public Color getCardRenderColor() {
        return cardRenderColor;
    }

    @Override
    public Color getCardTrailColor() {
        return cardTrailColor;
    }

    @Override
    public Color getSlashAttackColor() {
        return slashAttackColor;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        //Font used to display your current energy.
        //energyNumFontRed, Blue, Green, and Purple are used by the basegame characters.
        //It is possible to make your own, but not convenient.
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        //This occurs when you click the character's button in the character select screen.
        //See SoundMaster for a full list of existing sound effects, or look at BaseMod's wiki for adding custom audio.
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    //Don't adjust these four directly, adjust the contents of the CharacterStrings.json file.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }
    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }
    @Override
    public String getVampireText() {
        return TEXT[2]; //Generally, the only difference in this text is how the vampires refer to the player.
    }

    /*- You shouldn't need to edit any of the following methods. -*/

    //This is used to display the character's information on the character selection screen.
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                MAX_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this,
                getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.CARD_COLOR;
    }

    @Override
    public AbstractPlayer newInstance() {
        //Makes a new instance of your character class.
        return new Rosmontis();
    }
}