package com.mygdx.game.Sprites.TileObjects;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.screens.PlayScreen;


/**
 * Created by Iksob on 2018-01-10.
 */

public abstract class InteractiveTileObject extends  TileObject {

    protected InteractiveTileObject (PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
    }

    public abstract void onHeadHit();

}
