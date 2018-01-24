package com.mygdx.game.Sprites.TileObjects.particular;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.TileObjects.InteractiveTileObject;

/**
 * Created by grzegorz on 10.01.18.
 */

public class Spike extends InteractiveTileObject {
    public Spike(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SPIKE_BIT);
    }

    @Override
    public void onHeadHit() {

        Gdx.app.log("Spike", "Collision");
    }

    @Override
    public String toString() {
        return "Spike";
    }

}
