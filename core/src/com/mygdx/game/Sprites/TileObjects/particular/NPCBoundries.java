package com.mygdx.game.Sprites.TileObjects.particular;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.TileObjects.TileObject;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 29.01.18.
 */

public class NPCBoundries extends TileObject {
    public NPCBoundries(PlayScreen screen, Rectangle bounds){

        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SpriteUtilities.NPC_BOUNDRIES_BIT);
    }
}
