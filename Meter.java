package fi.thunder.cyborg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * The class for the meter used in the game, that displays how close you are to losing
 * @author Aleksi Kilpilampi
 */

public class Meter {
    public float counter;
    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private SandmanMain game;


    /**
     * The constructor for the meter
     * @param i a value to be set as the counter
     * @param g passes the game
     */
    public  Meter(float i, SandmanMain g){
        game = g;
        counter = i;
        img = new Texture("badlogic.jpg");
        shapeRenderer = game.getShape();
    }



    public void draw(SpriteBatch batch) {


        Gdx.app.log("piirrän", "muuten näy");
        batch.draw(img, 3f, 3f, 1f, 1f);



    }

    public void draw(ShapeRenderer shapeRenderer){

        shapeRenderer.rect(game.WORLD_WIDTH-0.5f, 1f, 0.5f, ((5f) - game.getValue()));

    }

    public void dispose() {
        game.dispose();
        img.dispose();
        batch.dispose();
    }
}
