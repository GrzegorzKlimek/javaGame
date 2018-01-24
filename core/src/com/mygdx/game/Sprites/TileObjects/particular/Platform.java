package com.mygdx.game.Sprites.TileObjects.particular;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.TileObjects.TileObject;

/**
 * Created by grzegorz on 10.01.18.
 */

public class Platform extends TileObject {

    public Platform(World world, TiledMap map, Rectangle bounds){

        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(PLATFORM_BIT);
    }

    @Override
    public String toString() {
        return "Platform";
    }

}
