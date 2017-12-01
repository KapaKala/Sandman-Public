package fi.thunder.cyborg;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Used to handle the creation of fonts in the game, by taking measures of the screen size
 * that the game is being ran on, and then creating fonts with sizes in relation to that.
 * @author Henri Kankaanpää
 */

public class FontGenerator {

    private static final int FONTSIZESMALL = 2;
    private static final int FONTSIZEMEDIUM = 3;
    private static final int FONTSIZELARGE = 4;
    private static final float FONTSIZEBASE = 0.01f;
    private Skin skin;
    private BitmapFont font;

    /**
     * Gets the size of the screen.
     * @return and returns a vector value.
     */

    private static Vector2 getViewPortSize() {
        return new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Gets the base font size in relation to the previously acquired viewport size.
     * @return Returns the base font size.
     */

    private static float getBaseFontSize() {
        return getViewPortSize().x * FONTSIZEBASE;
    }

    /**
     * Gets the small font size in relation to the previously acquired viewport size.
     * @return Returns the small font size.
     */

    public static int getFontsizesmall() {
        return (int)getBaseFontSize() * FONTSIZESMALL;
    }

    /**
     * Gets the medium font size in relation to the previously acquired viewport size.
     * @return Returns the medium font size.
     */

    public static int getFontsizemedium() {
        return (int)getBaseFontSize() * FONTSIZEMEDIUM;
    }

    /**
     * Gets the large font size in relation to the previously acquired viewport size.
     * @return Returns the large font size.
     */

    public static int getFontsizelarge() {
        return (int)getBaseFontSize() * FONTSIZELARGE;
    }

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/arial.ttf"));
    FreeTypeFontParameter parameter = new FreeTypeFontParameter();

    /**
     * Creates the fonts and puts them inside a skin.
     */

    public FontGenerator() {
        skin = new Skin();
        parameter.size = getFontsizesmall();
        font = generator.generateFont(parameter);
        skin.add("smallfont", font);
        parameter.size = getFontsizemedium();
        font = generator.generateFont(parameter);
        skin.add("mediumfont", font);
        parameter.size = getFontsizelarge();
        font = generator.generateFont(parameter);
        skin.add("largefont", font);
    }

    /**
     * Methods used by other classes to acquire the skin in which the created fonts reside.
     * @return Returns the skin.
     */

    public Skin getSkin() {
        return skin;
    }



}
