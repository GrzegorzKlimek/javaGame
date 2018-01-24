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

}
