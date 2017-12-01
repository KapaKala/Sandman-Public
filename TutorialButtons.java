package fi.thunder.cyborg;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * This class is for the buttons used in the tutorial, i.e. everything that you use to move
 * between the screens.
 * @author Henri Kankaanpää
 */

public class TutorialButtons extends Actor {
    private SandmanMain game;
    private Tutorial tutorial;
    private Sound sound;
    private Sprite button;

    /**
     * The constructor for the class. Presses of the button change the phase of the tutorial,
     * which in turn changes the buttons themselves, both the images as well as the bounds.
     * @param g passes the game, i.e. the main class
     * @param t passes the tutorial
     */

    public TutorialButtons (SandmanMain g, Tutorial t) {
        game = g;
        tutorial = t;

        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);
        button = new Sprite(new Texture("Button/0/idle.png"));

        change(1);
        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("tag", "tag");
                if (SandmanMain.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }
                if (Tutorial.tutorialPhase == 1) {
                    change(2);
                    Tutorial.tutorialPhase = 2;
                    tutorial.setPhase(2);
                } else if (Tutorial.tutorialPhase == 2) {
                    change(3);
                    Tutorial.tutorialPhase = 3;
                    tutorial.setPhase(3);
                } else if (Tutorial.tutorialPhase == 3) {
                    change(4);
                    Tutorial.tutorialPhase = 4;
                    tutorial.setPhase(4);
                } else if (Tutorial.tutorialPhase == 4) {
                    change(5);
                    Tutorial.tutorialPhase = 5;
                    tutorial.setPhase(5);
                } else if (Tutorial.tutorialPhase == 5) {
                    change(1);
                    game.setScreen(game.getMainMenu());
                    Tutorial.tutorialPhase = 1;
                    game.stopTutorial();
                }
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        button.draw(batch);
    }

    /**
     * Changes the button according to where the tutorial is going
     * @param phase tells the method which phase the tutorial is in
     */
    private void change(int phase) {
        if (phase ==1) {
            button = new Sprite(new Texture("Button/0/idle.png"));
            button.setSize(SandmanMain.REAL_WIDTH*0.1f, SandmanMain.REAL_HEIGHT*0.12f);
            button.setPosition(SandmanMain.REAL_WIDTH - button.getWidth() -5, SandmanMain.REAL_HEIGHT - button.getHeight() -5);
            setBounds(0, 0, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);
        } else if (phase == 2) {
            button = new Sprite(new Texture("Button/0/glowing.png"));
            button.setSize(SandmanMain.REAL_WIDTH*0.1f, SandmanMain.REAL_HEIGHT*0.12f);
            button.setPosition(SandmanMain.REAL_WIDTH - button.getWidth() -5, SandmanMain.REAL_HEIGHT - button.getHeight() -5);
            setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        }else if (phase == 3) {
            button = new Sprite(new Texture("Objects/faucet.png"));
            button.setSize(SandmanMain.REAL_WIDTH*0.1812f, SandmanMain.REAL_HEIGHT*0.2871f);
            button.setPosition(SandmanMain.REAL_WIDTH*0.1211f, SandmanMain.REAL_HEIGHT*0.2331f);
            setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        } else if (phase == 4) {
            button = new Sprite(new Texture("Button/2/idle.png"));
            button.setSize(SandmanMain.REAL_WIDTH*0.1f, SandmanMain.REAL_HEIGHT*0.12f);
            button.setPosition(5, 5);
            setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        } else if (phase == 5) {
            button = new Sprite(new Texture("Button/0/idle.png"));
            button.setSize(0, 0);
            button.setPosition(SandmanMain.REAL_WIDTH - button.getWidth() -5, SandmanMain.REAL_HEIGHT - button.getHeight() -5);
            setBounds(0, 0, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);
        }
    }
}
