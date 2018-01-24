package com.mygdx.game.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.JavaSimpleGame;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 23.01.18.
 */

public abstract class TileObject {
    public static final short PLATFORM_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short DIAMOND_BIT = 4;
    public static final short DESTROYED_BIT = 16;
    public static final short SPIKE_BIT = 32;
    public static final short OBJECT_BIT = 64;
    public static final short ENEMY_BIT = 128;



    protected World world;
    protected Map map;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;


    public  TileObject(PlayScreen screen, Rectangle bounds){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds=bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set( (bounds.getX() + bounds.getWidth() / 2)/ map.getPpm(), (bounds.getY() + bounds.getHeight() / 2) / map.getPpm() );

        body = world.createBody(bodyDef);

        shape.setAsBox( bounds.getWidth() / 2/ map.getPpm(), bounds.getHeight() / 2 / map.getPpm());
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

    }
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);

    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getTiledMap().getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * map.getPpm() / map.getTileWith()),
                (int)(body.getPosition().y *  map.getPpm() / map.getTileHeight()));
    }

}
