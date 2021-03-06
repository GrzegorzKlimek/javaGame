package com.mygdx.game.Sprites.TileObjects.particular;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.TileObjects.InteractiveTileObject;
import com.mygdx.game.Sprites.TileObjects.TileObject;
import com.mygdx.game.Sprites.agents.protagonist.Player;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by Iksob on 2018-01-10.
 */


public class Diamond extends TileObject implements InteractiveTileObject {
    public Diamond(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setSensor(true);
        fixture.setUserData(this);
        setCategoryFilter(SpriteUtilities.DIAMOND_BIT);

    }

    @Override
    public void onContact(Player player) {

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
