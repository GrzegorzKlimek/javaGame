package com.mygdx.game.Sprites.agents.protagonist;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.agents.SpriteAgent;
import com.mygdx.game.screens.PlayScreen;

;

/**
 * Created by grzegorz on 03.01.18.
 */

public class Player extends SpriteAgent {


    public static String BODY_USER_DATA = "PlayerBody";
    private static String NAME_OF_FIRST_REGION = "android_standing";
    private float durationOfAnimation = 0.1f;

    private static  Vector2 DEATH_TEXTURE_POSITION= new Vector2(1, 144);
    private static Vector2 RUNNING_TEXTURE_POSITION= new Vector2(169, 146);
    private static Vector2 FLYING_TEXTURE_POSITION= new Vector2(1, 68);
    private static  Vector2 STANDING_TEXTURE_POSITION = new Vector2(1, 1);
    private static Vector2 SIZE_OF_BOX2D_BODY = new Vector2(4, 5);
    private static Vector2 TEXTURE_SIZE = new Vector2(55, 78);
    private static Vector2 SPRITE_TEXTURE_SIZE = new Vector2(17, 25);


    private Animation <TextureRegion>playerFly;

    public Player ( PlayScreen screen, Vector2 startPosition) {
        super(screen, startPosition, NAME_OF_FIRST_REGION,SPRITE_TEXTURE_SIZE, SIZE_OF_BOX2D_BODY, TEXTURE_SIZE);
        currentState = STATE.STANDING;
        previousState = STATE.STANDING;
        stateTimer = 0;
        runningRight = true;
        defineAgent(startPosition, SpriteUtilities.PLAYER_BIT, BODY_USER_DATA);
        agentRun = new Animation <TextureRegion>(durationOfAnimation, getFramesForPlayerActionAnimation(STATE.RUNNING));
        playerFly = new Animation <TextureRegion>(durationOfAnimation, getFramesForPlayerActionAnimation(STATE.FLYING));
        Vector2 standingDroitTexturePos = getPositionOfAgentTexture(STATE.STANDING, 0);
        agentStand = new TextureRegion(getTexture(), (int) ( standingDroitTexturePos.x) , (int)( standingDroitTexturePos.y ) , textureRegionBoundsWith, textureRegionBoundsHeight);
        setRegion(agentStand);

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
                region = agentRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                default:
                region = agentStand;
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

        super.defineBody();
        FixtureDef fixtureDef = super.getFixtureDef(agentBit);
        fixtureDef.filter.maskBits = SpriteUtilities.PLATFORM_BIT | SpriteUtilities.DIAMOND_BIT | SpriteUtilities.SPIKE_BIT | SpriteUtilities.ENEMY_BIT ;
        b2body.createFixture(fixtureDef).setUserData(userData);
        super.defineTexturesPositions(DEATH_TEXTURE_POSITION, RUNNING_TEXTURE_POSITION, FLYING_TEXTURE_POSITION, STANDING_TEXTURE_POSITION );
    }


}


