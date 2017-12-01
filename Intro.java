package fi.thunder.cyborg;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The splash screen for the game, which goes through the logos of all parties involved in
 * making our wonderful game happen.
 * @author Henri Kankaanpää
 */

public class Intro implements Screen {
    private SandmanMain game;
    private SpriteBatch batch;
    private Texture logo;
    private OrthographicCamera camera;
    private float timer;

    private int currentLogo;

    public Intro (SandmanMain g) {
        game = g;
        batch = game.getBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SandmanMain.REAL_WIDTH, SandmanMain.REAL_HEIGHT);

        // Screen starts with TAMK-logo
        logo = new Texture (Gdx.files.internal("Logos/TAMK.png"));
        currentLogo = 1;
        timer = 0;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        changeLogo();

        batch.draw(logo, SandmanMain.REAL_WIDTH / 2 - (logo.getWidth()/2), SandmanMain.REAL_HEIGHT/2 - (logo.getHeight()/2), logo.getWidth(), logo.getHeight());

        batch.end();
    }

    /**
     * Changes logo after a set time or when user touches the screen.
     */
    public void changeLogo(){
        timer += Gdx.graphics.getDeltaTime();
        // TIKO Logo
        if((timer > 0.8  && currentLogo == 1) ||  (Gdx.input.justTouched() && currentLogo == 1 &&  timer > 0.1)){
            logo = new Texture (Gdx.files.internal("Logos/TIKO.png"));
            timer = 0;
            currentLogo = 2;
            Gdx.app.log("Intro", "logo1");
        }
        // UKK Logo
        if((timer > 0.8 && currentLogo == 2)  || (Gdx.input.justTouched() && currentLogo == 2 && timer > 0.1)){
            logo = new Texture (Gdx.files.internal("Logos/ukkinstitute.png"));
            timer = 0;
            currentLogo = 3;
            Gdx.app.log("Intro", "logo2");
        }
        // PETO Logo
        if((timer > 0.8 && currentLogo ==3)  ||  (Gdx.input.justTouched() && currentLogo == 3 && timer > 0.1)){
            logo = new Texture (Gdx.files.internal("Logos/PETO-logo.png"));
            timer = 0;
            currentLogo = 4;
            Gdx.app.log("Intro", "logo3");
        }
        if((timer > 0.8 && currentLogo == 4) || (Gdx.input.justTouched() && currentLogo == 4 && timer > 0.1)){
            Gdx.app.log("Intro", "logo4");
            if (game.resources.assetManager.update()) {
                game.setScreen(game.getMainMenu());
            }

        }
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
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
