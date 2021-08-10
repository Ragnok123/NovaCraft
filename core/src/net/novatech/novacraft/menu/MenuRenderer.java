package net.novatech.novacraft.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import net.novatech.novacraft.material.*;
import net.novatech.novacraft.world.*;
import net.novatech.novacraft.menu.objects.Button;
import net.novatech.novacraft.misc.*;

public class MenuRenderer extends Renderer {

    public Array<Button> menuMainBtns;
    public Array<Button> menuNGBtns;
    public Array<Button> createWorldsBtns;
    public Array<Button> loadWorldsBtns;

    public MenuRenderer(int width) {
        super(width, width * ((float) MainScreen.getHeight() / MainScreen.getWidth()));
        //main menu
        menuMainBtns = new Array<Button>();
        menuMainBtns.add(new Button("New singleplayer game", getWidth() / 2 - 100, getHeight() / 4));
        menuMainBtns.add(new Button("Load singleplayer game", getWidth() / 2 - 100, getHeight() / 4 + 30));
        menuMainBtns.add(new Button("Multiplayer game", getWidth() / 2 - 100, getHeight() / 4 + 60, 0));
        menuMainBtns.add(new Button("Quit", getWidth() / 2 - 100, getHeight() / 4 + 90));
        //new game menu
        menuNGBtns = new Array<Button>();
        menuNGBtns.add(new Button("Survival", getWidth() / 2 - 100, getHeight() / 4, 0));
        menuNGBtns.add(new Button("Creative", getWidth() / 2 - 100, getHeight() / 4 + 30));
        menuNGBtns.add(new Button("Back", getWidth() / 2 - 100, getHeight() / 4 + 60));
        //create new world slot
        createWorldsBtns.add(new Button("World 1", getWidth() / 2 - 100, getHeight() / 4, GameSaver.exists(1) ? 0 : 1));
        createWorldsBtns.add(new Button("World 2", getWidth() / 2 - 100, getHeight() / 4 + 30,GameSaver.exists(2) ? 0 : 1));
        createWorldsBtns.add(new Button("World 3", getWidth() / 2 - 100, getHeight() / 4 + 60,GameSaver.exists(3) ? 0 : 1));
        createWorldsBtns.add(new Button("World 4", getWidth() / 2 - 100, getHeight() / 4 + 90,GameSaver.exists(4) ? 0 : 1));
        createWorldsBtns.add(new Button("World 5", getWidth() / 2 - 100, getHeight() / 4 + 120,GameSaver.exists(5) ? 0 : 1));
        //load worlds
        loadWorldsBtns.add(new Button("World 1", getWidth() / 2 - 100, getHeight() / 4, GameSaver.exists(1) ? 1 : 0));
        loadWorldsBtns.add(new Button("World 2", getWidth() / 2 - 100, getHeight() / 4 + 30, GameSaver.exists(2) ? 1 : 0));
        loadWorldsBtns.add(new Button("World 3", getWidth() / 2 - 100, getHeight() / 4 + 60, GameSaver.exists(3) ? 1 : 0));
        loadWorldsBtns.add(new Button("World 4", getWidth() / 2 - 100, getHeight() / 4 + 90, GameSaver.exists(4) ? 1 : 0));
        loadWorldsBtns.add(new Button("World 5", getWidth() / 2 - 100, getHeight() / 4 + 120, GameSaver.exists(5) ? 1 : 0));
    }

