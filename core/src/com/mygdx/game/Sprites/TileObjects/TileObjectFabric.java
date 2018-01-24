package com.mygdx.game.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.TileObjects.particular.Diamond;
import com.mygdx.game.Sprites.TileObjects.particular.Platform;
import com.mygdx.game.Sprites.TileObjects.particular.Spike;

/**
 * Created by grzegorz on 10.01.18.
 */

public class TileObjectFabric {


    public static final int TILED_PLATFORM_LAYER_INDEX = 2;
    public static final int TILED_DIAMONDS_LAYER_INDEX = 3;
    public static final int TILED_SPIKES_LAYER_INDEX = 4;
    public static final int BODIES_LAYER_INDEX = 1;
    public static final int BACKGROUND_LAYER_INDEX = 0;



    public static final int [] objectLayers = { TILED_PLATFORM_LAYER_INDEX, TILED_DIAMONDS_LAYER_INDEX,  TILED_SPIKES_LAYER_INDEX,
           };


    public TileObject produce (World world, TiledMap map, Rectangle bounds, int indexOfTiledObjectLayer) {
        switch (indexOfTiledObjectLayer) {

            case TILED_PLATFORM_LAYER_INDEX :
                return  new Platform(world, map, bounds);

            case TILED_SPIKES_LAYER_INDEX :
                return  new Spike(world, map, bounds);
            case TILED_DIAMONDS_LAYER_INDEX:
                return  new Diamond(world, map, bounds);

            default:
                    return null;
        }

    }
}
