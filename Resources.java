package fi.thunder.cyborg;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * Contains all the pre-loaded assets, put inside the libgdx asset loader.
 * @author Henri Kankaanpää
 */

public class Resources {

    public AssetManager assetManager = new AssetManager();

    public Resources() {
        assetManager.load("Sounds/lullaby_Viljami.mp3", Music.class);
        assetManager.load("Sounds/AlarmClock.mp3", Sound.class);
        assetManager.load("Sounds/stereomusa.mp3", Sound.class);
        assetManager.load("Sounds/PhoneVibrate.mp3", Sound.class);
        assetManager.load("Sounds/Hana.mp3", Sound.class);
        assetManager.load("Sounds/Kahvinkeitin.mp3", Sound.class);
        assetManager.load("Sounds/Kuorsaus.mp3", Sound.class);
        assetManager.load("Sounds/click1.wav", Sound.class);
        assetManager.load("Sounds/click2.wav", Sound.class);
        assetManager.load("Sounds/click5.wav", Sound.class);
        assetManager.load("Sounds/KissaMau3.mp3", Sound.class);
        assetManager.load("Sounds/Partakone.mp3", Sound.class);
        assetManager.load("Sounds/Fridge.mp3", Sound.class);
        assetManager.load("Sounds/Roboottivakuumi.mp3", Sound.class);
        assetManager.load("Sounds/Suihku.mp3", Sound.class);
        assetManager.load("Sounds/ElectricToothbrush.mp3", Sound.class);
        assetManager.load("Sounds/Telkkari.mp3", Sound.class);
        assetManager.load("Sounds/TietsikkaDialUp.mp3", Sound.class);
        assetManager.load("Sounds/Vedenkeitin.mp3", Sound.class);
        assetManager.load("alkumenupohja.jpg", Texture.class);
        assetManager.load("highscorespohja.jpg", Texture.class);
        assetManager.load("loppumenutappio2.jpg", Texture.class);
        assetManager.load("loppumenuvoitto3.jpg", Texture.class);
        assetManager.load("darkness.png", Texture.class);
        assetManager.load("Objects/BDbg.jpg", Texture.class);
        assetManager.load("Objects/BRbg.jpg", Texture.class);
        assetManager.load("Objects/KTbg.jpg", Texture.class);
        assetManager.load("Objects/LRbg.jpg", Texture.class);
        assetManager.load("Objects/PBbg.jpg", Texture.class);
        assetManager.load("alkumenupohja.jpg", Texture.class);
        assetManager.load("highscorespohja.jpg", Texture.class);
        assetManager.load("loppumenutappio2.jpg", Texture.class);
        assetManager.load("loppumenuvoitto3.jpg", Texture.class);
        assetManager.load("UI/uiskin.json", Skin.class);
        assetManager.load("Localization/sandmanBundle_en_US", I18NBundle.class);
        assetManager.load("Localization/sandmanBundle_fi_FI", I18NBundle.class);
        assetManager.load("Button/fi.png", Texture.class);
        assetManager.load("Button/en.png", Texture.class);
        assetManager.finishLoading();
    }
}
