package fi.thunder.cyborg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * The pause button that is used in all the game screens. On click it opens a window with
 * several buttons with different actions.
 * @author Henri Kankaanpää
 */

public class PauseButton extends Actor {
    TextButton pauseButton;
    TextButton continueButton;
    TextButton restartButton;
    TextButton mainMenuButton;
    TextButton muteButton;
    Window pause;
    Skin skin;
    Sound sound;
    Sprite overlay;
    private SandmanMain game;
    private final Stage stage;

    /**
     * The main constructor for the pause button
     * @param g passes the game, or the main class
     * @param s passes the stage
     */

    public PauseButton(SandmanMain g, Stage s) {
        game = g;
        skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);
        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);

        stage = s;

        overlay = new Sprite(new Texture(Gdx.files.internal("darkness.png")));
        overlay.setColor(0,0,0,75);
        overlay.setSize(SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);

        continueButton = new TextButton("Continue", skin);
        continueButton.addListener(new InputListener() {
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
                game.setGameState(1);
                game.muteDisturbances();
                pause.remove();
                pause = null;
            }
        });

        restartButton = new TextButton("Restart", skin);
        restartButton.addListener(new InputListener(){
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
                game.newGame();
                game.setGameState(1);
                pause.remove();
                pause = null;
            }
        });

        mainMenuButton = new TextButton("Quit to Menu", skin);
        mainMenuButton.addListener(new InputListener(){
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
                game.setScreen(game.getMainMenu());
                game.setGameState(1);
                pause.remove();
                pause = null;
            }
        });

        muteButton = new TextButton("Mute", skin);



        pauseButton = new TextButton("PAUSE", skin);
        pauseButton.setSize(SandmanMain.REAL_WIDTH * 0.4f, SandmanMain.REAL_HEIGHT * 0.10f);
        pauseButton.setPosition(SandmanMain.REAL_WIDTH / 2 - pauseButton.getWidth() / 2, SandmanMain.REAL_HEIGHT - pauseButton.getHeight());
        setTouchable(Touchable.enabled);
        setBounds(pauseButton.getX(), pauseButton.getY(), pauseButton.getWidth(), pauseButton.getHeight());
        addListener(new InputListener() {
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

                if (pause == null) {
                    pause = new Window("PAUSED", skin);
                    pause.padTop(64);
                    pause.setMovable(false);
                    pause.add(continueButton).width(SandmanMain.REAL_WIDTH*0.4f).height(SandmanMain.REAL_HEIGHT * 0.1f).row();
                    pause.add(restartButton).width(SandmanMain.REAL_WIDTH*0.4f).height(SandmanMain.REAL_HEIGHT * 0.1f).row();
                    pause.add(mainMenuButton).width(SandmanMain.REAL_WIDTH*0.4f).height(SandmanMain.REAL_HEIGHT * 0.1f);
                    pause.setSize(SandmanMain.REAL_WIDTH / 1.5f, SandmanMain.REAL_HEIGHT / 1.5f);
                    pause.setPosition(SandmanMain.REAL_WIDTH / 2 - pause.getWidth() / 2, SandmanMain.REAL_HEIGHT / 2 - pause.getHeight() / 2);
                    stage.addActor(pause);
                    game.setGameState(0);
                    game.muteDisturbances();
                }
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        pauseButton.draw(batch, parentAlpha);

        if (pause != null) {
            overlay.draw(batch);
        }
    }

    @Override
    public void act(float delta) {
        pauseButton.act(Gdx.graphics.getDeltaTime());
    }

    public void dispose() {
        game.dispose();
        overlay.getTexture().dispose();
    }

    /**
     * Upon pausing the game through means not provided by the game (minimizing the program, getting
     * a call), this class is called to pause the game when returning to the game screens.
     */

    public void getPaused() {
        if (pause == null) {
            pause = new Window("PAUSED", skin);
            pause.padTop(64);
            pause.setMovable(false);
            pause.add(continueButton).width(SandmanMain.REAL_WIDTH*0.4f).height(SandmanMain.REAL_HEIGHT * 0.1f).row();
            pause.add(restartButton).width(SandmanMain.REAL_WIDTH*0.4f).height(SandmanMain.REAL_HEIGHT * 0.1f).row();
            pause.add(mainMenuButton).width(SandmanMain.REAL_WIDTH*0.4f).height(SandmanMain.REAL_HEIGHT * 0.1f);
            pause.setSize(SandmanMain.REAL_WIDTH / 1.5f, SandmanMain.REAL_HEIGHT / 1.5f);
            pause.setPosition(SandmanMain.REAL_WIDTH / 2 - pause.getWidth() / 2, SandmanMain.REAL_HEIGHT / 2 - pause.getHeight() / 2);
            stage.addActor(pause);
            game.setGameState(0);
            game.muteDisturbances();
        }
    }
}