    public void buttonClicked(Button button) {
        if (button.getLabel().toLowerCase().equals("new singleplayer game")) {
            NovaCraft.STATE = AppState.MENU_NEW_WORLD;
        } else if (button.getLabel().toLowerCase().equals("load singleplayer game")) {
            NovaCraft.STATE = AppState.MENU_LOAD_WORLD;
        } else if (button.getLabel().toLowerCase().equals("quit")) {
            Gdx.app.exit();
        } else if (button.getLabel().toLowerCase().equals("survival")) {
            MainScreen.NEW_GAME_MODE = 0;
            NovaCraft.STATE = AppState.GOTO_NEW_GAME;
        } else if (button.getLabel().toLowerCase().equals("creative")) {
            MainScreen.NEW_GAME_MODE = 1;
            NovaCraft.STATE = AppState.GOTO_NEW_GAME;
        } else if (button.getLabel().toLowerCase().equals("back")) {
            NovaCraft.STATE = AppState.MENU_MAIN;
        } else if(button.getLabel().toLowerCase().equals("world 1")){
            NovaCraft.WORLD_STATE = WorldState.WORLD_1;
            switch(NovaCraft.STATE){
                case MENU_NEW_WORLD:
                    NovaCraft.STATE = AppState.MENU_NEW_GAME;
                    break;
                case MENU_LOAD_WORLD:
                    NovaCraft.STATE = AppState.GOTO_LOAD_GAME;
                    break;
            }
        } else if(button.getLabel().toLowerCase().equals("world 2")){
            NovaCraft.WORLD_STATE = WorldState.WORLD_2;
            switch(NovaCraft.STATE){
                case MENU_NEW_WORLD:
                    NovaCraft.STATE = AppState.MENU_NEW_GAME;
                    break;
                case MENU_LOAD_WORLD:
                    NovaCraft.STATE = AppState.GOTO_LOAD_GAME;
                    break;
            }
        } else if(button.getLabel().toLowerCase().equals("world 3")){
            NovaCraft.WORLD_STATE = WorldState.WORLD_3;
            switch(NovaCraft.STATE){
                case MENU_NEW_WORLD:
                    NovaCraft.STATE = AppState.MENU_NEW_GAME;
                    break;
                case MENU_LOAD_WORLD:
                    NovaCraft.STATE = AppState.GOTO_LOAD_GAME;
                    break;
            }
        } else if(button.getLabel().toLowerCase().equals("world 4")){
            NovaCraft.WORLD_STATE = WorldState.WORLD_4;
            switch(NovaCraft.STATE){
                case MENU_NEW_WORLD:
                    NovaCraft.STATE = AppState.MENU_NEW_GAME;
                    break;
                case MENU_LOAD_WORLD:
                    NovaCraft.STATE = AppState.GOTO_LOAD_GAME;
                    break;
            }
        } else if(button.getLabel().toLowerCase().equals("world 5")){
            NovaCraft.WORLD_STATE = WorldState.WORLD_5;
            switch(NovaCraft.STATE){
                case MENU_NEW_WORLD:
                    NovaCraft.STATE = AppState.MENU_NEW_GAME;
                    break;
                case MENU_LOAD_WORLD:
                    NovaCraft.STATE = AppState.GOTO_LOAD_GAME;
                    break;
            }
        }
    }

    private void drawButton(Button button) {
        spriter.draw(Assets.menuBtn[button.getType()], button.getX(), button.getY());
        setFontColor(255, 255, 255);
        drawString(button.getLabel(),
                (button.getX() + button.getWidth() / 2) - (float) Assets.getStringWidth(button.getLabel()) / 2,
                (button.getY() + button.getHeight() / 2) - (float) Assets.getStringHeight(button.getLabel()) / 2);
    }

    private void drawButtons(Array<Button> buttons) {
        for (Button button : buttons) {
            if (button.getType() > 0) {
                if (button.getRect().contains(Gdx.input.getX() * getWidth() / MainScreen.getWidth(),
                        Gdx.input.getY() * getHeight() / MainScreen.getHeight()) &&
                        (!NovaCraft.TOUCH || Gdx.input.isTouched()))
                    button.setType(2);
                else button.setType(1);
            }
            drawButton(button);
        }
    }

    private void drawLabel(String str) {
        drawString(str);
    }

    @Override
    public void render() {
        spriter.begin();
        for (int x = 0; x <= getWidth() / 16; x++) {
            for (int y = 0; y <= getHeight() / 16; y++) {
                spriter.draw(Assets.blockTex[GameItems.getBlock(3).getTex()], x * 16, y * 16);
                spriter.draw(Assets.shade, x * 16, y * 16);
            }
        }
        spriter.draw(Assets.gameLogo, getWidth() / 2 - Assets.gameLogo.getWidth() / 2, 8);

        switch (NovaCraft.STATE) {
            case MENU_MAIN:
                drawButtons(menuMainBtns);
                break;
            case MENU_NEW_GAME:
                drawButtons(menuNGBtns);
                break;
            case GOTO_NEW_GAME:
            case GOTO_LOAD_GAME:
                drawLabel("Generating World...");
                break;
            case GOTO_MENU:
                drawLabel("Saving Game...");
                break;
        }

        drawString("NovaCraft " + NovaCraft.VERSION, 0,
                getHeight() - Assets.getStringHeight("NovaCraft " + NovaCraft.VERSION) * 1.5f);
        spriter.end();

        switch (NovaCraft.STATE) {
            case GOTO_NEW_GAME:
                NovaCraft.STATE = AppState.NEW_GAME;
                break;
            case GOTO_LOAD_GAME:
                NovaCraft.STATE = AppState.LOAD_GAME;
                break;
            case GOTO_MENU:
                NovaCraft.STATE = AppState.SAVE_GAME;
                break;
        }

    }
}
