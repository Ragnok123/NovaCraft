package net.novatech.novacraft.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.novatech.novacraft.NovaCraft;

public class Assets {

    public static final int BLOCK_TEXTURES = 66;
    public static final int ITEM_TEXTURES = 13;

    private static GlyphLayout layout;

    public static BitmapFont mcFont;

    public static Sprite gameLogo;

    public static Texture menuBtnTex;
    public static TextureRegion[] menuBtn = new TextureRegion[3];

    public static Texture plTex;
    public static Sprite[][] plSprite = new Sprite[2][4];

    public static Texture pigTex;
    public static Sprite[][] pigSprite = new Sprite[2][2];

    public static Sprite shade;

    public static Texture terrain;
    public static Sprite[] blockTex = new Sprite[BLOCK_TEXTURES];

    public static Texture items;
    public static Sprite[] itemTex = new Sprite[ITEM_TEXTURES];

    public static Texture gui;
    public static TextureRegion invBar;
    public static TextureRegion invBarCur;
    public static TextureRegion guiCur;

    public static Texture wreckTex;
    public static TextureRegion[] wreck = new TextureRegion[10];

    public static Texture creativeTex;
    public static TextureRegion creativeInv;
    public static TextureRegion creativeScr;

    public static Texture touchGui;
    public static TextureRegion[] touchArrows = new TextureRegion[4];
    public static TextureRegion touchLMB, touchRMB;
    public static TextureRegion touchMode;
    public static TextureRegion touchSpace;

    private static void loadPlayer() {
        plTex = new Texture(Gdx.files.internal("mobs/char.png"));
        //LOOK TO LEFT
        //head
        plSprite[0][0] = new Sprite(new TextureRegion(plTex, 0, 0, 12, 12));
        plSprite[0][0].flip(false, true);
        //body
        plSprite[0][1] = new Sprite(new TextureRegion(plTex, 0, 13, 12, 12));
        plSprite[0][1].flip(false, true);
        //hand
        plSprite[0][2] = new Sprite(new TextureRegion(plTex, 25, 5, 20, 20));
        plSprite[0][2].flip(false, true);
        //leg
        plSprite[0][3] = new Sprite(new TextureRegion(plTex, 25, 27, 20, 20));
        plSprite[0][3].flip(false, true);
        //LOOK TO RIGHT
        //head
        plSprite[1][0] = new Sprite(new TextureRegion(plTex, 13, 0, 12, 12));
        plSprite[1][0].flip(false, true);
        //body
        plSprite[1][1] = new Sprite(new TextureRegion(plTex, 13, 13, 12, 12));
        plSprite[1][1].flip(false, true);
        //hand
        plSprite[1][2] = new Sprite(new TextureRegion(plTex, 37, 5, 20, 20));
        plSprite[1][2].flip(false, true);
        //leg
        plSprite[1][3] = new Sprite(new TextureRegion(plTex, 37, 27, 20, 20));
        plSprite[1][3].flip(false, true);
    }

    private static void loadPig() {
        pigTex = new Texture(Gdx.files.internal("entities/pig.png"));
        pigSprite[0][0] = new Sprite(new TextureRegion(pigTex, 0, 0, 25, 12));
        pigSprite[0][0].flip(false, true);
        pigSprite[1][0] = new Sprite(new TextureRegion(pigTex, 0, 12, 25, 12));
        pigSprite[1][0].flip(false, true);
        pigSprite[0][1] = new Sprite(new TextureRegion(pigTex, 4, 26, 12, 12));
        pigSprite[0][1].flip(false, true);
        pigSprite[1][1] = new Sprite(new TextureRegion(pigTex, 16, 26, 12, 12));
        pigSprite[1][1].flip(false, true);
    }

    public static void load() {
        mcFont = new BitmapFont(Gdx.files.internal("font.fnt"), true);
        mcFont.getData().setScale(.375f);

        layout = new GlyphLayout();

        gameLogo = new Sprite(new Texture(Gdx.files.internal("gamelogoo.png")));
        gameLogo.flip(false, true);

        menuBtnTex = new Texture(Gdx.files.internal("buttons.png"));
        for (int i = 0; i < 3; i++) {
            menuBtn[i] = new TextureRegion(menuBtnTex, 0, 20 * i, 200, 20);
            menuBtn[i].flip(false, true);
        }

        loadPlayer();
        loadPig();

        shade = new Sprite(new Texture(Gdx.files.internal("shade.png")));

        gui = new Texture(Gdx.files.internal("gui.png"));
        guiCur = new TextureRegion(gui, 0, 0, 16, 16);
        invBar = new TextureRegion(gui, 0, 16, 182, 22);
        invBarCur = new TextureRegion(gui, 0, 38, 24, 24);

        creativeTex = new Texture(Gdx.files.internal("allitems.png"));
        creativeInv = new TextureRegion(creativeTex, 0, 0, 176, 136);
        creativeInv.flip(false, true);
        creativeScr = new TextureRegion(creativeTex, 3, 137, 12, 15);
        creativeScr.flip(false, true);

        wreckTex = new Texture(Gdx.files.internal("break.png"));
        for (int i = 0; i < 10; i++) {
            wreck[i] = new TextureRegion(wreckTex, 16 * i, 0, 16, 16);
        }

        if (NovaCraft.TOUCH) {
            touchGui = new Texture(Gdx.files.internal("touch_gui.png"));
            for (int i = 0; i < 4; i++) {
                touchArrows[i] = new TextureRegion(touchGui, i * 26, 0, 26, 26);
                touchArrows[i].flip(false, true);
            }
            touchLMB = new TextureRegion(touchGui, 0, 26, 26, 26);
            touchLMB.flip(false, true);
            touchRMB = new TextureRegion(touchGui, 52, 26, 26, 26);
            touchRMB.flip(false, true);
            touchMode = new TextureRegion(touchGui, 26, 26, 26, 26);
            touchMode.flip(false, true);
            touchSpace = new TextureRegion(touchGui, 0, 52, 104, 26);
            touchSpace.flip(false, true);
        }

        terrain = new Texture(Gdx.files.internal("terrain.png"));
        for (int i = 0; i < BLOCK_TEXTURES; i++) {
            blockTex[i] = new Sprite(terrain,
                    (i % 16) * 16, (i / 16) * 16, 16, 16);
            blockTex[i].flip(false, true);
            blockTex[i].setSize(8, 8);
        }
        terrain = null;

        items = new Texture(Gdx.files.internal("items.png"));
        for (int i = 0; i < ITEM_TEXTURES; i++) {
            itemTex[i] = new Sprite(items,
                    (i % 16) * 16, (i / 16) * 16, 16, 16);
            itemTex[i].flip(false, true);
        }
        items = null;
    }

    public static int getStringWidth(String s) {
        layout.setText(mcFont, s);
        return (int) layout.width;
    }

    public static int getStringHeight(String s) {
        layout.setText(mcFont, s);
        return (int) layout.height;
    }

}
