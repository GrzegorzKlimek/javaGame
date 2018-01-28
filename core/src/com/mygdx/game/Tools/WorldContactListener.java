package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Sprites.TileObjects.InteractiveTileObject;
import com.mygdx.game.Sprites.agents.protagonist.Player;

/**
 * Created by Iksob on 2018-01-12.
 */

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Begin Contact","");

        Player player = getPlayerFromContact(contact);
        InteractiveTileObject tileObject = getInteractiveTileObjectFromContact(contact);
        if(player != null && tileObject != null) {
            tileObject.onContact(player);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact","");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private Player getPlayerFromContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Player) {
            return (Player) fixA.getUserData();
        } else if (fixB.getUserData() instanceof Player) {
            return (Player) fixB.getUserData();
        } else {
            return null;
        }

    }

    private InteractiveTileObject getInteractiveTileObjectFromContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof InteractiveTileObject) {
            return (InteractiveTileObject) fixA.getUserData();
        } else if (fixB.getUserData() instanceof InteractiveTileObject) {
            return (InteractiveTileObject) fixB.getUserData();
        } else {
            return null;
        }

    }
}
