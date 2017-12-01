package fi.thunder.cyborg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.ArrayList;
import java.util.Locale;

/**
 * The main class of the game, where all the magic happens
 * @author Henri Kankaanpää, Aleksi Kilpilampi
 * @version 1.0 release
 */

public class SandmanMain extends Game {
	public static final String TAG = "ChangingScreens";
	SpriteBatch batch;
	ShapeRenderer shaperenderer;
	public static Preferences prefs;

	I18NBundle translate;
	private int language;

	public static final float WORLD_WIDTH = 12.8f;
	public static final float WORLD_HEIGHT = 7.2f;
	public static float REAL_WIDTH;
	public static float REAL_HEIGHT;
	private float counter;
	private float distCounter;
	private float winCounter;
	public int score;

	public boolean debug;
	public boolean debug_done = false;

	private Intro intro;
	private Tutorial tutorial;
	private Highscore highscore;

	private MainMenuScreen mainMenuScreen;
	private WinScreen winScreen;
	private LoseScreen loseScreen;
	private HighScoreScreen highScoreScreen;

	private GameScreen gameScreen;

	private OptionsScreen optionsScreen;

	//huoneet pitää luoda heti että niihin voi luoda häiriöitä vaikka niissä ei ole käyty.

	 private Bathroom bathroom;

	 private Kitchen kitchen;

	 private LivingRoom livingRoom;

	 private ParentsBedroom parentsBedroom;

	 ArrayList<Disturbance> disturbancelist = new ArrayList<Disturbance>();
	 ArrayList<Disturbance> removelist = new ArrayList<Disturbance>();

	private Disturbance disturbance;

	private int gameState = 1;
	private float survivalTimer;
	private float survivalDifficulty;

	public Resources resources;

	/**
	 * gets the intro
	 * @return returns Intro
	 */
	public Intro getIntro() {
		return intro;
	}

	/**
	 * gets the tutorial, if there isn't one we create a new one
	 * @return return the tutorial
	 */
	public Tutorial getTutorial() {
		if (tutorial == null) {
			tutorial = new Tutorial(this);
		}
		return tutorial;
	}

	/**
	 * we stop and dispose the tutorial
	 */
	public void stopTutorial() {
		tutorial.dispose();
		tutorial = null;
	}

	/**
	 * we get (and create if there isn't one) main menu
	 * @return returns the main menu
	 */
	public MainMenuScreen getMainMenu() {
		if (mainMenuScreen != null) {
			mainMenuScreen = null;
		}
		mainMenuScreen = new MainMenuScreen(this);
		return mainMenuScreen;
	}

	/**
	 * We create all the rooms that the games have (in order to be able to spawn disturbances there)
	 * @return returns the main gamescreen (bedroom)
	 */
	public GameScreen getGameScreen() {
		gameScreen = new GameScreen(this);
		bathroom = new Bathroom(this);
		kitchen = new Kitchen(this);
		livingRoom =  new LivingRoom(this);
		parentsBedroom = new ParentsBedroom(this);
		return gameScreen;
	}

	/**
	 * Moving back to the gamescreen, for the navigation buttons
	 * @return returns the main gamescreen
	 */
	public GameScreen switchToGameScreen() {
		return gameScreen;
	}

	/**
	 * We get (and if it doesn't exist, create one) screen for options
	 * @return returns the options screen
	 */
	public OptionsScreen getOptionsScreen() {
		if(optionsScreen == null) {
			optionsScreen = new OptionsScreen(this);
			return optionsScreen;
		} else {
			return optionsScreen;
		}
	}

	/**
	 We get (and if it doesn't exist, create one) gamescreen for bathroom
	 * @return returns the bathroom
	 */
	public Bathroom getBathroom() {
		if(bathroom == null) {
			bathroom = new Bathroom(this);
			return bathroom;
		} else {
			return bathroom;
		}
	}

