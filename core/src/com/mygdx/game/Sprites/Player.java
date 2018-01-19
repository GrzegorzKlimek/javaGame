package com.mygdx.game.Sprites;

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
import com.mygdx.game.JavaSimpleGame;
import com.mygdx.game.screens.PlayScreen;

;

/**
 * Created by grzegorz on 03.01.18.
 */

public class Player extends Sprite {
    public static int PLAYER_TEXTURE_WIDTH = 55;
    public static int PLAYER_TEXTURE_HEIGHT = 80;

    public static Vector2 DEATH_SPRITE_POSITION= new Vector2(1, 6);
    public static Vector2 RUNNING_SPRITE_POSITION= new Vector2(169, 8);
    public static Vector2 FLYING_SPRITE_POSITION= new Vector2(333, 1);
    public static Vector2 STANDING_SPRITE_POSITION = new Vector2(491, 5);

    public static int FIRST_DEATH_PICTURE_INDEX = 0;
    public static int LAST_DEATH_PICTURE_INDEX = 2;

    public static int FIRST_RUNNING_PICTURE_INDEX = 3;
    public static int LAST_RUNNING_PICTURE_INDEX = 5;

    public static int FIRST_FLYING_PICTURE_INDEX = 6;
    public static int LAST_FLYING_PICTURE_INDEX = 8;

    public static int FIRST_STANING_PICTURE_INDEX = 9;
    public static int LAST_STANING_PICTURE_INDEX = 11;






    public enum State {DEATH, FLYING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion playerStand;
    private Animation playerRun;
    private Animation playerJump;
    private float stateTimer;
    private boolean runningRight;

    public Player (World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("standing"));
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

       Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            frames.add( new TextureRegion(getTexture(),  (int) (i * FLYING_SPRITE_POSITION.x) , (i * FLYING_SPRITE_POSITION.y) , PLAYER_TEXTURE_WIDTH, PLAYER_TEXTURE_HEIGHT));
        }


        this.world = world;
        definePlayer();
        Vector2 runningDroitTexturePos = getPositionOfPlayerTexture(State.FLYING, 2);
        playerStand = new TextureRegion(getTexture(), (int) ( runningDroitTexturePos.x) , (int)( runningDroitTexturePos.y ) , PLAYER_TEXTURE_WIDTH, PLAYER_TEXTURE_HEIGHT );
        setBounds(0,0, 17/JavaSimpleGame.PPM, 25/JavaSimpleGame.PPM);
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
        return  new Vector2( x + index * PLAYER_TEXTURE_WIDTH, y );
    }


    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
    }




    public void definePlayer () {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / JavaSimpleGame.PPM , 32 / JavaSimpleGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / JavaSimpleGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }


}


