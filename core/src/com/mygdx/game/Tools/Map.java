package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by grzegorz on 24.01.18.
 */

public class Map extends TiledMap {
    private String pathToTileMap = "level1.tmx";
    private TiledMap tileMap;
    private   int width = 400;
    private   int height = 250;
    private   float ppm = 100;
    private   int tileWith = 16;
    private   int tileHeight = 16;
    public static final int BACKGROUND_LAYER_INDEX = 0;
    public static final int BODIES_LAYER_INDEX = 1;
    public static final int TILED_PLATFORM_LAYER_INDEX = 2;
    public static final int TILED_DIAMONDS_LAYER_INDEX = 3;
    public static final int TILED_SPIKES_LAYER_INDEX = 4;
    private   int [] interactiveLayersIndexes = {TILED_PLATFORM_LAYER_INDEX, TILED_DIAMONDS_LAYER_INDEX, TILED_SPIKES_LAYER_INDEX};



    public Map () {

        tileMap = new TmxMapLoader().load(pathToTileMap);
    }

    public int getWidth() {

        return width;
    }
    public int getHeight() {

        return height;
    }

    public float getPpm() {

        return ppm;
    }

    public int getTileWith() {

        return tileWith;
    }

    public int getTileHeight() {

        return tileHeight;
    }


    public TiledMap getTiledMap () {
        return tileMap;
    }

    public int[] getInteractiveLayersIndexes() {
        return interactiveLayersIndexes;
    }
}
