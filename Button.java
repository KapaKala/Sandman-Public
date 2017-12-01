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
 * The class used to crate the arrow buttons with which you move between different game screens.
 * @author Henri Kankaanpää, Aleksi Kilpilampi
 */

public class Button extends Actor {

    private SandmanMain game;
    Sprite button;
    Texture idle;
    Texture pressed;
    Texture glowing;
    Sound sound;
    float xPos;
    float yPos;

    int roomID;

    /*
    posID:
    0 = upper right
    1 = lower right
    2 = lower left
    3 = upper left
    toRoomID:
    0 = main bedroom
    1 = bathroom
    2 = kitchen
    3 = living room
    4 = parents' bedroom
     */

    /**
     * The main constructor for the buttons.
     * @param g Passes the game, or the main class.
     * @param posID Used to determine where the arrow is pointing as follows:
     *              posID:
     *              0 = upper right
     *              1 = lower right
     *              2 = lower left
     *              3 = upper left
     * @param toRoomID Used to determine which room is returned upon pressing the button, as follows:
     *                 toRoomID:
     *                 0 = main bedroom
     *                 1 = bathroom
     *                 2 = kitchen
     *                 3 = living room
     *                 4 = parents' bedroom
     */

    public Button (SandmanMain g, int posID, final int toRoomID) {
        game = g;
        roomID = toRoomID;
        sound = game.resources.assetManager.get("Sounds/click1.wav", Sound.class);

        if (posID == 0) {
            idle = new Texture(Gdx.files.internal("Button/0/idle.png"));
            button = new Sprite(idle);
            button.setSize(SandmanMain.REAL_WIDTH*0.1f, SandmanMain.REAL_HEIGHT*0.12f);
            xPos = SandmanMain.REAL_WIDTH - button.getWidth() - SandmanMain.REAL_WIDTH*0.01f;
            yPos = SandmanMain.REAL_HEIGHT - button.getHeight() - SandmanMain.REAL_HEIGHT*0.01f;
            button.setPosition(xPos, yPos);

            //glowing
            glowing = new Texture(Gdx.files.internal("Button/0/glowing.png"));

        }

        if (posID == 1) {
            idle = new Texture(Gdx.files.internal("Button/1/idle.png"));
            button = new Sprite(idle);
            button.setSize(SandmanMain.REAL_WIDTH*0.1f, SandmanMain.REAL_HEIGHT*0.12f);
            xPos = SandmanMain.REAL_WIDTH - button.getWidth() - SandmanMain.REAL_WIDTH*0.01f;
            yPos = SandmanMain.REAL_HEIGHT*0.01f;
            button.setPosition(xPos, yPos);

            //glowing
            glowing = new Texture(Gdx.files.internal("Button/1/glowing.png"));

        }

        if (posID == 2) {
            idle = new Texture(Gdx.files.internal("Button/2/idle.png"));
            button = new Sprite(idle);
            button.setSize(SandmanMain.REAL_WIDTH*0.1f, SandmanMain.REAL_HEIGHT*0.12f);
            xPos = SandmanMain.REAL_WIDTH*0.01f;
            yPos = SandmanMain.REAL_HEIGHT*0.01f;
            button.setPosition(xPos, yPos);

            //glowing
            glowing = new Texture(Gdx.files.internal("Button/2/glowing.png"));

        }

        if (posID == 3) {
            idle = new Texture(Gdx.files.internal("Button/3/idle.png"));
            button = new Sprite(idle);
            button.setSize(SandmanMain.REAL_WIDTH*0.1f, SandmanMain.REAL_HEIGHT*0.12f);
            xPos = SandmanMain.REAL_WIDTH*0.01f;
            yPos = SandmanMain.REAL_HEIGHT - button.getHeight() - SandmanMain.REAL_HEIGHT*0.01f;
            button.setPosition(xPos, yPos);

            //glowing
            glowing = new Texture(Gdx.files.internal("Button/3/glowing.png"));

        }

        setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "pressed");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("button", "lifted");
                if (roomID == 0) {
                    game.setScreen(game.switchToGameScreen());
                } else if (roomID == 1) {
                    game.setScreen(game .getBathroom());
                } else if (roomID == 2) {
                    game.setScreen(game.getKitchen());
                } else if (roomID == 3) {
                    game.setScreen(game .getLivingRoom());
                } else if (roomID == 4) {
                    game.setScreen(game.getParentsBedroom());
                }
                if (game.prefs.getBoolean("sound")) {
                    sound.play(1);
                } else {
                    sound.play(0);
                }

                Gdx.app.log("Toimin", "no en kyl toimi");
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        if (game.getActive(roomID) == true) {
            button.setTexture(glowing);
        }
        else{
            button.setTexture(idle);
        }
        button.draw(batch);


    }

    public void dispose() {
        idle.dispose();
        glowing.dispose();
    }
}