	/**
	 *We get (and if it doesn't exist, create one) screen for kitchen
	 * @return returns the kitchen
	 */
	public Kitchen getKitchen() {
		if(kitchen == null) {
			kitchen = new Kitchen(this);
			return kitchen;
		} else {
			return kitchen;
		}
	}

	/**
	 *We get (and if it doesn't exist, create one) screen for living room
	 * @return returns the living room
	 */
	public LivingRoom getLivingRoom() {
		if(livingRoom == null) {
			livingRoom = new LivingRoom(this);
			return livingRoom;
		} else {
			return livingRoom;
		}
	}

	/**
	 *We get (and if it doesn't exist, create one) screen for parent's bedroom
	 * @return returns the parent's bedroom
	 */
	public ParentsBedroom getParentsBedroom() {
		if(parentsBedroom == null) {
			parentsBedroom = new ParentsBedroom(this);
			return parentsBedroom;
		} else {
			return parentsBedroom;
		}
	}

	/**
	 * Creates the loss screen, and in case there is a previous one, it gets removed
	 * @return returns the loss screen
	 */
	public LoseScreen getLoseScreen() {
		if (loseScreen != null) {
			loseScreen.dispose();
			loseScreen = null;
		}
		loseScreen = new LoseScreen(this);
		return loseScreen;
	}

	/**
	 * Creates the win screen, and in case there is a previous one, it gets removed
	 * @return returns the win screen
	 */
	public WinScreen getWinScreen() {
		if (winScreen != null) {
			winScreen = null;
		}
		winScreen = new WinScreen(this);
		return winScreen;
	}

	/**
	 * Creates the high score screen, and in case there is a previous one, it gets removed
	 * @return returns the high score screen
	 */
	public HighScoreScreen getHighScoreScreen() {
		if (highScoreScreen != null) {
			highScoreScreen = null;
		}
			highScoreScreen = new HighScoreScreen(this);
			return highScoreScreen;
	}

	/**
	 * A method for giving Spritebatch to other classes.
	 * @return returns the batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}

	/**
	 * Similiar function as getBatch, but for ShapeRenderer
	 * @return returns the ShapeRenderer
	 */
	public ShapeRenderer getShape() {return shaperenderer; }

	/**
	 * A cleanuptool that resets the roos and game values for a fresh game
	 */
	public void newGame() {
		if (gameScreen != null) {
			removeAllDisturbances();
			gameScreen.dispose();
			kitchen.dispose();
			bathroom.dispose();
			livingRoom.dispose();
			parentsBedroom.dispose();
			if (loseScreen != null) {
				loseScreen.dispose();
				loseScreen = null;
			}
			if (winScreen != null) {
				winScreen.dispose();
				winScreen = null;
			}

		}
		counter = 0;
		score = 0;
		distCounter = 0;
		winCounter = 0;
		survivalTimer = 0;
		survivalDifficulty = 0;
		this.setScreen(getGameScreen());

	}

	@Override
	/**
	 * The part where we create the game and setup the values.
	 */
	public void create () {
		REAL_WIDTH = Gdx.graphics.getWidth();
		REAL_HEIGHT = Gdx.graphics.getHeight();

		resources = new Resources();

		Locale defaultLocale = Locale.getDefault();
		if(defaultLocale.getCountry().equals("FI")) {
			language = 1;
		}
		else {language = 2;}

		Gdx.app.log(TAG, "create");
		batch = new SpriteBatch();
		shaperenderer = new ShapeRenderer();
		counter = 0f;
		distCounter = 0f;
		survivalTimer = 0f;
		survivalDifficulty = 0.5f;
		score = 0;


		intro = new Intro(this);
		setScreen(intro);

		prefs = Gdx.app.getPreferences("SandmanPrefs");
		highscore = new Highscore();
		highscore.createScores();
		changeLanguage(language);

		if (!prefs.contains("gameMode")) {
			prefs.putInteger("gameMode", 0);
		}

		if (!prefs.contains("sound")) {
			prefs.putBoolean("sound", true);
		}

		if (!prefs.contains("difficulty")) {
			prefs.putFloat("difficulty", 1);
		}

		if (prefs.contains("language")) {
			if (prefs.getInteger("language") == 1) {
				changeLanguage(1);
			} else if (prefs.getInteger("language") == 2) {
				changeLanguage(2);
			}
		}

		prefs.flush();

		debug = false;
	}

