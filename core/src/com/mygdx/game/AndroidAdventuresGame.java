package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.PlayScreen;

public class AndroidAdventuresGame extends Game {

	public static  float PPM = 100;
	public static float V_WIDTH = 400;
	public static float V_HEIGHT = 250;

	private   int score = 0;
	public SpriteBatch batch;
	private boolean isOver = false;



	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

	}

	@Override
	public void render () {
		if (isOver) {
			setScreen(new GameOverScreen(this));
		}
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean over) {
		isOver = over;
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
