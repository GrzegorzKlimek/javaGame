package com.mygdx.game.Sprites.agents.enemies.particular;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Sprites.SpriteUtilities;
import com.mygdx.game.Sprites.TileObjects.TileObject;
import com.mygdx.game.Sprites.agents.enemies.Enemy;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by Iksob on 2018-01-12.
 */

public class Evil extends Enemy {
    private float stateTime;

    public Evil(PlayScreen screen, float x, float y) {
        super(screen, x, y);
    }

    @Override
    protected void defineEnemy(){
        Map map = screen.getMap();
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / map.getPpm() , 32 /  map.getPpm());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(12 /  map.getPpm());
        fdef.filter.categoryBits = SpriteUtilities.ENEMY_BIT;
        fdef.filter.maskBits = SpriteUtilities.PLATFORM_BIT |

                SpriteUtilities.DIAMOND_BIT |
                SpriteUtilities.ENEMY_BIT |
                SpriteUtilities.OBJECT_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
