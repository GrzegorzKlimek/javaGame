package com.mygdx.game.Sprites.agents.protagonist;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.agents.SpriteAgents;
import com.mygdx.game.screens.PlayScreen;

;

/**
 * Created by grzegorz on 03.01.18.
 */

public class Player extends SpriteAgents {


    public static String BODY_USER_DATA = "PlayerBody";
    private float durationOfAnimation = 0.1f;

    private  static  Vector2 START_POSITION = new Vector2(32,32);
    private static  Vector2 DEATH_TEXTURE_POSITION= new Vector2(1, 6);
    private static Vector2 RUNNING_TEXTURE_POSITION= new Vector2(169, 8);
    private static Vector2 FLYING_TEXTURE_POSITION= new Vector2(333, 1);
    private static  Vector2 STANDING_TEXTURE_POSITION = new Vector2(491, 5);

    private Animation <TextureRegion>playerFly;
    private float stateTimer;
    private boolean runningRight;

    public Player ( PlayScreen screen) {
        super(screen, START_POSITION, "flying", 10, 55,78);
        currentState = STATE.STANDING;
        previousState = STATE.STANDING;
        stateTimer = 0;
        runningRight = true;
        defineAgent(startPosition, SpriteUtilities.PLAYER_BIT, BODY_USER_DATA);
        playerRun = new Animation <TextureRegion>(durationOfAnimation, getFramesForPlayerActionAnimation(STATE.RUNNING));
        playerFly = new Animation <TextureRegion>(durationOfAnimation, getFramesForPlayerActionAnimation(STATE.FLYING));
        Vector2 standingDroitTexturePos = getPositionOfPlayerTexture(STATE.STANDING, 0);
        playerStand = new TextureRegion(getTexture(), (int) ( standingDroitTexturePos.x) , (int)( standingDroitTexturePos.y ) , textureWith, textureHeight);
        setBounds(0,0, 17/map.getPpm(), 25/map.getPpm());
        setRegion(playerStand);

    }


    @Override
    protected int getActionFramesNumber(STATE actionOrStateOfPlayer) {
        switch (actionOrStateOfPlayer) {
            case FLYING:
                return 2;
            case RUNNING:
                return 3;
            default:
                return 2;
        }
    }


    @Override
    protected TextureRegion getFrame (float deltaTime) {
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


    @Override
    public void defineAgent (Vector2 startPosition , short agentBit, String userData) {
        super.defineAgent(startPosition, agentBit, userData);
        super.defineTexturesPositions(DEATH_TEXTURE_POSITION, RUNNING_TEXTURE_POSITION, FLYING_TEXTURE_POSITION, STANDING_TEXTURE_POSITION );
    }


}


