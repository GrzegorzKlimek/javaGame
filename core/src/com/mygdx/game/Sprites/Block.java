package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Iksob on 2018-01-10.
 */

public class Block extends InteractiveTileObject {
    public Block(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
    }
}
