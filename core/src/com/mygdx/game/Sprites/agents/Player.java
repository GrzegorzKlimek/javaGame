package com.mygdx.game.Sprites.agents;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.TileObjects.TileObject;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.screens.PlayScreen;

;

/**
 * Created by grzegorz on 03.01.18.
 */

public class Player extends Sprite {
    public static int TEXTURE_WIDTH_OF_PLAYER = 55;
    public static int TEXTURE_HEIGHT_OF_PLAYER = 78;
    private final int SHAPE_RADIUS_OF_BODY = 10;
    public static String BODY_USER_DATA = "PlayerBody";


    public  Vector2 DEATH_SPRITE_POSITION= new Vector2(1, 6);
    public  Vector2 RUNNING_SPRITE_POSITION= new Vector2(169, 8);
    public  Vector2 FLYING_SPRITE_POSITION= new Vector2(333, 1);
    public  Vector2 STANDING_SPRITE_POSITION = new Vector2(491, 5);
    private float durationOfAnimation = 0.1f;

    public enum State {DEATH, FLYING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Map map;
    public Body b2body;
    private TextureRegion playerStand;
    private Animation <TextureRegion> playerRun;
    private Animation <TextureRegion>playerFly;
    private float stateTimer;
    private boolean runningRight;

    public Player ( PlayScreen screen) {
        super(screen.getAtlas().findRegion("standing"));
        this.map = screen.getMap();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        playerRun = new Animation <TextureRegion>(durationOfAnimation, getFramesForPlayerActionAnimation(State.RUNNING));
        playerFly = new Animation <TextureRegion>(durationOfAnimation, getFramesForPlayerActionAnimation(State.FLYING));
        this.world = screen.getWorld();
        definePlayer();
        Vector2 standingDroitTexturePos = getPositionOfPlayerTexture(State.STANDING, 0);
        playerStand = new TextureRegion(getTexture(), (int) ( standingDroitTexturePos.x) , (int)( standingDroitTexturePos.y ) , TEXTURE_WIDTH_OF_PLAYER, TEXTURE_HEIGHT_OF_PLAYER);
        setBounds(0,0, 17/map.getPpm(), 25/map.getPpm());
        setRegion(playerStand);

    }

    private Vector2 getPositionOfPlayerTexture (State state, int index) {
        float x;
        float y;
        switch (state) {
            case DEATH:
                x = DEATH_SPRITE_POSITION.x;
                y = DEATH_SPRITE_POSITION.y;
                break;
            case FLYING:
                x = FLYING_SPRITE_POSITION.x;
                y = FLYING_SPRITE_POSITION.y;
                break;
            case RUNNING:
                x= RUNNING_SPRITE_POSITION.x;
                y = RUNNING_SPRITE_POSITION.y;
                break;
            case STANDING:
                x = STANDING_SPRITE_POSITION.x;
                y = STANDING_SPRITE_POSITION.y;
                break;
            default:
                throw new RuntimeException("illegal state of player in Player.getPositionOfPlayerTexture()");
        }
        return  new Vector2( x + index * TEXTURE_WIDTH_OF_PLAYER, y );
    }

    private Array<TextureRegion> getFramesForPlayerActionAnimation (State actionOrStateOfPlayer) {
        Vector2 playerTexturePos ;
        Array<TextureRegion> frames = new Array<TextureRegion>();
        int iterations = getActionFramesNumber(actionOrStateOfPlayer);
        for (int i = 0; i < iterations; i++) {
            playerTexturePos = getPositionOfPlayerTexture(actionOrStateOfPlayer, i);
            frames.add(new TextureRegion(getTexture(), (int) ( playerTexturePos.x) , (int)( playerTexturePos.y ) , TEXTURE_WIDTH_OF_PLAYER, TEXTURE_HEIGHT_OF_PLAYER));
        }
        return frames;
    }

    private int getActionFramesNumber(State actionOrStateOfPlayer) {
        switch (actionOrStateOfPlayer) {
            case FLYING:
                return 2;
            case RUNNING:
                return 3;
            default:
                return 2;
        }
    }


    public void update(float dt){
       setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
       setRegion(getFrame(dt));
    }

    public TextureRegion getFrame (float deltaTime) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case FLYING:
                region =  playerFly.getKeyFrame(stateTimer, true);
                break;
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                default:
                region = playerStand;
                break;
        }
        if ( (b2body.getLinearVelocity().x < 0 || !runningRight) && !isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        if ( (b2body.getLinearVelocity().x > 0 || runningRight) && isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + deltaTime : 0;
        previousState = currentState;
        return  region;

    }

    public State getState() {
        if (b2body.getLinearVelocity().y != 0) {
            return  State.FLYING;
        } else if (b2body.getLinearVelocity().x != 0) {
            return  State.RUNNING;
        } else {
            return  State.STANDING;
        }

    }

    public void definePlayer () {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / map.getPpm() , 32 / map.getPpm());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(SHAPE_RADIUS_OF_BODY / map.getPpm());
        fixtureDef.filter.categoryBits = SpriteUtilities.PLAYER_BIT;
        fixtureDef.filter.maskBits = SpriteUtilities.PLATFORM_BIT | SpriteUtilities.DIAMOND_BIT | SpriteUtilities.SPIKE_BIT;


        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef).setUserData(BODY_USER_DATA);


    }


}


