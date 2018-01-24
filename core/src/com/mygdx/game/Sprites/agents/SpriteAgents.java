package com.mygdx.game.Sprites.agents;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 24.01.18.
 */

public abstract class SpriteAgents extends Sprite {
    public enum STATE {DEATH, FLYING, STANDING, RUNNING};

    public   Vector2 startPosition ;
    public   Vector2 deathTexturePos ;
    public  Vector2 runningTexturePos ;
    public  Vector2 flyingTexturePos ;
    public  Vector2 standingTexturePos ;

    public  int textureWith;
    public  int textureHeight;
    protected  int shapeRadiusOfBody;
    protected World world;
    protected  Map map;
    public Body b2body;

    public  SpriteAgents (PlayScreen screen, Vector2 startPosition, String firstRegion, int shapeRadius, int textureWith, int textureHeight) {
        super(screen.getAtlas().findRegion(firstRegion));
        map = screen.getMap();
        world = screen.getWorld();
        this.shapeRadiusOfBody = shapeRadius;
        this.textureWith = textureWith;
        this.textureHeight = textureHeight;
        this.startPosition = startPosition;

    }

    protected void defineAgent(Vector2 startPosition , short agentBit, String userData) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startPosition.x / map.getPpm() , startPosition.y / map.getPpm());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(shapeRadiusOfBody / map.getPpm());
        fixtureDef.filter.categoryBits = agentBit;
        fixtureDef.filter.maskBits = SpriteUtilities.PLATFORM_BIT | SpriteUtilities.DIAMOND_BIT | SpriteUtilities.SPIKE_BIT;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(userData);

    }
    protected void defineTexturesPositions (Vector2 deathTexturePosition, Vector2 runningTexturePosition,
                                            Vector2 flyingTexturePosition, Vector2 standingTexturePosition) {
        this.standingTexturePos = standingTexturePosition;
        this.deathTexturePos = deathTexturePosition;
        this.runningTexturePos = runningTexturePosition;
        this.flyingTexturePos = flyingTexturePosition;
    }



}
