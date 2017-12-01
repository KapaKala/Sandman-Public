package fi.thunder.cyborg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The main menu screen of the game, where you can get to the game, the high score screen,
 * the options screen, and the tutorial screen.
 * @author Henri Kankaanpää
 */

public class MainMenuScreen implements Screen {
    public static final String TAG = "MainMenuScreen";

    private SandmanMain game;

    private SpriteBatch batch;
    private Texture bg;

    private Stage stage;
    private Sound sound;
    private Music music;
    private OrthographicCamera camera;

    private TextButton gameButton;
    private TextButton optionsButton;
    private TextButton highScoreButton;
    private TextButton tutorialButton;
    private TextButton modeButton;

    /**
     * Constructor for the main menu screen
     * @param g passes the main class
     */

    public MainMenuScreen(SandmanMain g) {
        Gdx.app.log(TAG, "MainMenuScreen g = " + g);
        game = g;
        batch = game.getBatch();

        bg = game.resources.assetManager.get("alkumenupohja.jpg", Texture.class);

        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);

        music = game.resources.assetManager.get("Sounds/lullaby_Viljami.mp3", Music.class);

        if (SandmanMain.prefs.getBoolean("sound")) {
            music.play();
            music.setVolume(1);
        } else {
            music.setVolume(0);

        }


        stage = new Stage(new FitViewport(SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT), batch);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.WORLD_WIDTH, SandmanMain.WORLD_HEIGHT);

        Skin skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);
        gameButton = new TextButton("Play", skin, "default");
        if (SandmanMain.prefs.getInteger("gameMode") == 0) {
            gameButton.setText("Play");
        } else if (SandmanMain.prefs.getInteger("gameMode") == 1) {
            gameButton.setText("Survival");
        }
        gameButton.setSize(SandmanMain.REAL_WIDTH * 0.35f, SandmanMain.REAL_HEIGHT * 0.15f);
        gameButton.setPosition(SandmanMain.REAL_WIDTH / 2 - gameButton.getWidth() / 2, SandmanMain.REAL_HEIGHT / 1.8f - gameButton.getHeight() / 2);
        gameButton.setBounds(gameButton.getX(), gameButton.getY(), gameButton.getWidth(), gameButton.getHeight());
        gameButton.setTouchable(Touchable.enabled);
        gameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                music.stop();
                Gdx.app.log("button", "lifted");
                game.newGame();
                game.setScreen(game.getGameScreen());
            }
        });

        highScoreButton = new TextButton("High Scores", skin, "default");
        highScoreButton.setSize(SandmanMain.REAL_WIDTH * 0.35f, SandmanMain.REAL_HEIGHT * 0.15f);
        highScoreButton.setPosition(SandmanMain.REAL_WIDTH / 2 - highScoreButton.getWidth() / 2, SandmanMain.REAL_HEIGHT / 2.4f - highScoreButton.getHeight() / 2);
        highScoreButton.setBounds(highScoreButton.getX(), highScoreButton.getY(), highScoreButton.getWidth(), highScoreButton.getHeight());
        highScoreButton.setTouchable(Touchable.enabled);
        highScoreButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                Gdx.app.log("button", "lifted");
                game.setScreen(game.getHighScoreScreen());
            }
        });

        optionsButton = new TextButton("", skin, "default");
        optionsButton.setSize(SandmanMain.REAL_WIDTH * 0.35f, SandmanMain.REAL_HEIGHT * 0.15f);
        optionsButton.setPosition(SandmanMain.REAL_WIDTH / 2 - optionsButton.getWidth() / 2, SandmanMain.REAL_HEIGHT / 3.7f - optionsButton.getHeight() / 2);
        optionsButton.setBounds(optionsButton.getX(), optionsButton.getY(), optionsButton.getWidth(), optionsButton.getHeight());
        optionsButton.setTouchable(Touchable.enabled);
        optionsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                Gdx.app.log("button", "lifted");
                game.setScreen(game.getOptionsScreen());
            }
        });

        tutorialButton = new TextButton("Tutorial", skin, "default");
        tutorialButton.setSize(SandmanMain.REAL_WIDTH * 0.35f, SandmanMain.REAL_HEIGHT * 0.15f);
        tutorialButton.setPosition(SandmanMain.REAL_WIDTH / 2 - gameButton.getWidth() / 2, SandmanMain.REAL_HEIGHT / 8f - gameButton.getHeight() / 2);
        tutorialButton.setBounds(tutorialButton.getX(), tutorialButton.getY(), tutorialButton.getWidth(), tutorialButton.getHeight());
        tutorialButton.setTouchable(Touchable.enabled);
        tutorialButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                music.stop();
                Gdx.app.log("button", "lifted");
                game.setScreen(game.getTutorial());
            }
        });

        modeButton = new TextButton("Mode", skin, "default");
        modeButton.setSize(SandmanMain.REAL_WIDTH * 0.3f, SandmanMain.REAL_HEIGHT * 0.10f);
        modeButton.setPosition(0,0);
        modeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                Gdx.app.log("button", "lifted");
                if (SandmanMain.prefs.getInteger("gameMode") == 0) {
                    SandmanMain.prefs.putInteger("gameMode", 1);
                    SandmanMain.prefs.flush();
                    gameButton.setText("Survival");
                } else if (SandmanMain.prefs.getInteger("gameMode") == 1) {
                    SandmanMain.prefs.putInteger("gameMode", 0);
                    SandmanMain.prefs.flush();
                    gameButton.setText(game.translate.get("play"));
                }

            }
        });

        stage.addActor(gameButton);
        stage.addActor(highScoreButton);
        stage.addActor(optionsButton);
        stage.addActor(tutorialButton);
        stage.addActor(modeButton);
    }

    /**
     * Used to change the text on the buttons in relation to the active locale
     */

    private void refreshTexts(){
        gameButton.setText(game.translate.get("play"));
        highScoreButton.setText(game.translate.get("highscore"));
        optionsButton.setText(game.translate.get("options"));
        tutorialButton.setText(game.translate.get("tutorial"));
        modeButton.setText(game.translate.get("mode"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        if (SandmanMain.prefs.getBoolean("sound")) {
            music.play();
            music.setVolume(1);
        } else {
            music.setVolume(0);
        }
        refreshTexts();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(bg, 0, 0, SandmanMain.WORLD_WIDTH, SandmanMain.WORLD_HEIGHT);
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

    }

}
