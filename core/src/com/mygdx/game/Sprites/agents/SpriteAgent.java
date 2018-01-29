package com.mygdx.game.Sprites.agents;

import com.badlogic.gdx.ai.steer.SteeringBehavior;
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
import com.mygdx.game.AndroidAdventuresGame;
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
    protected TextureRegion agentDeath;
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
    protected AndroidAdventuresGame game;
    protected World world;
    protected  Map map;
    public B2DSteeringEntity steerableB2body;
    protected float stateTimer;
    protected boolean runningRight;

    public SpriteAgent(PlayScreen screen, Vector2 startPosition, String firstRegion, Vector2 sizeOfSprite, Vector2 sizeOfB2DBody, Vector2 boundsOfTextureRegion) {
        super(screen.getAtlas().findRegion(firstRegion));
        game = screen.getGame();
        map = screen.getMap();
        world = screen.getWorld();
        this.sizeOfB2DBody = sizeOfB2DBody;
        this.sizeOfSprite = sizeOfSprite;
        this.textureRegionBoundsWith = (int)boundsOfTextureRegion.x;
        this.textureRegionBoundsHeight =  (int) boundsOfTextureRegion.y;
        this.startPosition = startPosition;
        setBounds(0,0, sizeOfSprite.x/ AndroidAdventuresGame.PPM, sizeOfSprite.y/ AndroidAdventuresGame.PPM);


    }
    protected abstract int getActionFramesNumber(STATE actionOrStateOfPlayer);
    protected abstract TextureRegion getFrame (float deltaTime);

    protected abstract void defineAgent(Vector2 startPosition , short agentBit);

    public Body getBody () {
        return steerableB2body.getBody();
    }

    public B2DSteeringEntity getSteerableBody () {
        return steerableB2body;
    }

    protected  void defineBody() {
        Body b2body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startPosition.x / AndroidAdventuresGame.PPM, startPosition.y / AndroidAdventuresGame.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);
        steerableB2body = new B2DSteeringEntity(b2body);
    }
    protected FixtureDef getFixtureDef (short agentBit) {
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeOfB2DBody.x/ AndroidAdventuresGame.PPM, sizeOfB2DBody.y / AndroidAdventuresGame.PPM);
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
        if (currentState == STATE.DEATH) {
            return currentState;
        } else if (steerableB2body.getLinearVelocity().y != 0) {
            return  STATE.FLYING;
        } else if (steerableB2body.getLinearVelocity().x != 0) {
            return  STATE.RUNNING;
        } else {
            return  STATE.STANDING;
        }

    }

    public void setState(STATE state) {
       // this.currentState = state;
    }


    public void update(float dt){
        steerableB2body.update(dt);
        updateRunningDirectionInfo();
        updateStateTimer(dt);
        setPosition(steerableB2body.getPosition().x - getWidth() /2 , steerableB2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    protected void updateRunningDirectionInfo() {
        if ( (steerableB2body.getLinearVelocity().x < 0 || !runningRight) && !isFlipX()) {

            runningRight = false;
        }
        if ( (steerableB2body.getLinearVelocity().x > 0 || runningRight) && isFlipX()) {

            runningRight = true;
        }

    }

    protected  void updateStateTimer(float dt) {
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
    }
    protected  boolean doTextureNeedToBeFlipt() {
        return  (runningRight && isFlipX()) || (!runningRight && !isFlipX());
    }

    protected Array<TextureRegion> getFramesForPlayerActionAnimation (STATE actionOrStateOfPlayer) {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        int iterations = getActionFramesNumber(actionOrStateOfPlayer);
        for (int i = 0; i < iterations; i++) {

            frames.add(SpriteUtilities.loadTexture(this, actionOrStateOfPlayer, i));
        }
        return frames;
    }

    public void setBehaviour(SteeringBehavior<Vector2> steeringBehavior) {
        steerableB2body.setBehaviour(steeringBehavior);
    }

    public boolean isDeath() {
        return currentState.equals(STATE.DEATH);
    }



}
