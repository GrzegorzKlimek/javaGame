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
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;


    public  TileObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map=map;
        this.bounds=bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set( (bounds.getX() + bounds.getWidth() / 2)/ JavaSimpleGame.PPM, (bounds.getY() + bounds.getHeight() / 2) / JavaSimpleGame.PPM );

        body = world.createBody(bdef);

        shape.setAsBox( bounds.getWidth() / 2/ JavaSimpleGame.PPM, bounds.getHeight() / 2 / JavaSimpleGame.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);

    }
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * JavaSimpleGame.PPM / 16),
                (int)(body.getPosition().y * JavaSimpleGame.PPM / 16));
    }

}
