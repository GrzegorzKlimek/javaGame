package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.JavaSimpleGame;

/**
 * Created by grzegorz on 06.12.17.
 */

public class PlayScreen implements Screen {

    private JavaSimpleGame game;
    private Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public PlayScreen(JavaSimpleGame game) {
        this.game = game;
        this.texture = new Texture("badlogic.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(800, 480, gameCam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(texture, 0,0);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

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

    }
}
