package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Sprites.agents.SpriteAgent;
import com.mygdx.game.Sprites.agents.protagonist.Player;

/**
 * Created by grzegorz on 24.01.18.
 */

public class SpriteUtilities {

    public static final short PLATFORM_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short DIAMOND_BIT = 4;
    public static final short DESTROYED_BIT = 16;
    public static final short SPIKE_BIT = 32;
    public static final short KEY_BIT = 64;
    public static final short ENEMY_BIT = 128;
    public static final short NPC_BOUNDRIES_BIT = 256;

    public static TextureRegion loadTexture (SpriteAgent agent, SpriteAgent.STATE state, int numberOfFrame) {
        int xCoordinate;
        int yCoordinate;
        if (agent instanceof Player && state.equals(SpriteAgent.STATE.FLYING)) {
            xCoordinate = (int) (agent.flyingTexturePos.x) + numberOfFrame * agent.textureRegionBoundsWith;
            yCoordinate = (int) (agent.flyingTexturePos.y);

        } else {
            switch (state) {
                case RUNNING:
                    xCoordinate = (int) (agent.runningTexturePos.x) + numberOfFrame * agent.textureRegionBoundsWith;
                    yCoordinate = (int) (agent.runningTexturePos.y);
                    break;
                case DEATH:
                    xCoordinate = (int) (agent.deathTexturePos.x) + numberOfFrame * agent.textureRegionBoundsWith;
                    yCoordinate = (int) (agent.deathTexturePos.y);
                    break;
                case STANDING:
                default:
                    xCoordinate = (int) (agent.standingTexturePos.x) + numberOfFrame * agent.textureRegionBoundsWith;
                    yCoordinate = (int) (agent.standingTexturePos.y);
                    break;
            }
        }

        return new TextureRegion(agent.getTexture(), xCoordinate, yCoordinate, agent.textureRegionBoundsWith, agent.textureRegionBoundsHeight);
    }

    public static  float vectorToAngle (Vector2 vector) {
        return  (float) Math.atan2(-vector.x, vector.y);
    }

    public static Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float)Math.sin(angle);
        outVector.y = -(float)Math.cos(angle);
        return outVector;
    }


}
