package fi.thunder.cyborg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *The class for the disturbance actor objects that you need to shut down during the game
 * @author Aleksi Kilpilampi, Henri Kankaanpää
 */

public class Disturbance extends Actor {

    private SandmanMain g;
    Sprite sprite;
    Sound sound;
    float xPos;
    float yPos;
    boolean started = false;
    int idD;
    int objectId;
    boolean animation = false;
    Texture disturbanceSheet;
    Animation disturbanceAnimation;
    private float stateTime;
    private TextureRegion currentFrame;
    private Rectangle disturbanceRect;

    private float counter;

    /**
     * Constructor for disturbances
     * @param game the game class and it's functions and values
     * @param id determines which disturbance object to build
     */
    public Disturbance(SandmanMain game, int id){

        g = game;





            //puhelin makuuhuoneessa
            if (id == 0){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/mobileON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 0;
                idD = 0;
                /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/mobile.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.1123f, SandmanMain.REAL_HEIGHT * 0.1493f);
                xPos = SandmanMain.REAL_WIDTH*0.1479f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.571f);
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/PhoneVibrate.mp3", Sound.class);
            }

            //tietokone
            if (id == 1){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/computerON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 60f, allFrames);
                disturbanceRect = new Rectangle();

                idD = 0;
                objectId = 1;
               /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/computer.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.2275f, SandmanMain.REAL_HEIGHT * 0.2357f);
                xPos = SandmanMain.REAL_WIDTH*0.6675f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.5234f);
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/TietsikkaDialUp.mp3", Sound.class);
            }

            // roomba
            if (id == 2){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/roboimuriON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                idD = 0;
                objectId = 2;
                /*sprite = new Sprite(new Texture(Gdx.files.internal("Objects/roboimuriON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.081f, SandmanMain.REAL_HEIGHT * 0.095f);
                xPos = SandmanMain.REAL_WIDTH*0.33f;
                yPos = SandmanMain.REAL_HEIGHT*0.2f;
                disturbanceRect.setPosition(xPos, yPos);
                if (game.resources.assetManager.isLoaded("Sounds/Roboottivakuumi.mp3")) {
                    sound = game.resources.assetManager.get("Sounds/Roboottivakuumi.mp3", Sound.class);
                }
            }

           /* //oksa
            if (id == 3){
                objectId = 3;
                idD = 0;
                sprite = new Sprite(new Texture(Gdx.files.internal("Objects/badlogic.jpg")));
                sprite.setSize(g.REAL_WIDTH*0.081f, g.REAL_HEIGHT*0.095f);
                xPos = g.REAL_WIDTH*0.6f;
                yPos = g.REAL_HEIGHT*0.3f;
                sprite.setPosition(xPos,yPos);

                sound = game.resources.assetManager.get("Sounds/click5.wav", Sound.class);
            } */

            //peitto
            if (id == 3){
                objectId = 3;
                idD = 0;
                sprite = new Sprite(new Texture(Gdx.files.internal("Objects/planketOFF.png")));
                sprite.setSize(SandmanMain.REAL_WIDTH*0.34f, SandmanMain.REAL_HEIGHT*0.241f);
                xPos = SandmanMain.REAL_WIDTH*0.328f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.565f);
                sprite.setPosition(xPos,yPos);

                sound = game.resources.assetManager.get("Sounds/click5.wav", Sound.class);
            }

            //bathroom

            //suihku
            if (id == 4){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/suihkuON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                idD = 1;
                objectId = 4;
                /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/suihkuON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.0947f, SandmanMain.REAL_HEIGHT * 0.3587f);
                xPos = SandmanMain.REAL_WIDTH*0.3022f;
                yPos = SandmanMain.REAL_HEIGHT*0.525f;
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/Suihku.mp3", Sound.class);
            }

            //hana
            if (id == 5){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/lavuaariON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 5;
                idD = 1;
                /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/faucet.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.1812f, SandmanMain.REAL_HEIGHT * 0.2871f);
                xPos = SandmanMain.REAL_WIDTH*0.1211f;
                yPos = SandmanMain.REAL_HEIGHT*0.2331f;
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/Hana.mp3", Sound.class);
            }

            // hammasharja
            if (id == 6){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/hammasharjaON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 6;
                idD = 1;
                /*sprite = new Sprite(new Texture(Gdx.files.internal("Objects/hammasharjaON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.0337f, SandmanMain.REAL_HEIGHT * 0.0924f);
                xPos = SandmanMain.REAL_WIDTH*0.2383f;
                yPos = SandmanMain.REAL_HEIGHT*0.6517f;
                disturbanceRect.setPosition(xPos, yPos);
                if (game.resources.assetManager.isLoaded("Sounds/ElectricToothbrush.mp3")) {
                    sound = game.resources.assetManager.get("Sounds/ElectricToothbrush.mp3", Sound.class);
                }
            }

            //partakone
            if (id == 7){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/partakoneON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 7;
                idD = 1;
                /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/partakoneON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.0601f, SandmanMain.REAL_HEIGHT * 0.0911f);
                xPos = SandmanMain.REAL_WIDTH*0.2422f;
                yPos = SandmanMain.REAL_HEIGHT*0.14f;
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/Partakone.mp3", Sound.class);
            }



            //kitchen

            //vedenkeitin
            if (id == 8){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/vedenkeitinON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 40f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 8;
                idD = 2;
                /*sprite = new Sprite(new Texture(Gdx.files.internal("Objects/vedenkeitinON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.0513f, SandmanMain.REAL_HEIGHT * 0.1022f);
                xPos = g.REAL_WIDTH*0.7451f;
                yPos = g.REAL_HEIGHT*(1f-0.5703f);
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/Vedenkeitin.mp3", Sound.class);
            }

            //jääkaappi
            if (id == 9){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/fridgeON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 50f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 9;
                idD = 2;
                /*sprite = new Sprite(new Texture(Gdx.files.internal("Objects/fridge.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.2231f, SandmanMain.REAL_HEIGHT * 0.666f);
                xPos = SandmanMain.REAL_WIDTH*0.0732f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.8867f);
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/Fridge.mp3", Sound.class);
            }

            // kahvinkeitin
            if (id == 10){
                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/kahvinkeitin_ON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 30f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 10;
                idD = 2;
                /*sprite = new Sprite(new Texture(Gdx.files.internal("Objects/kahvinkeitin_ON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.0825f, SandmanMain.REAL_HEIGHT * 0.2110f);
                xPos = SandmanMain.REAL_WIDTH*0.4541f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.4752f);
                disturbanceRect.setPosition(xPos, yPos);
                if (game.resources.assetManager.isLoaded("Sounds/Kahvinkeitin.mp3")) {
                    sound = game.resources.assetManager.get("Sounds/Kahvinkeitin.mp3", Sound.class);
                }
            }



            //livingroom

            //stereot
            if (id == 11){
                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/stereotON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 40f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 11;
                idD = 3;
                /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/stereotON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.1577f, SandmanMain.REAL_HEIGHT * 0.1777f);
                xPos = SandmanMain.REAL_WIDTH*0.5649f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.7266f);
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/stereomusa.mp3", Sound.class);
            }



            //telkkari
            if (id == 12){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/tvON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 12;
                idD = 3;
                /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/tvON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.1577f, SandmanMain.REAL_HEIGHT * 0.1777f);
                xPos = SandmanMain.REAL_WIDTH*0.4111f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.5742f);
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/Telkkari.mp3", Sound.class);
            }

            //kissa
            if (id == 13){
                objectId = 13;
                idD = 3;
                sprite = new Sprite(new Texture(Gdx.files.internal("Objects/cat.png")));
                sprite.setSize(SandmanMain.REAL_WIDTH*0.1265f, SandmanMain.REAL_HEIGHT*0.1973f);
                xPos = SandmanMain.REAL_WIDTH*0.5518f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.8317f);
                sprite.setPosition(xPos,yPos);

                sound = game.resources.assetManager.get("Sounds/KissaMau3.mp3", Sound.class);
            }

            //parents

            //faija kuorsaa
            if (id == 14){

                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/dadON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 20f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 14;
                idD = 4;
               /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/dad.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH*0.1226f, SandmanMain.REAL_HEIGHT*0.2480f);
                xPos = SandmanMain.REAL_WIDTH*0.5796f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.6171f);
                disturbanceRect.setPosition(xPos,yPos);

                sound = game.resources.assetManager.get("Sounds/Kuorsaus.mp3", Sound.class);
            }

            //herätyskello
            if (id == 15){
                animation = true;
                disturbanceSheet = new Texture(Gdx.files.internal("Objects/alarmclockON.png"));
                int tileWidth = disturbanceSheet.getWidth() /3;
                int tileHeight = disturbanceSheet.getHeight() /1;

                TextureRegion[][] tmp = TextureRegion.split(disturbanceSheet, tileWidth, tileHeight);
                TextureRegion[] allFrames = Animaatio.toTextureArray(tmp, 3, 1);

                disturbanceAnimation = new Animation(3/ 150f, allFrames);
                disturbanceRect = new Rectangle();

                objectId = 15;
                idD = 4;
               /* sprite = new Sprite(new Texture(Gdx.files.internal("Objects/alarmclockON.png"))); */
                disturbanceRect.setSize(SandmanMain.REAL_WIDTH * 0.0425f, SandmanMain.REAL_HEIGHT * 0.1074f);
                xPos = SandmanMain.REAL_WIDTH*0.8170f;
                yPos = SandmanMain.REAL_HEIGHT*(1f-0.6162f);
                disturbanceRect.setPosition(xPos, yPos);

                sound = game.resources.assetManager.get("Sounds/AlarmClock.mp3", Sound.class);
            }




            if (SandmanMain.prefs.getBoolean("sound")) {
                sound.loop(1);
            } else {
                sound.loop(0);
            }


            setTouchable(Touchable.enabled);
            if (animation)
                setBounds(disturbanceRect.getX(), disturbanceRect.getY(), disturbanceRect.getWidth(), disturbanceRect.getHeight());
            else
                setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
            addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Disturbance) event.getTarget()).started = true;
                    g.setInActive(idD);
                    RemoveMe();
                    sound.stop();
                    Disturbance.super.remove();
                    Gdx.app.log("arvoni on", "postins");
                    g.increaseScore(2000);
                }
            });
         }






    @Override
    /**draw method for animated and regular disturbances.
     * @param batch
     * @param parentAlpha
     */
    public void draw(Batch batch, float parentAlpha){
        if (animation){
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = disturbanceAnimation.getKeyFrame(stateTime, true);




            batch.draw(currentFrame, disturbanceRect.x, disturbanceRect.y, disturbanceRect.getWidth(), disturbanceRect.getHeight());
        }
        else
            sprite.draw(batch);

    }

    @Override
    /**if the disturbance has been tapped, we dispose it
     * @param delta delta time
     */
    public void act(float delta) {
        if (started) {
            if(animation){
                disturbanceSheet.dispose();
            }
            else {
                sprite.getTexture().dispose();
            }
            started = false;
        }
        counter += Gdx.graphics.getDeltaTime();

    }

    /**
     * pauses the sound
     */
    public void muteSound() {
        sound.pause();
    }

    /**
     * resumes the sound
     */
    public void resumeSound() {
        sound.resume();
    }


    /**
     * removes and disposes a disturbance
     */
    public void RemoveMe(){
        Gdx.app.log("Poistetaan", String.valueOf(this.idD));
        g.removeDisturbance(this.objectId);
        sound.stop();
        dispose();
    }

    /**
     * stops the sound
     */

    public void stopSounds(){
        sound.stop();
    }

    /**
     * Disposes the texture or the sprite of disturbance
     */

    public void dispose() {
        g.dispose();
        if(animation){
            disturbanceSheet.dispose();
        }
        else
            sprite.getTexture().dispose();

    }

}

