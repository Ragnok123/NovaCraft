package net.novatech.novacraft.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import net.novatech.novacraft.material.Items;
import net.novatech.novacraft.entity.*;
import net.novatech.novacraft.misc.Assets;

import java.io.Serializable;

import static net.novatech.novacraft.MainScreen.GP;

public class Player extends Mob implements Serializable {

    public int[] inv;
    public int invSlot;
    public int gameMode;
    public boolean swim;

    public Player(int gameMode) {
        super(0, 0, 4, 30, 1, true);
        this.gameMode = gameMode;
        inv = new int[9];
        pos = getSpawnPoint().cpy();
        swim = false;
    }

    public void respawn() {
        pos.set(getSpawnPoint());
        mov.setZero();
    }

    private Vector2 getSpawnPoint() {
        int x = 0, y;
        for (y = 0; y < GP.world.getHeight(); y++) {
            if (y == GP.world.getHeight() - 1) {
                y = 60;
                GP.world.setForeMap(x, y, 1);
                break;
            }
            if (GP.world.getForeMap(x, y) > 0 && Items.getBlock(GP.world.getForeMap(x, y)).hasCollision()) break;
        }
        return new Vector2(x * 16 + 8 - (float) getWidth() / 2, (float) y * 16 - getHeight());
    }

    public void setDir(int dir) {
        if (dir != getDir()) switchDir();
    }

    @Override
    public void ai() {
    }

    @Override
    public void changeDir() {
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float x, float y) {
        if (mov.x != 0 || Assets.plSprite[0][2].getRotation() != 0) {
            Assets.plSprite[0][2].rotate(animDelta);
            Assets.plSprite[1][2].rotate(-animDelta);
            Assets.plSprite[0][3].rotate(-animDelta);
            Assets.plSprite[1][3].rotate(animDelta);
        } else {
            Assets.plSprite[0][2].setRotation(0);
            Assets.plSprite[1][2].setRotation(0);
            Assets.plSprite[0][3].setRotation(0);
            Assets.plSprite[1][3].setRotation(0);
        }
        if (Assets.plSprite[0][2].getRotation() >= 60 || Assets.plSprite[0][2].getRotation() <= -60)
            animDelta = -animDelta;

        //back hand
        Assets.plSprite[1][2].setPosition(x - 6, y);
        Assets.plSprite[1][2].draw(spriteBatch);
        //back leg
        Assets.plSprite[1][3].setPosition(x - 6, y + 10);
        Assets.plSprite[1][3].draw(spriteBatch);
        //front leg
        Assets.plSprite[0][3].setPosition(x - 6, y + 10);
        Assets.plSprite[0][3].draw(spriteBatch);
        //head
        spriteBatch.draw(Assets.plSprite[getDir()][0], x - 2, y - 2);
        //body
        spriteBatch.draw(Assets.plSprite[getDir()][1], x - 2, y + 8);
        //item in hand
        if (inv[invSlot] > 0) {
            float handRotation = MathUtils.degRad * Assets.plSprite[0][2].getRotation();
            switch (Items.getItem(inv[invSlot]).getType()) {
                case 0:
                    Assets.blockTex[Items.getItem(inv[invSlot]).getTex()].setPosition(
                            x - 8 * MathUtils.sin(handRotation),
                            y + 6 + 8 * MathUtils.cos(handRotation));
                    Assets.blockTex[Items.getItem(inv[invSlot]).getTex()].draw(spriteBatch);
                    break;
                default:
                    Assets.itemTex[Items.getItem(inv[invSlot]).getTex()].flip((getDir() == 0), false);
                    Assets.itemTex[Items.getItem(inv[invSlot]).getTex()].setRotation(
                            -45 + getDir() * 90 + Assets.plSprite[0][2].getRotation());
                    Assets.itemTex[Items.getItem(inv[invSlot]).getTex()].setPosition(
                            x - 10 + (12 * getDir()) - 8 * MathUtils.sin(handRotation),
                            y + 2 + 8 * MathUtils.cos(handRotation));
                    Assets.itemTex[Items.getItem(inv[invSlot]).getTex()].draw(spriteBatch);
                    Assets.itemTex[Items.getItem(inv[invSlot]).getTex()].flip((getDir() == 0), false);
                    break;
            }
        }
        //front hand
        Assets.plSprite[0][2].setPosition(x - 6, y);
        Assets.plSprite[0][2].draw(spriteBatch);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(pos.x, pos.y, getWidth(), getHeight());
    }

}
