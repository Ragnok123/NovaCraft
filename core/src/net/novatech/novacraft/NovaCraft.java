package net.novatech.novacraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import net.novatech.novacraft.misc.AppState;
import net.novatech.novacraft.world.WorldState;

public class NovaCraft extends Game {

    public static final String VERSION = "pre-alpha 0.0.4";
    public static String GAME_FOLDER;

    public static AppState STATE;
    public static WorldState WORLD_STATE;

    public static boolean TOUCH;

    public NovaCraft(String gameFolder) {
        this(gameFolder, false);
    }

    public NovaCraft(String gameFolder, boolean touch) {
        GAME_FOLDER = gameFolder;
        TOUCH = touch;
        STATE = AppState.MENU_MAIN;
    }

    @Override
    public void create() {
        Gdx.app.log("NovaCraft", GAME_FOLDER);
        Gdx.files.absolute(GAME_FOLDER).mkdirs();
        setScreen(new MainScreen());
    }

}