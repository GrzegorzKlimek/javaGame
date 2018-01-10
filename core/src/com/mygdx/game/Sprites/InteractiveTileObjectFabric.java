package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by grzegorz on 10.01.18.
 */

public class InteractiveTileObjectFabric {

    public static final int TILED_GROUND_LAYER_INDEX = 1;
    public static final int TILED_PLATFORM_LAYER_INDEX = 2;
    public static final int TILED_LADDER_LAYER_INDEX = 4;
    public static final int TILED_SPIKES_LAYER_INDEX = 5;
    public static final int TILED_UPPER_MARGIN_LAYER_INDEX = 6;


    public static final int [] objectLayers = {TILED_GROUND_LAYER_INDEX, TILED_PLATFORM_LAYER_INDEX, TILED_LADDER_LAYER_INDEX, TILED_SPIKES_LAYER_INDEX,
            TILED_UPPER_MARGIN_LAYER_INDEX};


    public InteractiveTileObject produce (World world, TiledMap map, Rectangle bounds, int indexOfTiledObjectLayer) {
        InteractiveTileObject result;
        switch (indexOfTiledObjectLayer) {
            case TILED_GROUND_LAYER_INDEX :
                return  new Block(world, map, bounds);
            case TILED_PLATFORM_LAYER_INDEX :
                return  new Platform(world, map, bounds);
            case TILED_LADDER_LAYER_INDEX :
                return  new Ladder(world, map, bounds);
            case TILED_SPIKES_LAYER_INDEX :
                return  new Spike(world, map, bounds);
            case TILED_UPPER_MARGIN_LAYER_INDEX :
                return  new UpperMargin(world, map, bounds);
            default:
                    return null;
        }

    }
}
