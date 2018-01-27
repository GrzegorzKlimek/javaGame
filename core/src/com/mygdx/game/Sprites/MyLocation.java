package com.mygdx.game.Sprites;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by grzegorz on 27.01.18.
 */

public class MyLocation implements Location <Vector2> {
    /**
     * Returns the vector indicating the position of this location.
     */

    private Body body;



    public MyLocation (Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    /**
     * Returns the float value indicating the orientation of this location. The orientation is the angle in radians representing
     * the direction that this location is facing.
     */
    @Override
    public float getOrientation() {
        return body.getAngle();
    }

    /**
     * Sets the orientation of this location, i.e. the angle in radians representing the direction that this location is facing.
     *
     * @param orientation the orientation in radians
     */
    @Override
    public void setOrientation(float orientation) {
        body.setTransform(body.getPosition().x , body.getPosition().y, orientation);
    }

    /**
     * Returns the angle in radians pointing along the specified vector.
     *
     * @param vector the vector
     */
    @Override
    public float vectorToAngle(Vector2 vector) {
        return SpriteUtilities.vectorToAngle(vector);
    }

    /**
     * Returns the unit vector in the direction of the specified angle expressed in radians.
     *
     * @param outVector the output vector.
     * @param angle     the angle in radians.
     * @return the output vector for chaining.
     */
    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return SpriteUtilities.angleToVector(outVector, angle);
    }

    /**
     * Creates a new location.
     * <p>
     * This method is used internally to instantiate locations of the correct type parameter {@code T}. This technique keeps the API
     * simple and makes the API easier to use with the GWT backend because avoids the use of reflection.
     *
     * @return the newly created location.
     */
    @Override
    public Location newLocation() {
        return new MyLocation(this.body);
    }
}
