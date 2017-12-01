package fi.thunder.cyborg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The class for the Parents' Bedroom screen
 * @author Henri Kankaanpää, Aleksi Kilpilampi
 */

public class ParentsBedroom implements Screen {
    public static final String TAG = "ParentsBedroom";

    private SandmanMain game;

    private SpriteBatch batch;
    private Texture bg;

    private OrthographicCamera camera;

    private Stage stage;
    private Button button;

    private boolean active;

    private Meter meter;
    private ShapeRenderer shaperenderer;
    private Label label;
    private Skin skin;
    private PauseButton pauseButton;

    /**
     * The constructor for the parents bedroom screen
     * @param g passes the main class
     */

    public ParentsBedroom(SandmanMain g) {
        Gdx.app.log(TAG, "ParentsBedroom g = " + g);
        game = g;
        active = false;

        batch = game.getBatch();
        shaperenderer = game.getShape();
        meter = new Meter(0f, game);

        bg = game.resources.assetManager.get("Objects/PBbg.jpg", Texture.class);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.WORLD_WIDTH, SandmanMain.WORLD_HEIGHT);

        skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);

        stage = new Stage(new FitViewport(SandmanMain.REAL_WIDTH,SandmanMain.REAL_HEIGHT), batch);
        button = new Button(game, 1, 0);

        label = new Label("Score: " + game.getScore(), skin);
        label.setPosition(SandmanMain.REAL_WIDTH/2 - label.getWidth(), 0);
        stage.addActor(label);

        stage.addActor(button);

        pauseButton = new PauseButton(game, stage);
        stage.addActor(pauseButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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

        game.getDisturbance();
        label.setText("Score: " + game.getScore());

        shaperenderer.begin(ShapeRenderer.ShapeType.Filled);
        shaperenderer.setColor(1, 1, 0, 1);
        meter.draw(shaperenderer);
        shaperenderer.end();
    }

    /**
     * Adds a disturbance actor to the stage, and sets the screen as "active"
     * @param disturbance passes a disturbance actor
     */

    public void setDisturbance(Actor disturbance){

        stage.addActor(disturbance);
        active = true;
    }

    /**
     * Clears the disturbance objects from the stage
     */

    public void clearDisturbances() {
        if (stage.getActors() != null) {
            stage.getActors().clear();
            stage.clear();
        }
    }

    /**
     * A method for setting the "active" status of the screen, used for changing the color of the
     * navigation arrows when there is a disturbance present in the room
     * @param b passes a boolean value
     */

    public void setActive(boolean b){
        if ( b == true){
            active = true;
        }
        if (b == false){
            active = false;
        }
    }

    /**
     * Gets the value of the "active" status to the main class
     * @return returns the value of "active"
     */

    public boolean getActive(){
        return active;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        game.setGameState(0);
    }

    @Override
    public void resume() {
        pauseButton.getPaused();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        game.dispose();

    }
}
