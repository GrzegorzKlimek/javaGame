package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.JavaSimpleGame;

import com.mygdx.game.Sprites.Block;
import com.mygdx.game.Sprites.Diamond;
import com.mygdx.game.Sprites.InteractiveTileObjectFabric;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by Iksob on 2018-01-10.
 */

public class B2WorldCreator {
    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map=screen.getMap();

            InteractiveTileObjectFabric tileObjectFabric = new InteractiveTileObjectFabric();
            int [] tileObjLayersIndexes = InteractiveTileObjectFabric.objectLayers;

            for (int i = 0; i < tileObjLayersIndexes.length; i++) {
                int tileLayerObjIndex = tileObjLayersIndexes[i];
                for (MapObject object : map.getLayers().get(tileLayerObjIndex).getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    tileObjectFabric.produce(world, map, rect, tileLayerObjIndex);
                }

            }

    }
}
