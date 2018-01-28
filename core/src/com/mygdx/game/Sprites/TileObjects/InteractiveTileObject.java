package com.mygdx.game.Sprites.TileObjects;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Sprites.agents.protagonist.Player;
import com.mygdx.game.screens.PlayScreen;


/**
 * Created by Iksob on 2018-01-10.
 */

public interface InteractiveTileObject {

      void onContact(Player player);

}
