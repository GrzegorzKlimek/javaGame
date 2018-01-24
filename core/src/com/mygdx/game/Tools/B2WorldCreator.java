package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import com.mygdx.game.Sprites.TileObjects.TileObjectFabric;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by Iksob on 2018-01-10.
 */

public class B2WorldCreator {
    public B2WorldCreator(PlayScreen screen){


            TileObjectFabric tileObjectFabric = new TileObjectFabric();
            int [] tileObjLayersIndexes = screen.getMap().getInteractiveLayersIndexes();

            for (int i = 0; i < tileObjLayersIndexes.length; i++) {
                int tileLayerObjIndex = tileObjLayersIndexes[i];
                for (MapObject object : screen.getMap().getTiledMap().getLayers().get(tileLayerObjIndex).getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    tileObjectFabric.produce(screen, rect, tileLayerObjIndex);
                }

            }
    }
}
