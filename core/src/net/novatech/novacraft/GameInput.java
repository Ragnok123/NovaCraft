package net.novatech.novacraft;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;
import net.novatech.novacraft.entities.Pig;
import net.novatech.novacraft.misc.*;

import static net.novatech.novacraft.MainScreen.GP;

public class GameInput {

    private boolean checkSwim() {
        return Items.isFluid(GP.world.getForeMap(GP.player.getMapX(), GP.player.getMapY()));
    }

    private boolean insideCreativeInv(int screenX, int screenY) {
        return (screenX > GP.renderer.getWidth() / 2 - Assets.creativeInv.getRegionWidth() / 2 &&
                screenX < GP.renderer.getWidth() / 2 + Assets.creativeInv.getRegionWidth() / 2 &&
                screenY > GP.renderer.getHeight() / 2 - Assets.creativeInv.getRegionHeight() / 2 &&
                screenY < GP.renderer.getHeight() / 2 + Assets.creativeInv.getRegionHeight() / 2);
    }

    private void wasdPressed(int keycode) {
        if (GP.ctrlMode == 0 || !NovaCraft.TOUCH) {
            switch (keycode) {
                case Input.Keys.A:
                    GP.player.mov.x = -GamePhysics.PL_SPEED;
                    GP.player.setDir(0);
                    if (NovaCraft.TOUCH && checkSwim()) GP.player.swim = true;
                    break;
                case Input.Keys.D:
                    GP.player.mov.x = GamePhysics.PL_SPEED;
                    GP.player.setDir(1);
                    if (NovaCraft.TOUCH && checkSwim()) GP.player.swim = true;
                    break;
            }
        } else {
            switch (keycode) {
                case Input.Keys.A:
                    GP.curX--;
                    break;
                case Input.Keys.D:
                    GP.curX++;
                    break;
                case Input.Keys.W:
                    GP.curY--;
                    break;
                case Input.Keys.S:
                    GP.curY++;
                    break;
            }
            GP.blockDmg = 0;
        }
    }

    public void keyDown(int keycode) {
        GP.isKeyDown = true;
        GP.keyDownCode = keycode;
        if (keycode == Input.Keys.W || keycode == Input.Keys.A ||
                keycode == Input.Keys.S || keycode == Input.Keys.D) {
            wasdPressed(keycode);
        } else switch (keycode) {
            case Input.Keys.ALT_LEFT:
                if (NovaCraft.TOUCH) {
                    GP.ctrlMode++;
                    if (GP.ctrlMode > 1) GP.ctrlMode = 0;
                }
                break;

            case Input.Keys.SPACE:
                if (checkSwim()) {
                    GP.player.swim = true;
                } else if (GP.player.canJump) {
                    GP.player.mov.add(0, -7);
                } else if (!GP.player.flyMode && GP.player.gameMode == 1) {
                    GP.player.flyMode = true;
                    GP.player.mov.y = 0;
                } else if (GP.player.flyMode) {
                    GP.player.mov.y = -GamePhysics.PL_SPEED;
                }
                break;

            case Input.Keys.CONTROL_LEFT:
                GP.player.mov.y = GamePhysics.PL_SPEED;
                break;

            case Input.Keys.E:
                if (NovaCraft.STATE == AppState.GAME_PLAY) switch (GP.player.gameMode) {
                    case 0:
                        //TODO survival inv
                        break;
                    case 1:
                        NovaCraft.STATE = AppState.GAME_CREATIVE_INV;
                        break;
                }
                else NovaCraft.STATE = AppState.GAME_PLAY;
                break;

            case Input.Keys.G:
                GP.mobs.add(new Pig(GP.curX * 16, GP.curY * 16));
                break;

            case Input.Keys.Q:
                GP.world.placeToForeground(GP.curX, GP.curY, 8);
                break;

            case Input.Keys.ESCAPE:
            case Input.Keys.BACK:
                NovaCraft.STATE = AppState.GOTO_MENU;
                break;

            case Input.Keys.F1:
                MainScreen.SHOW_DEBUG = !MainScreen.SHOW_DEBUG;
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                GP.player.mov.x = 0;
                if (NovaCraft.TOUCH && GP.player.swim) GP.player.swim = false;
                break;

            case Input.Keys.SPACE:
            case Input.Keys.CONTROL_LEFT:
                if (GP.player.flyMode) GP.player.mov.y = 0;
                if (GP.player.swim) GP.player.swim = false;
                break;
        }
    }

