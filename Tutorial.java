package fi.thunder.cyborg;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The screen for the tutorial, which is used to instruct the player in playing the game.
 * Consists of text that is changed according to the locale, the background and a black overlay
 * over it, as well as different types of buttons created by the TutorialButtons class.
 * @author Henri Kankaanpää
 */

public class Tutorial implements Screen {

    private SandmanMain game;

    private TutorialButtons button;

    private SpriteBatch batch;
    private Texture bg1;
    private Texture bg2;
    private Sprite overlay;

    private OrthographicCamera camera;

    private Stage stage;

    private Skin skin;
    private BitmapFont font;

    private Sound sound;
    private Sound disturbanceSound;

    private String text;

    private boolean loopingsound = true;

    private FontGenerator fontGenerator;
    private Skin fontSkin;


    public static int tutorialPhase = 1;

    /**
     * The constructor for the tutorial.
     * @param g passes the game, i.e. the main class
     */
    public Tutorial (SandmanMain g) {

        game = g;
        batch = game.getBatch();
        fontGenerator = new FontGenerator();
        fontSkin = fontGenerator.getSkin();

        button = new TutorialButtons(game, this);


        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);
        disturbanceSound = game.resources.assetManager.get("Sounds/Hana.mp3", Sound.class);

        bg1 = game.resources.assetManager.get("Objects/BDbg.jpg", Texture.class);
        bg2 = game.resources.assetManager.get("Objects/BRbg.jpg", Texture.class);
        overlay = new Sprite(new Texture(Gdx.files.internal("darkness.png")));
        overlay.setColor(0,0,0,75);
        overlay.setSize(SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);


        stage = new Stage(new FitViewport(SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT), batch);
        stage.addActor(button);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);

        skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);
        font = fontSkin.getFont("largefont");

        text = game.translate.get("tutorial1");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (tutorialPhase == 1 || tutorialPhase == 2 || tutorialPhase == 5) {
            batch.draw(bg1, 0, 0, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);
            overlay.draw(batch);
            font.draw(batch, text, SandmanMain.REAL_WIDTH * 0.1f, SandmanMain.REAL_HEIGHT *0.65f, SandmanMain.REAL_WIDTH * 0.75f, Align.center, true);
        } else if (tutorialPhase == 3 || tutorialPhase == 4) {
            batch.draw(bg2, 0, 0, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);
            overlay.draw(batch);
            font.draw(batch, text, SandmanMain.REAL_WIDTH *0.35f, SandmanMain.REAL_HEIGHT *0.75f, SandmanMain.REAL_WIDTH * 0.5f, Align.center, true);
        }


        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        game.dispose();
    }

    /**
     * The method used to change the phase of the tutorial, helping it go through different screens
     * @param phase the phase of the tutorial
     */
    public void setPhase(int phase) {
        if (phase == 1) {

        }else if (phase == 2) {
            text = game.translate.get("tutorial2");
            if (loopingsound) {
                if (SandmanMain.prefs.getBoolean("sound")) {
                    disturbanceSound.loop(1);
                } else {
                    disturbanceSound.loop(0);
                }
                loopingsound = false;
            }
        }else if (phase == 3) {
            text = game.translate.get("tutorial3");
        }else if (phase == 4) {
            text = game.translate.get("tutorial4");
            disturbanceSound.stop();
        }else if (phase == 5) {
            text = game.translate.get("tutorial5");
        }
    }
}