	/**
	 *  Increases the value of meter per disturbance. If the meter runs out, player loses the game.
	 *  If player survives long enough, player wins the game.
	 */
	public void increaseValue(){
		//mittari laskee nopeammin mitä enemmän häiriöitä
		for (Disturbance d : disturbancelist){
			counter = counter + Gdx.graphics.getDeltaTime()/3;
		}

		score++;
		if (counter > 15f && !debug){
			highscore.checkHighScore(score);
			removeAllDisturbances();
			setScreen(getLoseScreen());
			counter = 0;
			winCounter = 0;
			gameScreen = null;
			score = 0;
		}

		if (winCounter > 30f && !debug) {
			highscore.checkHighScore(score);
			removeAllDisturbances();
			setScreen(getWinScreen());
			counter = 0;
			winCounter = 0;
			gameScreen = null;
			score = 0;
		} else {
			winCounter+=Gdx.graphics.getDeltaTime();
		}
	}

	/**
	 * Same as before but for survival mode. Player can't win the game in this mode
	 */
	public void increaseSurvivalValue() {
		score++;
		survivalTimer += (Gdx.graphics.getDeltaTime());
		for (Disturbance d : disturbancelist){
			counter = counter + Gdx.graphics.getDeltaTime()/3;
		}

		if (survivalTimer > 4f) {
			survivalDifficulty += 0.1f;
			survivalTimer = 0;
			Gdx.app.log("DIFFICULTY", "" + survivalDifficulty);

		}

		if (counter > 15f && !debug){
			removeAllDisturbances();
			setScreen(getLoseScreen());
			counter = 0;
			gameScreen.clearDisturbances();
			gameScreen = null;
			highscore.checkHighScore(score);
			score = 0;
			survivalTimer = 0;
		}

		Gdx.app.log("SURVIVALTIMER", "" + survivalTimer);
	}

	/**
	 * //meter gets it's value from here
	 * @return returns the value, divided by three in order to scale the meter correctly.
	 */
	public float getValue(){
		return counter/3;
	}

	/**
	 * Returns the score for display.
	 * @return returns the score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Increases the score.
	 * @param amount by how much the score is increased.
	 */
	public void increaseScore (int amount) {
		score = score + amount;
	}

	/**
	 * Removes the disturbance so it doesn't decrease the value of the meter.
	 * @param id tells which disturbacne to remove.
	 */
	public void removeDisturbance(int id){
		for (Disturbance d : disturbancelist){
			if (d.objectId == id ){
				removelist.add(d);
				Gdx.app.log("pääsin tänne asti", String.valueOf(d));
			}
		}
		for (Disturbance d : removelist){
			disturbancelist.remove(d);
		}
	}

	/**
	 * Removes all the disturbances if the game is ended.
	 */
	public void removeAllDisturbances() {
		bathroom.clearDisturbances();
		kitchen.clearDisturbances();
		gameScreen.clearDisturbances();
		livingRoom.clearDisturbances();
		parentsBedroom.clearDisturbances();

		for (Disturbance d: disturbancelist) {
			d.stopSounds();
			d.remove();
		}

		disturbancelist.clear();
	}

	/**
	 * Mutes the sounds from disturbances.
	 */
	public void muteDisturbances() {
		if (gameState == 0) {
			for (Disturbance d : disturbancelist) {
				d.muteSound();
			}
		} else if (gameState == 1) {
			for (Disturbance d : disturbancelist) {
				d.resumeSound();
			}
		}
	}

