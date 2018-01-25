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
    public Body b2body;
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
        setBounds(0,0, sizeOfSprite.x/map.getPpm(), sizeOfSprite.y/map.getPpm());


    }
    protected abstract int getActionFramesNumber(STATE actionOrStateOfPlayer);
    protected abstract TextureRegion getFrame (float deltaTime);

    protected abstract void defineAgent(Vector2 startPosition , short agentBit, String userData);

    protected  void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startPosition.x / map.getPpm(), startPosition.y / map.getPpm());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);
    }
    protected FixtureDef getFixtureDef (short agentBit) {
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeOfB2DBody.x/ map.getPpm(), sizeOfB2DBody.y / map.getPpm());
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
                throw new RuntimeException("illegal state of player in Player.getPositionOfAgentTexture()");
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
        if (b2body.getLinearVelocity().y != 0) {
            return  STATE.FLYING;
        } else if (b2body.getLinearVelocity().x != 0) {
            return  STATE.RUNNING;
        } else {
            return  STATE.STANDING;
        }

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
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
