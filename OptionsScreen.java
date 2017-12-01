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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * The screen where the setting of multiple options can take place, namely the difficulty level,
 * toggling of the sounds of the game, and toggling between the available languages (English and
 * Finnish)
 * @author Henri Kankaanpää
 */

public class OptionsScreen implements Screen {
    public static final String TAG = "OptionsScreen";

    private SandmanMain game;

    private SpriteBatch batch;
    private Texture bg;

    private Stage stage;
    private Skin skin;

    private OrthographicCamera camera;

    private TextButton button;
    private TextButton difficultyButton;
    private TextButton soundButton;

    private Label difficultyLabel;
    private Sound sound;
    private Sound sound2;

    private SpriteDrawable fi;
    private SpriteDrawable en;

    private ImageButton language;

    /**
     * Main constructor for the screen
     * @param g passes the game
     */

    public OptionsScreen(SandmanMain g) {
        Gdx.app.log(TAG, "OptionsScreen g = " + g);
        game = g;

        batch = game.getBatch();

        bg = game.resources.assetManager.get("alkumenupohja.jpg", Texture.class);
        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);
        sound2 = game.resources.assetManager.get("Sounds/click2.wav", Sound.class);

        stage = new Stage();
        skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);

        fi = new SpriteDrawable(new Sprite(game.resources.assetManager.get("Button/fi.png", Texture.class)));
        en = new SpriteDrawable(new Sprite(game.resources.assetManager.get("Button/en.png", Texture.class)));

        difficultyButton = new TextButton("", skin);
        difficultyButton.setSize(SandmanMain.REAL_WIDTH * 0.4f, SandmanMain.REAL_HEIGHT * 0.10f);
        difficultyButton.setPosition(SandmanMain.REAL_WIDTH/2 - difficultyButton.getWidth()/2, SandmanMain.REAL_HEIGHT/2.15f);
        difficultyButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (SandmanMain.prefs.getFloat("difficulty") == 0.75f) {
                    SandmanMain.prefs.putFloat("difficulty", 1f);
                } else if (SandmanMain.prefs.getFloat("difficulty") == 1f) {
                    SandmanMain.prefs.putFloat("difficulty", 1.5f);
                } else if (SandmanMain.prefs.getFloat("difficulty") == 1.5f) {
                    SandmanMain.prefs.putFloat("difficulty", 2f);
                } else if (SandmanMain.prefs.getFloat("difficulty") == 2f) {
                    SandmanMain.prefs.putFloat("difficulty", 0.75f);
                }
                SandmanMain.prefs.flush();
                refreshTexts();
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
            }
        });

        stage.addActor(difficultyButton);
        difficultyLabel = new Label("Difficulty: ", skin);
        difficultyLabel.setPosition(difficultyButton.getX(), difficultyButton.getY()+difficultyButton.getHeight());
        stage.addActor(difficultyLabel);

        soundButton = new TextButton("", skin);
        soundButton.setSize(SandmanMain.REAL_WIDTH * 0.4f, SandmanMain.REAL_HEIGHT * 0.10f);
        soundButton.setPosition(SandmanMain.REAL_WIDTH/2 - soundButton.getWidth()/2, SandmanMain.REAL_HEIGHT/3);
        stage.addActor(soundButton);

        soundButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (SandmanMain.prefs.getBoolean("sound")) {

                    if (SandmanMain.prefs.getBoolean("sound")) {
                        sound2.play(1);
                    } else {
                        sound2.play(0);
                    }
                    SandmanMain.prefs.putBoolean("sound", false);
                } else if (!SandmanMain.prefs.getBoolean("sound")) {
                    SandmanMain.prefs.putBoolean("sound", true);
                    if (SandmanMain.prefs.getBoolean("sound")) {
                        sound.play(1);
                    } else {
                        sound.play(0);
                    }

                }
                SandmanMain.prefs.flush();
                refreshTexts();
            }
        });

        button = new TextButton("Save and Return", skin, "default");
        button.setSize(SandmanMain.REAL_WIDTH * 0.4f, SandmanMain.REAL_HEIGHT * 0.10f);
        button.setPosition(SandmanMain.REAL_WIDTH / 2 - button.getWidth() / 2, SandmanMain.REAL_HEIGHT / 4 - button.getHeight() / 2);
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
                game.setScreen(game.getMainMenu());
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                SandmanMain.prefs.flush();
            }
        });

        language = new ImageButton(fi, fi, en);
        language.setSize(SandmanMain.REAL_WIDTH * 0.1f, SandmanMain.REAL_HEIGHT * 0.1f);
        language.setPosition(0, 0);
        if (SandmanMain.prefs.getInteger("language") == 1) {
            language.setChecked(false);
        } else {
            language.setChecked(true);
        }
        language.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (game.getLanguage() == 1) {
                    game.changeLanguage(2);
                    language.setChecked(true);
                    SandmanMain.prefs.putInteger("language", 2);
                    refreshTexts();
                } else {
                    game.changeLanguage(1);
                    language.setChecked(false);
                    SandmanMain.prefs.putInteger("language", 1);
                    refreshTexts();
                }
                SandmanMain.prefs.flush();
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
            }
        });

        stage.addActor(language);

        refreshTexts();

        stage.addActor(button);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);
    }

    /**
     * Used to refresh the texts of the buttons upon either opening the screen or pushing certain
     * buttons, so with for example changing the language, all the buttons subsequently change
     * the language as well.
     */

    private void refreshTexts(){
        if (SandmanMain.prefs.getBoolean("sound")) {
            soundButton.setText(game.translate.get("soundbuttonON"));
        } else {
            soundButton.setText(game.translate.get("soundbuttonOFF"));
        }
        if (SandmanMain.prefs.getFloat("difficulty") == 0.75f) {
            difficultyButton.setText(game.translate.get("easy"));
        } else if (SandmanMain.prefs.getFloat("difficulty") == 1f) {
            difficultyButton.setText(game.translate.get("normal"));
        } else if (SandmanMain.prefs.getFloat("difficulty") == 1.5f) {
            difficultyButton.setText(game.translate.get("hard"));
        } else if (SandmanMain.prefs.getFloat("difficulty") == 2f) {
            difficultyButton.setText(game.translate.get("nightmare"));
        }
        button.setText(game.translate.get("return"));

        difficultyLabel.setText(game.translate.get("difficulty"));
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
