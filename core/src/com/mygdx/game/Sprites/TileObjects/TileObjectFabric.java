package com.mygdx.game.Sprites.TileObjects;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.TileObjects.particular.Diamond;
import com.mygdx.game.Sprites.TileObjects.particular.Platform;
import com.mygdx.game.Sprites.TileObjects.particular.Spike;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 10.01.18.
 */

public class TileObjectFabric {


    public TileObject produce (PlayScreen screen, Rectangle bounds, int indexOfTiledObjectLayer) {
        switch (indexOfTiledObjectLayer) {

            case Map.TILED_PLATFORM_LAYER_INDEX:
                return  new Platform(screen, bounds);
            case Map.TILED_SPIKES_LAYER_INDEX :
                return  new Spike(screen, bounds);
            case Map.TILED_DIAMONDS_LAYER_INDEX:
                return  new Diamond(screen, bounds);

            default:
                    return null;
        }

    }

}
