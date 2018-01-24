package com.mygdx.game.Sprites.TileObjects.particular;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.TileObjects.TileObject;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 10.01.18.
 */

public class Platform extends TileObject {

    public Platform(PlayScreen screen, Rectangle bounds){

        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SpriteUtilities.PLATFORM_BIT);
    }

    @Override
    public String toString() {
        return "Platform";
    }

}
