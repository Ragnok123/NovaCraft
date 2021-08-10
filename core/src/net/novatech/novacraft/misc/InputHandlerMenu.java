package net.novatech.novacraft.misc;

import com.badlogic.gdx.InputProcessor;
import net.novatech.novacraft.NovaCraft;
import net.novatech.novacraft.menu.MenuRenderer;
import net.novatech.novacraft.menu.objects.Button;

public class InputHandlerMenu implements InputProcessor {

    private MenuRenderer menuRenderer;

    public InputHandlerMenu(MenuRenderer menuRenderer) {
        this.menuRenderer = menuRenderer;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int mb) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int mb) {
        screenX *= menuRenderer.getWidth() / MainScreen.getWidth();
        screenY *= menuRenderer.getHeight() / MainScreen.getHeight();
        switch (NovaCraft.STATE) {
            case MENU_MAIN:
                for (Button button : menuRenderer.menuMainBtns) {
                    if (button.getRect().contains(screenX, screenY) && button.getType() > 0) {
                        menuRenderer.buttonClicked(button);
                        break;
                    }
                }
                break;
            case MENU_NEW_GAME:
                for (Button button : menuRenderer.menuNGBtns) {
                    if (button.getRect().contains(screenX, screenY) && button.getType() > 0) {
                        menuRenderer.buttonClicked(button);
                        break;
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
