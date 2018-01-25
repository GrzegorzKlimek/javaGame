package com.mygdx.game.Sprites.TileObjects.particular;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.TileObjects.InteractiveTileObject;
import com.mygdx.game.Sprites.TileObjects.TileObject;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by Iksob on 2018-01-10.
 */


public class Diamond extends InteractiveTileObject {
    public Diamond(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SpriteUtilities.DIAMOND_BIT);

    }

    @Override
    public void onHeadHit() {

        Gdx.app.log("Diamond", "Collision");
        setCategoryFilter(SpriteUtilities.DESTROYED_BIT);
        getCell().setTile(null);
        game.addScore(1);
    }

    @Override
    public String toString() {
        return "Diamond";
    }
}
