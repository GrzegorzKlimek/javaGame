package com.mygdx.game.Sprites.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.AndroidAdventuresGame;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 23.01.18.
 */

public abstract class TileObject {



    protected World world;
    protected Map map;
    protected AndroidAdventuresGame game;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;


    public  TileObject(PlayScreen screen, Rectangle bounds){
        this.game = screen.getGame();
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds=bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set( (bounds.getX() + bounds.getWidth() / 2)/ AndroidAdventuresGame.PPM, (bounds.getY() + bounds.getHeight() / 2) / AndroidAdventuresGame.PPM );

        body = world.createBody(bodyDef);

        shape.setAsBox( bounds.getWidth() / 2/ AndroidAdventuresGame.PPM, bounds.getHeight() / 2 / AndroidAdventuresGame.PPM);
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
        return layer.getCell((int)(body.getPosition().x * AndroidAdventuresGame.PPM / map.getTileWith()),
                (int)(body.getPosition().y *  AndroidAdventuresGame.PPM / map.getTileHeight()));
    }

}
