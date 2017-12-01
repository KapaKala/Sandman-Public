package fi.thunder.cyborg;

/**
 * The class for managing and checking the high scores.
 * @author Henri Kankaanpää
 */

public class Highscore {

    /**
     * In case the preferences of the device the game is ran on does not yet have any high scores,
     * this class is called to create the first five high scores as zeros.
     */

    public void createScores() {
        if (!SandmanMain.prefs.contains("highScore")) {
            SandmanMain.prefs.putInteger("highScore", 0);
        } else if (!SandmanMain.prefs.contains("highScore2")) {
            SandmanMain.prefs.putInteger("highScore2", 0);
        } else if (!SandmanMain.prefs.contains("highScore3")) {
            SandmanMain.prefs.putInteger("highScore3", 0);
        } else if (!SandmanMain.prefs.contains("highScore4")) {
            SandmanMain.prefs.putInteger("highScore4", 0);
        } else if (!SandmanMain.prefs.contains("highScore5")) {
            SandmanMain.prefs.putInteger("highScore5", 0);
        }
    }

    /**
     * After a game ends, this class receives the score, and goes through all the previous high
     * scores, checks whether or not any of them have been surpassed, and subsequently sets the
     * new high score in its steed, moving all possible lower high scores down by one place.
     * @param score passes the final score of the game.
     */

    public void checkHighScore(int score) {
        if (score >= SandmanMain.prefs.getInteger("highScore")) {
            SandmanMain.prefs.putInteger("highScore5", SandmanMain.prefs.getInteger("highScore4"));
            SandmanMain.prefs.putInteger("highScore4", SandmanMain.prefs.getInteger("highScore3"));
            SandmanMain.prefs.putInteger("highScore3", SandmanMain.prefs.getInteger("highScore2"));
            SandmanMain.prefs.putInteger("highScore2", SandmanMain.prefs.getInteger("highScore"));
            SandmanMain.prefs.putInteger("highScore", score);
        } else if (score >= SandmanMain.prefs.getInteger("highScore2")) {
            SandmanMain.prefs.putInteger("highScore5", SandmanMain.prefs.getInteger("highScore4"));
            SandmanMain.prefs.putInteger("highScore4", SandmanMain.prefs.getInteger("highScore3"));
            SandmanMain.prefs.putInteger("highScore3", SandmanMain.prefs.getInteger("highScore2"));
            SandmanMain.prefs.putInteger("highScore2", score);
        } else if (score >= SandmanMain.prefs.getInteger("highScore3")) {
            SandmanMain.prefs.putInteger("highScore5", SandmanMain.prefs.getInteger("highScore4"));
            SandmanMain.prefs.putInteger("highScore4", SandmanMain.prefs.getInteger("highScore3"));
            SandmanMain.prefs.putInteger("highScore3", score);
        } else if (score >= SandmanMain.prefs.getInteger("highScore4")) {
            SandmanMain.prefs.putInteger("highScore5", SandmanMain.prefs.getInteger("highScore4"));
            SandmanMain.prefs.putInteger("highScore4", score);
        } else if (score >= SandmanMain.prefs.getInteger("highScore5")) {
            SandmanMain.prefs.putInteger("highScore5", score);
        }
        SandmanMain.prefs.flush();
    }
}
