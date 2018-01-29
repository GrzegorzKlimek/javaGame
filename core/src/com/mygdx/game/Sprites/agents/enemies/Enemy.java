package com.mygdx.game.Sprites.agents.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.agents.SpriteAgent;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by grzegorz on 29.01.18.
 */

public abstract class Enemy extends SpriteAgent {

    public enum KIND {DARK_KNIGHT};

    public Enemy(PlayScreen screen, Vector2 startPosition, String firstRegion, Vector2 sizeOfSprite, Vector2 sizeOfB2DBody, Vector2 boundsOfTextureRegion) {
        super(screen, startPosition, firstRegion, sizeOfSprite, sizeOfB2DBody, boundsOfTextureRegion);
    }


    @Override
    public void defineAgent (Vector2 startPosition , short agentBit) {
        super.defineBody();
        FixtureDef fixtureDef = super.getFixtureDef(agentBit);
        fixtureDef.filter.maskBits = SpriteUtilities.PLATFORM_BIT  | SpriteUtilities.SPIKE_BIT | SpriteUtilities.PLAYER_BIT | SpriteUtilities.NPC_BOUNDRIES_BIT;
        getBody().createFixture(fixtureDef).setUserData(this);

    }
}
