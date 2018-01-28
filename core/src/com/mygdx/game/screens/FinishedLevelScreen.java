package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AndroidAdventuresGame;

/**
 * Created by grzegorz on 29.01.18.
 */

public class FinishedLevelScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public FinishedLevelScreen(Game game){
        this.game = game;
        viewport = new FitViewport(AndroidAdventuresGame.V_WIDTH, AndroidAdventuresGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((AndroidAdventuresGame) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("FINISHED LEVEL", font);
        Label scoreLabel = new Label("SCORE", font);
        String points = String.format("%03d", ((AndroidAdventuresGame) game).getScore()  );
        Label pointLabel = new Label(  points, font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(scoreLabel).expandX().padTop(10f);
        table.row();
        table.add(pointLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((AndroidAdventuresGame) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
