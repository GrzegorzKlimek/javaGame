package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Sprites.agents.SpriteAgents;
import com.mygdx.game.Sprites.agents.protagonist.Player;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 24.01.18.
 */

public class SpriteUtilities {

    public static final short PLATFORM_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short DIAMOND_BIT = 4;
    public static final short DESTROYED_BIT = 16;
    public static final short SPIKE_BIT = 32;
    public static final short OBJECT_BIT = 64;
    public static final short ENEMY_BIT = 128;

    public static TextureRegion loadTexture (SpriteAgents agent, SpriteAgents.STATE state, int numberOfFrame) {
        int xCoordinate;
        int yCoordinate;
        if (agent instanceof Player && state.equals(SpriteAgents.STATE.FLYING)) {
            xCoordinate = (int) (agent.flyingTexturePos.x) + numberOfFrame * agent.textureWith;
            yCoordinate = (int) (agent.flyingTexturePos.y);

        } else {
            switch (state) {
                case RUNNING:
                    xCoordinate = (int) (agent.runningTexturePos.x) + numberOfFrame * agent.textureWith;
                    yCoordinate = (int) (agent.runningTexturePos.y);
                    break;
                case DEATH:
                    xCoordinate = (int) (agent.runningTexturePos.x) + numberOfFrame * agent.textureWith;
                    yCoordinate = (int) (agent.deathTexturePos.y);
                    break;
                case STANDING:
                default:
                    xCoordinate = (int) (agent.runningTexturePos.x) + numberOfFrame * agent.textureWith;
                    yCoordinate = (int) (agent.standingTexturePos.y);
                    break;
            }
        }

        return new TextureRegion(agent.getTexture(), xCoordinate, yCoordinate, agent.textureWith, agent.textureHeight);
    }


}
