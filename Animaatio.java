package fi.thunder.cyborg;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The class used to run the animations of all the animated objects
 * @author Jussi Pohjolainen
 */

public class Animaatio {

    /**
     * Transforms a 2D TextureRegion array to 1D.
     *
     * @param tr 2D TextureRegion array
     * @param cols col number
     * @param rows row number
     * @return Transformed 1D array
     */

public static TextureRegion[] toTextureArray( TextureRegion [][]tr, int cols, int rows ) {
    TextureRegion[] frames
            = new TextureRegion[cols * rows];

    int index = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            frames[index++] = tr[i][j];
        }
    }

    return frames;

    }
}