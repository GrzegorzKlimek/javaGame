package com.mygdx.game.Sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.JavaSimpleGame;
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
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / JavaSimpleGame.PPM , 32 / JavaSimpleGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(12 / JavaSimpleGame.PPM);
        fdef.filter.categoryBits = JavaSimpleGame.ENEMY_BIT;
        fdef.filter.maskBits = JavaSimpleGame.GROUND_BIT |

            JavaSimpleGame.DIAMOND_BIT |
            JavaSimpleGame.BLOCK_BIT |
            JavaSimpleGame.ENEMY_BIT |
            JavaSimpleGame.OBJECT_BIT;




        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
