package fi.thunder.cyborg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * The screen that is called when you lose the game - always called at the end of the survival mode
 * @author Henri Kankaanpää, Aleksi Kilpilampi
 */

public class LoseScreen implements Screen {

    public static final String TAG = "Bathroom";

    private SandmanMain game;

    private SpriteBatch batch;
    private Texture bg;

    private OrthographicCamera camera;

    private Stage stage;
    private Skin skin;
    private Skin fontskin;
    private TextButton button;
    private TextButton ngButton;
    private String info;
    private int infoID;

    private Sound sound;

    private BitmapFont font;
    private Sprite overlay;

    /**
     * Constructor for the lose screen
     * @param g passes the game class
     */


    public LoseScreen(SandmanMain g) {
        Gdx.app.log(TAG, "LoseScreen g = " + g);
        game = g;

        batch = game.getBatch();

        bg = game.resources.assetManager.get("loppumenutappio2.jpg", Texture.class);
        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);
        overlay = new Sprite(new Texture(Gdx.files.internal("darkness.png")));
        overlay.setColor(0,0,0,75);
        overlay.setSize(SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT * 0.2f);
        overlay.setPosition(0, SandmanMain.REAL_HEIGHT * 0.4f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);

        stage = new Stage(new ScreenViewport());

        skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);
        FontGenerator fontGenerator = new FontGenerator();
        fontskin = fontGenerator.getSkin();
        font = fontskin.getFont("mediumfont");
        font.setColor(1, 1, 1, 1);

        button = new TextButton("Back to menu", skin, "default");
        button.setSize(SandmanMain.REAL_WIDTH * 0.4f, SandmanMain.REAL_HEIGHT * 0.10f);
        button.setPosition(SandmanMain.REAL_WIDTH / 2 - button.getWidth() / 2, SandmanMain.REAL_HEIGHT * 0.25f);
        button.setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        button.setTouchable(Touchable.enabled);
        button.addListener(new ClickListener() {
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

        ngButton = new TextButton("Retry", skin, "default");
        ngButton.setSize(SandmanMain.REAL_WIDTH * 0.4f, SandmanMain.REAL_HEIGHT * 0.10f);
        ngButton.setPosition(SandmanMain.REAL_WIDTH / 2 - ngButton.getWidth() / 2, SandmanMain.REAL_HEIGHT * 0.10f);
        ngButton.setBounds(ngButton.getX(), ngButton.getY(), ngButton.getWidth(), ngButton.getHeight());
        ngButton.setTouchable(Touchable.enabled);
        ngButton.addListener(new ClickListener() {
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
                game.newGame();
            }
        });

        stage.addActor(button);
        stage.addActor(ngButton);


        infoID = (int)(Math.random()*21);

        if ( infoID == 0){
            info = game.translate.get("info0");
        }
        if ( infoID == 1){
            info = game.translate.get("info1");
        }
        if ( infoID == 2){
            info = game.translate.get("info2");
        }
        if ( infoID == 3){
            info = game.translate.get("info3");
        }
        if ( infoID == 4){
            info = game.translate.get("info4");
        }
        if ( infoID == 5){
            info = game.translate.get("info5");
        }
        if ( infoID == 6){
            info = game.translate.get("info6");
        }
        if ( infoID == 7){
            info = game.translate.get("info7");
        }
        if ( infoID == 8){
            info = game.translate.get("info8");
        }
        if ( infoID == 9){
            info = game.translate.get("info9");
        }
        if ( infoID == 10){
            info = game.translate.get("info10");
        }
        if ( infoID == 11){
            info = game.translate.get("info11");
        }
        if ( infoID == 12){
            info = game.translate.get("info12");
        }
        if ( infoID == 13){
            info = game.translate.get("info13");
        }
        if ( infoID == 14){
            info = game.translate.get("info14");
        }
        if ( infoID == 15){
            info = game.translate.get("info15");
        }
        if ( infoID == 16){
            info = game.translate.get("info16");
        }
        if ( infoID == 17){
            info = game.translate.get("info17");
        }
        if ( infoID == 18){
            info = game.translate.get("info18");
        }
        if ( infoID == 19){
            info = game.translate.get("info19");
        }
        if ( infoID == 20){
            info = game.translate.get("info20");
        }


    }

    /**
     * Used to change the text on the buttons according to the locale
     */
    private void refreshTexts(){
        button.setText(game.translate.get("backtomenu"));
        ngButton.setText(game.translate.get("retry"));
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
        overlay.draw(batch);
        font.draw(batch, info, 0, SandmanMain.REAL_HEIGHT * 0.55f, SandmanMain.REAL_WIDTH, Align.center, true);
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
        fontskin.dispose();
        stage.getActors().clear();
        stage.dispose();
        game.dispose();
    }
}
