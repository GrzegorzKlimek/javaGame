package com.mygdx.game.Sprites;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by grzegorz on 27.01.18.
 */

public class B2DSteeringEntity implements Steerable <Vector2> {
    /**
     * Returns the vector indicating the linear velocity of this Steerable.
     */
    private boolean taged;
    private float maxLinearSpeed, maxLinearAcceleration;
    private float maxAngularSpeed, maxAngularAcceleration;
    private MyLocation location;
    private float zeroLinearSpeedThreshold;
    private SteeringBehavior<Vector2> behavior;
    private SteeringAcceleration<Vector2> steerOutput;


    public B2DSteeringEntity (Body body) {

        location = new MyLocation(body);
        this.maxLinearSpeed = 50;
        this.maxLinearAcceleration = 200;
        this.maxAngularSpeed = 30;
        this.maxAngularAcceleration = 5;
        this.taged = false;
        this.steerOutput = new SteeringAcceleration<Vector2>(new Vector2());
        this.getBody().setUserData(this);
    }

    public Body getBody() {
        return location.getBody();
    }

    public void setBehaviour(SteeringBehavior<Vector2> steeringBehavior) {
        this.behavior = steeringBehavior;
    }
    public  SteeringBehavior<Vector2> getBehavior () {
        return behavior;
    }

    public void update (float delta) {
        if (behavior != null) {
            behavior.calculateSteering(steerOutput);
            applySteering(delta);
        }
    }

    private void applySteering (float delta) {
        boolean anyAcceleration = false;
        if (!steerOutput.linear.isZero()) {
            Vector2 force = new Vector2(steerOutput.linear.scl(delta).x , 0);
            getBody().applyForceToCenter(force, true);
            anyAcceleration = true;
        }

        if (anyAcceleration) {
            Vector2 velocity = getBody().getLinearVelocity();
            float currentSpeedSquere = velocity.len2();
            if (currentSpeedSquere > maxLinearSpeed * maxLinearSpeed) {
                getBody().setLinearVelocity(velocity.scl(maxLinearSpeed/ (float) Math.sqrt(currentSpeedSquere)));
            }
        }
    }

    @Override
    public Vector2 getLinearVelocity() {

        return location.getBody().getLinearVelocity();
    }

    /**
     * Returns the float value indicating the the angular velocity in radians of this Steerable.
     */
    @Override
    public float getAngularVelocity() {

        return location.getBody().getAngularVelocity();
    }

    /**
     * Returns the bounding radius of this Steerable.
     */
    @Override
    public float getBoundingRadius() {
        return 4;
    }

    /**
     * Returns {@code true} if this Steerable is tagged; {@code false} otherwise.
     */
    @Override
    public boolean isTagged() {
        return taged;
    }

    /**
     * Tag/untag this Steerable. This is a generic flag utilized in a variety of ways.
     *
     * @param tagged the boolean value to set
     */
    @Override
    public void setTagged(boolean tagged) {
        this.taged = tagged;
    }

    /**
     * Returns the threshold below which the linear speed can be considered zero. It must be a small positive value near to zero.
     * Usually it is used to avoid updating the orientation when the velocity vector has a negligible length.
     */
    @Override
    public float getZeroLinearSpeedThreshold() {

        return zeroLinearSpeedThreshold;
    }

    /**
     * Sets the threshold below which the linear speed can be considered zero. It must be a small positive value near to zero.
     * Usually it is used to avoid updating the orientation when the velocity vector has a negligible length.
     *
     * @param value
     */
    @Override
    public void setZeroLinearSpeedThreshold(float value) {
        this.zeroLinearSpeedThreshold = value;
    }

    /**
     * Returns the maximum linear speed.
     */
    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    /**
     * Sets the maximum linear speed.
     *
     * @param maxLinearSpeed
     */
    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    /**
     * Returns the maximum linear acceleration.
     */
    @Override
    public float getMaxLinearAcceleration() {

        return maxLinearAcceleration;
    }

    /**
     * Sets the maximum linear acceleration.
     *
     * @param maxLinearAcceleration
     */
    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    /**
     * Returns the maximum angular speed.
     */
    @Override
    public float getMaxAngularSpeed() {

        return maxAngularSpeed;
    }

    /**
     * Sets the maximum angular speed.
     *
     * @param maxAngularSpeed
     */
    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    /**
     * Returns the maximum angular acceleration.
     */
    @Override
    public float getMaxAngularAcceleration() {

        return maxAngularAcceleration;
    }

    /**
     * Sets the maximum angular acceleration.
     *
     * @param maxAngularAcceleration
     */
    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    /**
     * Returns the vector indicating the position of this location.
     */
    @Override
    public Vector2 getPosition() {

        return location.getPosition();
    }

    /**
     * Returns the float value indicating the orientation of this location. The orientation is the angle in radians representing
     * the direction that this location is facing.
     */
    @Override
    public float getOrientation() {

        return location.getOrientation();
    }

    /**
     * Sets the orientation of this location, i.e. the angle in radians representing the direction that this location is facing.
     *
     * @param orientation the orientation in radians
     */
    @Override
    public void setOrientation(float orientation) {
        location.setOrientation(orientation);
    }

    /**
     * Returns the angle in radians pointing along the specified vector.
     *
     * @param vector the vector
     */
    @Override
    public float vectorToAngle(Vector2 vector) {

        return location.vectorToAngle(vector);
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

        return location.angleToVector(outVector, angle);
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
        return location.newLocation();
    }
}
