package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.JavaSimpleGame;

/**
 * Created by Iksob on 2018-01-10.
 */


public class Diamond extends InteractiveTileObject {
    public Diamond(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);

    }
}
