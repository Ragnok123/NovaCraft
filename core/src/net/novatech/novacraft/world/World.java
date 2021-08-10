package net.novatech.novacraft.world;

import net.novatech.novacraft.material.Drop;

public class GameWorld {

    private int WIDTH, HEIGHT;
    private int[][] foreMap;
    private int[][] backMap;

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public float getWidthPx() {
        return WIDTH * 16f;
    }

    public float getHeightPx() {
        return HEIGHT * 16f;
    }

    public int[][] getFullForeMap() {
        return foreMap;
    }

    public int[][] getFullBackMap() {
        return backMap;
    }

    private int transformX(int x) {
        x = x % getWidth();
        if (x < 0) x = getWidth() - Math.abs(x);
        return x;
    }

    private int getMap(int x, int y, int layer) {
        int map = 0;
        try {
            x = transformX(x);
            map = (layer == 0) ? foreMap[x][y] : backMap[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return map;
    }

    private void setMap(int x, int y, int layer, int value) {
        try {
            x = transformX(x);
            if (layer == 0) foreMap[x][y] = value;
            else backMap[x][y] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public int getForeMap(int x, int y) {
        return getMap(x, y, 0);
    }

    public void setForeMap(int x, int y, int value) {
        setMap(x, y, 0, value);
    }

    public int getBackMap(int x, int y) {
        return getMap(x, y, 1);
    }

    public void setBackMap(int x, int y, int value) {
        setMap(x, y, 1, value);
    }

    private void placeSlab(int x, int y, int value) {
        switch (value) {
            case 51:
                setForeMap(x, y, 52);
                break;
            case 53:
                setForeMap(x, y, 21);
                break;
            case 54:
                setForeMap(x, y, 5);
                break;
            case 55:
                setForeMap(x, y, 4);
                break;
            case 56:
                setForeMap(x, y, 28);
                break;
            case 58:
                setForeMap(x, y, 57);
                break;
        }
    }

    public void placeToForeground(int x, int y, int value) {
        if (getForeMap(x, y) == 0 || value == 0 || !Items.getBlock(getForeMap(x, y)).hasCollision()) {
            setForeMap(x, y, value);
        } else if (Items.isSlab(value) && getForeMap(x, y) == value) {
            placeSlab(x, y, value);
        }
        GameProc.UPD_X = x - 8;
        GameProc.UPD_Y = y - 8;
        GameProc.DO_UPD = true;
    }

    public void placeToBackground(int x, int y, int value) {
        if (value == 0 || (getBackMap(x, y) == 0 && Items.getBlock(value).hasCollision()) &&
                (!Items.getBlock(value).isTransparent() || value == 18)) {
            setBackMap(x, y, value);
        }
    }

    public void destroyForeMap(int x, int y, GameProc gp) {
        if (Items.getBlock(getForeMap(x, y)).getDrop() > 0)
            gp.drops.add(new Drop(transformX(x) * 16 + 4, y * 16 + 4, Items.getBlock(getForeMap(x, y)).getDrop()));
        placeToForeground(x, y, 0);
    }

    public void destroyBackMap(int x, int y, GameProc gp) {
        if (Items.getBlock(getBackMap(x, y)).getDrop() > 0)
            gp.drops.add(new Drop(transformX(x) * 16 + 4, y * 16 + 4, Items.getBlock(getBackMap(x, y)).getDrop()));
        placeToBackground(x, y, 0);
    }

    public void generate(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        WorldGen.genWorld(WIDTH, HEIGHT);
        foreMap = WorldGen.getForeMap();
        backMap = WorldGen.getBackMap();
        WorldGen.clear();
    }

    public void setMaps(int[][] foreMap, int[][] backMap) {
        this.foreMap = foreMap.clone();
        this.backMap = backMap.clone();
        WIDTH = foreMap.length;
        HEIGHT = foreMap[0].length;
    }

}