    public void touchDown(int screenX, int screenY, int button) {
        GP.touchDownTime = TimeUtils.millis();
        GP.isTouchDown = true;
        GP.touchDownBtn = button;
        GP.touchDownX = screenX;
        GP.touchDownY = screenY;
    }

    public void touchUp(int screenX, int screenY, int button) {
        if (NovaCraft.TOUCH && GP.isKeyDown) {
            keyUp(GP.keyDownCode);
            GP.isKeyDown = false;
        }
        if (GP.isTouchDown) {
            if (NovaCraft.STATE == AppState.GAME_CREATIVE_INV && insideCreativeInv(screenX, screenY)) {
                int ix = (int) (screenX - (GP.renderer.getWidth() / 2 - Assets.creativeInv.getRegionWidth() / 2 + 8)) / 18;
                int iy = (int) (screenY - (GP.renderer.getHeight() / 2 - Assets.creativeInv.getRegionHeight() / 2 + 18)) / 18;
                int item = GP.creativeScroll * 8 + (ix + iy * 8);
                if (ix >= 8 || ix < 0 || iy < 0 || iy >= 5) item = -1;
                if (item >= 0 && item < Items.getItemsSize()) {
                    for (int i = 8; i > 0; i--) {
                        GP.player.inv[i] = GP.player.inv[i - 1];
                    }
                    GP.player.inv[0] = item;
                }
            } else if (NovaCraft.STATE == AppState.GAME_CREATIVE_INV) {
                NovaCraft.STATE = AppState.GAME_PLAY;
            } else if (screenY < Assets.invBar.getRegionHeight() &&
                    screenX > GP.renderer.getWidth() / 2 - Assets.invBar.getRegionWidth() / 2 &&
                    screenX < GP.renderer.getWidth() / 2 + Assets.invBar.getRegionWidth() / 2) {
                GP.player.invSlot = (int) ((screenX - (GP.renderer.getWidth() / 2 - Assets.invBar.getRegionWidth() / 2)) / 20);
            } else if (button == Input.Buttons.RIGHT) {
                GP.useItem(GP.curX, GP.curY,
                        GP.player.inv[GP.player.invSlot], false);
            } else if (button == Input.Buttons.LEFT) {
                GP.blockDmg = 0;
            }
        }
        GP.isTouchDown = false;
    }

    public void touchDragged(int screenX, int screenY) {
        if (NovaCraft.STATE == AppState.GAME_CREATIVE_INV && Math.abs(screenY - GP.touchDownY) > 16) {
            if (insideCreativeInv(screenX, screenY)) {
                GP.creativeScroll -= (screenY - GP.touchDownY) / 16;
                GP.touchDownX = screenX;
                GP.touchDownY = screenY;
                if (GP.creativeScroll < 0) GP.creativeScroll = 0;
                if (GP.creativeScroll > GP.maxCreativeScroll)
                    GP.creativeScroll = GP.maxCreativeScroll;
            }
        }
    }

    public void scrolled(int amount) {
        switch (NovaCraft.STATE) {
            case GAME_PLAY:
                GP.player.invSlot += amount;
                if (GP.player.invSlot < 0) GP.player.invSlot = 8;
                if (GP.player.invSlot > 8) GP.player.invSlot = 0;
                break;
            case GAME_CREATIVE_INV:
                GP.creativeScroll += amount;
                if (GP.creativeScroll < 0) GP.creativeScroll = 0;
                if (GP.creativeScroll > GP.maxCreativeScroll)
                    GP.creativeScroll = GP.maxCreativeScroll;
                break;
        }
    }

}
