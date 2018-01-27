package com.mygdx.game.Sprites.agents.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.agents.SpriteAgent;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by Iksob on 2018-01-12.
 */

public class DarkKnight extends SpriteAgent {
    public static String BODY_USER_DATA = "EnemyBody";
    private static String NAME_OF_FIRST_REGION = "dark_knight_standing";
    private float durationOfAnimation = 0.1f;

    private static Vector2 RUNNING_TEXTURE_POSITION= new Vector2(1, 215);
    private static  Vector2 STANDING_TEXTURE_POSITION = new Vector2(723, 207);
    private static  Vector2 DEATH_TEXTURE_POSITION= null;
    private static Vector2 FLYING_TEXTURE_POSITION= null;

    private static Vector2 SIZE_OF_BOX2D_BODY = new Vector2(8, 25);
    private static Vector2 TEXTURE_REGION_BOUNDS = new Vector2(240, 272);
    private static Vector2 SPRITE_TEXTURE_SIZE = new Vector2(40, 68);

    public DarkKnight(PlayScreen screen, Vector2 startPosition) {
        super(screen, startPosition, NAME_OF_FIRST_REGION, SPRITE_TEXTURE_SIZE, SIZE_OF_BOX2D_BODY, TEXTURE_REGION_BOUNDS);
        currentState = STATE.STANDING;
        previousState = STATE.STANDING;
        stateTimer = 0;
        runningRight = true;
        defineAgent(startPosition, SpriteUtilities.ENEMY_BIT, BODY_USER_DATA);
        agentRun = new Animation<TextureRegion>(durationOfAnimation, getFramesForPlayerActionAnimation(STATE.RUNNING));
        Vector2 standingDroitTexturePos = getPositionOfAgentTexture(STATE.STANDING, 0);
        agentStand = new TextureRegion(getTexture(), (int) ( standingDroitTexturePos.x) , (int)( standingDroitTexturePos.y ) , textureRegionBoundsWith, textureRegionBoundsHeight);
        setRegion(agentStand);
    }

    @Override
    protected int getActionFramesNumber(STATE actionOrStateOfPlayer) {
        return 3;
    }


    @Override
    protected TextureRegion getFrame(float deltaTime) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {

            case RUNNING:
                region = agentRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = agentStand;
                break;
        }
        if ( (steerableB2body.getLinearVelocity().x < 0 || !runningRight) && !isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        if ( (steerableB2body.getLinearVelocity().x > 0 || runningRight) && isFlipX()) {
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
        fixtureDef.filter.maskBits = SpriteUtilities.PLATFORM_BIT | SpriteUtilities.DIAMOND_BIT | SpriteUtilities.SPIKE_BIT | SpriteUtilities.PLAYER_BIT ;
        getBody().createFixture(fixtureDef).setUserData(userData);
        super.defineTexturesPositions(DEATH_TEXTURE_POSITION, RUNNING_TEXTURE_POSITION, FLYING_TEXTURE_POSITION, STANDING_TEXTURE_POSITION );
    }

    @Override
    protected STATE getState() {
        if (getBody().getLinearVelocity().x != 0) {
            return  STATE.RUNNING;
        } else {
            return  STATE.STANDING;
        }

    }

}
