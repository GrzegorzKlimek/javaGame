package com.mygdx.game.Sprites.agents;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.AndroidAdventures;
import com.mygdx.game.Sprites.B2DSteeringEntity;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 24.01.18.
 */

public abstract class SpriteAgent extends Sprite {
    public enum STATE {DEATH, FLYING, STANDING, RUNNING};
    protected STATE currentState;
    protected STATE previousState;
    protected TextureRegion agentStand;
    protected Animation<TextureRegion> agentRun;

    public   Vector2 startPosition ;
    public   Vector2 deathTexturePos ;
    public  Vector2 runningTexturePos ;
    public  Vector2 flyingTexturePos ;
    public  Vector2 standingTexturePos ;

    public   int textureRegionBoundsWith;
    public  int textureRegionBoundsHeight;
    protected Vector2 sizeOfSprite;
    protected  Vector2 sizeOfB2DBody;
    protected World world;
    protected  Map map;
    public B2DSteeringEntity steerableB2body;
    protected float stateTimer;
    protected boolean runningRight;

    public SpriteAgent(PlayScreen screen, Vector2 startPosition, String firstRegion, Vector2 sizeOfSprite, Vector2 sizeOfB2DBody, Vector2 boundsOfTextureRegion) {
        super(screen.getAtlas().findRegion(firstRegion));
        map = screen.getMap();
        world = screen.getWorld();
        this.sizeOfB2DBody = sizeOfB2DBody;
        this.sizeOfSprite = sizeOfSprite;
        this.textureRegionBoundsWith = (int)boundsOfTextureRegion.x;
        this.textureRegionBoundsHeight =  (int) boundsOfTextureRegion.y;
        this.startPosition = startPosition;
        setBounds(0,0, sizeOfSprite.x/ AndroidAdventures.PPM, sizeOfSprite.y/AndroidAdventures.PPM);


    }
    protected abstract int getActionFramesNumber(STATE actionOrStateOfPlayer);
    protected abstract TextureRegion getFrame (float deltaTime);

    protected abstract void defineAgent(Vector2 startPosition , short agentBit, String userData);

    public Body getBody () {
        return steerableB2body.getBody();
    }

    protected  void defineBody() {
        Body b2body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startPosition.x / AndroidAdventures.PPM, startPosition.y / AndroidAdventures.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);
        steerableB2body = new B2DSteeringEntity(b2body);
    }
    protected FixtureDef getFixtureDef (short agentBit) {
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeOfB2DBody.x/ AndroidAdventures.PPM, sizeOfB2DBody.y /AndroidAdventures.PPM);
        fixtureDef.filter.categoryBits = agentBit;
        fixtureDef.shape = shape;
        return fixtureDef;
    }

    protected Vector2 getPositionOfAgentTexture(STATE state, int index) {
        float x;
        float y;
        switch (state) {
            case DEATH:
                x = deathTexturePos.x;
                y = deathTexturePos.y;
                break;
            case FLYING:
                x = flyingTexturePos.x;
                y = flyingTexturePos.y;
                break;
            case RUNNING:
                x= runningTexturePos.x;
                y = runningTexturePos.y;
                break;
            case STANDING:
                x = standingTexturePos.x;
                y = standingTexturePos.y;
                break;
            default:
                throw new RuntimeException("illegal state of agent in SpriteAgent.getPositionOfAgentTexture()");
        }
        return  new Vector2( x + index * textureRegionBoundsWith, y );
    }


    protected void defineTexturesPositions (Vector2 deathTexturePosition, Vector2 runningTexturePosition,
                                            Vector2 flyingTexturePosition, Vector2 standingTexturePosition) {
        this.standingTexturePos = standingTexturePosition;
        this.deathTexturePos = deathTexturePosition;
        this.runningTexturePos = runningTexturePosition;
        this.flyingTexturePos = flyingTexturePosition;
    }

    protected STATE getState() {
        if (steerableB2body.getLinearVelocity().y != 0) {
            return  STATE.FLYING;
        } else if (steerableB2body.getLinearVelocity().x != 0) {
            return  STATE.RUNNING;
        } else {
            return  STATE.STANDING;
        }

    }


    public void update(float dt){
        steerableB2body.update(dt);
        setPosition(steerableB2body.getPosition().x - getWidth() /2 , steerableB2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    protected Array<TextureRegion> getFramesForPlayerActionAnimation (STATE actionOrStateOfPlayer) {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        int iterations = getActionFramesNumber(actionOrStateOfPlayer);
        for (int i = 0; i < iterations; i++) {

            frames.add(SpriteUtilities.loadTexture(this, actionOrStateOfPlayer, i));
        }
        return frames;
    }



}
