package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.PlayScreen;

public class AndroidAdventures extends Game {

	public  int score = 0;


	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
	public SpriteBatch getBatch() {
		return batch;
	}
	public void addScore(int score) {
		this.score += score;
	}
	public int getScore () {
		return score;
	}
	public void resetScore () {
		score = 0;
	}
}
