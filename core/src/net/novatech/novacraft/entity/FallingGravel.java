package net.novatech.novacraft.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import net.novatech.novacraft.material.Items;
import net.novatech.novacraft.misc.Assets;

public class FallingGravel extends Mob {

    public FallingGravel(float x, float y) {
        super(x, y, 16, 16, 0);
        mov = new Vector2(0, 1);
    }

    @Override
    public void ai() {
    }

    @Override
    public void changeDir() {
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float x, float y) {
        spriteBatch.draw(Assets.blockTex[Items.getBlock(11).getTex()], x, y);
    }

    @Override
    public int getType() {
        return 11;
    }

}