	/**
	 * Creates a random disturbance that doesn't exist yet.
	 */
	public void getDisturbance() {

		if (debug && !debug_done) {
			debug_done = true;
			for (int room = 0; room < 16; room++) {

				disturbance = new Disturbance(this, room);

					disturbancelist.add(disturbance);

					if (room >= 0 && room <= 3) {
						gameScreen.setDisturbance(disturbance);

					}
					if (room > 3 && room < 8) {
						bathroom.setDisturbance(disturbance);

					}
					if (room > 7 && room < 11) {
						kitchen.setDisturbance(disturbance);

					}
					if (room > 10 && room < 14) {
						livingRoom.setDisturbance(disturbance);

					}
					if (room > 13) {
						parentsBedroom.setDisturbance(disturbance);

					}
					distCounter = 0f;
				}

			}

		if (gameState == 1 && !debug) {
			if (prefs.getInteger("gameMode") == 0) {
				distCounter += Gdx.graphics.getDeltaTime() * prefs.getFloat("difficulty");
				//lisätään mittarin arvoa täs välis
				increaseValue();
			} else if (prefs.getInteger("gameMode") == 1) {
				increaseSurvivalValue();
				distCounter += Gdx.graphics.getDeltaTime() * survivalDifficulty;
			}

			if (distCounter > 4f){
				int room = (int) (Math.random()*16);
				boolean same = false;

				if (!disturbancelist.isEmpty()) {
					for (Disturbance d : disturbancelist) {
						if (room == d.objectId) {
							same = true;
						}
					}
				}
				//if there isn't the same disturbance osaan englantia
				if (!same) {

					Gdx.app.log("add", String.valueOf(room));
					disturbance = new Disturbance(this, room);

						disturbancelist.add(disturbance);

						if (room >= 0 && room <=3){
							gameScreen.setDisturbance(disturbance);

						}
						if (room > 3 && room <8){
							bathroom.setDisturbance(disturbance);

						}
						if (room > 7 && room <11){
							kitchen.setDisturbance(disturbance);

						}
						if (room > 10 && room <14){
							livingRoom.setDisturbance(disturbance);

						}
						if (room > 13){
							parentsBedroom.setDisturbance(disturbance);

						}
						distCounter = 0f;

				}
			}
		}
	}

	/**
	 * Toggles between a paused game state and a running game state
	 * @param toState 0 is paused, 1 is running
	 */
	public void setGameState(int toState) {
		gameState = toState;
	}

	/**
	 * Checks if the certain room is active so we can change the state of the arrow buttons.
	 * @param id id of the room
	 * @return returns true or false if the room has disturbances in it
	 */
	public boolean getActive(int id){
		if (id == 0){
			return gameScreen.getActive();
		}
		else if (id == 1){
			return bathroom.getActive();
		}
		else if (id == 2){
			return kitchen.getActive();
		}
		else if (id == 3){
			return livingRoom.getActive();
		}
		else if (id == 4){
			return parentsBedroom.getActive();
		}
		else{
			return true;
		}
	}

	/**
	 * when a disturbance is removed, room is set to be inactive
	 * @param id id of the room
	 */
	public void setInActive(int id){
		if (id == 0){
			 gameScreen.setActive(false);
		}
		else if (id == 1){
			 bathroom.setActive(false);
		}
		else if (id == 2){
			 kitchen.setActive(false);
		}
		else if (id == 3){
			livingRoom.setActive(false);
		}
		else if (id == 4){
			parentsBedroom.setActive(false);
		}

	}

	/**
	 * Changes the language.
	 * @param i English or Finnish.
	 */
	public void changeLanguage(int i){
		if(i == 1) {
			translate = resources.assetManager.get("Localization/sandmanBundle_fi_FI",I18NBundle.class);
			language = 1;
		}
		if(i == 2) {
			translate = resources.assetManager.get("Localization/sandmanBundle_en_US",I18NBundle.class);
			language = 2;
		}
	}

	/**
	 * Gets the language.
	 * @return returns. the language.
	 */
	public int getLanguage() {
		return language;
	}



	@Override
	/**
	 * Call overclass rendering method.
	 */
	public void render () {
		super.render();
	}

}
