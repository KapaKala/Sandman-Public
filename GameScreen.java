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
 * The "main" game screen, the bedroom, to which you get to when you start the game, and is the center of
 * the game itself.
 * @author Henri Kankaanpää, Aleksi Kilpilampi
 */

public class GameScreen implements Screen {
    public static final String TAG = "GameScreen";

    private SandmanMain game;

    private SpriteBatch batch;
    private Texture bg;

    private OrthographicCamera camera;

    private Skin skin;
    private Stage stage;
    private Button buttonBd;
    private Button buttonK;
    private Button buttonLr;
    private Button buttonPr;
    private PauseButton pauseButton;

    private boolean active;

    private Meter meter;
    private ShapeRenderer shaperenderer;

    private Label label;

    /**
     * The constructor for the main game screen, the bedroom
     * @param g passes the main class
     */

    public GameScreen(SandmanMain g) {
        Gdx.app.log(TAG, "GameScreen g = " + g);
        game = g;
        active = false;

        batch = game.getBatch();

        bg = game.resources.assetManager.get("Objects/BDbg.jpg", Texture.class);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.WORLD_WIDTH, SandmanMain.WORLD_HEIGHT);

        shaperenderer = game.getShape();
        shaperenderer.setProjectionMatrix(camera.combined);
        meter = new Meter(0f, game);

        skin = game.resources.assetManager.get("UI/uiskin.json", Skin.class);
        stage = new Stage(new FitViewport(SandmanMain.REAL_WIDTH,SandmanMain.REAL_HEIGHT), batch);

        label = new Label("Score: " + game.getScore(), skin);
        label.setPosition(SandmanMain.REAL_WIDTH / 2 - label.getWidth(), 0);
        stage.addActor(label);

        buttonBd = new Button(game, 0, 1);
        buttonK = new Button(game, 1, 2);
        buttonLr = new Button(game, 2, 3);
        buttonPr = new Button(game, 3, 4);
        pauseButton = new PauseButton(game, stage);
        stage.addActor(buttonBd);
        stage.addActor(buttonK);
        stage.addActor(buttonLr);
        stage.addActor(buttonPr);
        stage.addActor(pauseButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Adds a disturbance actor to the stage, and sets the screen as "active"
     * @param actor passes a disturbance actor
     */

    public void setDisturbance(Actor actor){
        stage.addActor(actor);
        setActive(true);
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
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(bg, 0, 0, SandmanMain.WORLD_WIDTH, SandmanMain.WORLD_HEIGHT);
        batch.end();

        game.getDisturbance();

        label.setText("Score: " + game.getScore());

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        shaperenderer.begin(ShapeRenderer.ShapeType.Filled);
        shaperenderer.setColor(1, 1, 0, 1);
        meter.draw(shaperenderer);
        shaperenderer.end();

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
        stage.getActors().clear();
        stage.dispose();
        game.dispose();
    }
}
