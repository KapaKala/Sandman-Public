package fi.thunder.cyborg;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

/**
 * The screen in which you can view the local high scores, up until the fifth largest score gained.
 * The records are saved via a preference file.
 * @author Henri Kankaanpää
 */

public class HighScoreScreen implements Screen {
    public static final String TAG = "OptionsScreen";

    private SandmanMain game;

    private SpriteBatch batch;
    private Texture bg;

    private Stage stage;
    private Skin skin;
    private Sound sound;

    private OrthographicCamera camera;

    private TextButton button;

    private BitmapFont font20;

    /**
     * Main constructor for the screen
     * @param g passes the game, the main class
     */

    public HighScoreScreen(SandmanMain g) {
        Gdx.app.log(TAG, "OptionsScreen g = " + g);
        game = g;

        batch = game.getBatch();

        bg = game.resources.assetManager.get("highscorespohja.jpg", Texture.class);
        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);

        SandmanMain.REAL_WIDTH = Gdx.graphics.getWidth();
        SandmanMain.REAL_HEIGHT = Gdx.graphics.getHeight();

        stage = new Stage();
        skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;

        font20 = generator.generateFont(parameter);

        button = new TextButton("Main Menu", skin, "default");
        button.setSize(SandmanMain.REAL_WIDTH * 0.4f, SandmanMain.REAL_HEIGHT * 0.10f);
        button.setPosition(SandmanMain.REAL_WIDTH / 2 - button.getWidth() / 2, SandmanMain.REAL_HEIGHT / 5 - button.getHeight() / 2);
        button.setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        button.setTouchable(Touchable.enabled);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "lifted");
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                game.setScreen(game.getMainMenu());
            }
        });

        stage.addActor(button);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);
    }

    public void refreshTexts() {
        button.setText(game.translate.get("backtomenu"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        refreshTexts();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(bg, 0, 0, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);
        font20.setColor(0,0,0,1f);
        font20.draw(batch, "1. \n" +
                "2. \n" +
                "3. \n" +
                "4. \n" +
                "5.", SandmanMain.REAL_WIDTH * 0.38f, SandmanMain.REAL_HEIGHT * 0.60f, 100, Align.left, false);
        font20.draw(batch, "" + SandmanMain.prefs.getInteger("highScore") + " \n" +
                "" + SandmanMain.prefs.getInteger("highScore2") + " \n" +
                "" + SandmanMain.prefs.getInteger("highScore3") + " \n" +
                "" + SandmanMain.prefs.getInteger("highScore4") + " \n" +
                "" + SandmanMain.prefs.getInteger("highScore5"), SandmanMain.REAL_WIDTH * 0.45f, SandmanMain.REAL_HEIGHT * 0.60f, 100, Align.right, false);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        batch.dispose();
        game.dispose();
    }
}
