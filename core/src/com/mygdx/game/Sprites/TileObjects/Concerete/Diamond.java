package com.mygdx.game.Sprites.TileObjects.Concerete;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.JavaSimpleGame;
import com.mygdx.game.Sprites.TileObjects.InteractiveTileObject;

/**
 * Created by Iksob on 2018-01-10.
 */


public class Diamond extends InteractiveTileObject {
    public Diamond(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(DIAMOND_BIT);

    }

    @Override
    public void onHeadHit() {

        Gdx.app.log("Diamond", "Collision");
    }

    @Override
    public String toString() {
        return "Diamond";
    }
}
