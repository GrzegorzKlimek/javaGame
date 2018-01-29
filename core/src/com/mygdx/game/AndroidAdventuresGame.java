package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.FinishedLevelScreen;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.PlayScreen;

public class AndroidAdventuresGame extends Game {

	public static  float PPM = 100;
	public static float V_WIDTH = 400;
	public static float V_HEIGHT = 250;
	private Integer worldTimer;
	private float timeCount;



	private   int score = 0;
	public SpriteBatch batch;
	private boolean isOver = false;
	private boolean playerFinishedLevel = false;



	@Override
	public void create () {
		worldTimer = 100;
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

	}

	@Override
	public void render () {
		if (isOver ) {
			setScreen(new GameOverScreen(this));
		}
		if (playerFinishedLevel) {
			setScreen(new FinishedLevelScreen(this));
		}
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}

	public void updateWorldTimer(float dt) {
		timeCount += dt;
		if(timeCount >= 1){
			worldTimer--;
			timeCount=0;
		}
		if (worldTimer <= 0) {
			isOver = true;
		}
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

	public Integer getWorldTimer() {
		return worldTimer;
	}

	public void setPlayerFinishedLevel(boolean playerFinishedLevel) {
		this.playerFinishedLevel = playerFinishedLevel;
	}
}
