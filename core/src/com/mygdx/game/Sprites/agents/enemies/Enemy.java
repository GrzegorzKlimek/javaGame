package com.mygdx.game.Sprites.agents.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by Iksob on 2018-01-12.
 */

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Enemy(PlayScreen screen, float x, float y){
        this.world=screen.getWorld();
        this.screen = screen;
        setPosition(x,y);

    }

    protected abstract void defineEnemy();
}