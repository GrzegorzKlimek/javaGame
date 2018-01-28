package com.mygdx.game.Sprites.TileObjects.particular;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.TileObjects.InteractiveTileObject;
import com.mygdx.game.Sprites.TileObjects.TileObject;
import com.mygdx.game.Sprites.agents.protagonist.Player;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 28.01.18.
 */

public class Key extends TileObject implements InteractiveTileObject {

    public Key(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SpriteUtilities.KEY_BIT);
    }

    @Override
    public void onContact(Player player) {
        player.setIsFinishedLevel(true);
    }
}
