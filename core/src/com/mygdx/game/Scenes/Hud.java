package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AndroidAdventuresGame;
import com.mygdx.game.Tools.Map;


/**
 * Created by grzegorz on 06.12.17.
 */

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private float timeCount;
    private AndroidAdventuresGame game;
    private int score;


    private Label coundDownLabel;
    private static Label scoreLebel;
    private Label timeLebel;
    private Label levelLebel;
    private Label worldLebel;
    private Label gamerLebel;

    public  Hud (AndroidAdventuresGame game, Map map) {

        timeCount = 0;
        this.game = game;
        score = game.getScore();

        viewport = new FitViewport(map.getHeight(), map.getWidth(), new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        coundDownLabel = new Label(String.format("%03d", game.getWorldTimer()), new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        scoreLebel = new Label(String.format("%06d", score), new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        timeLebel = new Label("TIME", new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        levelLebel= new Label("1-1", new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        worldLebel= new Label("WORLD", new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        gamerLebel =new Label("SCORE", new Label.LabelStyle( new BitmapFont(), Color.WHITE));

        table.add(gamerLebel).expandX().padTop(10);
        table.add(worldLebel).expandX().padTop(10);
        table.add(timeLebel).expandX().padTop(10);
        table.row();
        table.add(scoreLebel).expandX();
        table.add(levelLebel).expandX();
        table.add(coundDownLabel).expandX();
        stage.addActor(table);

    }

    public void update(float dt){

            coundDownLabel.setText(String.format("%03d",game.getWorldTimer()));

        updateScore();

    }


    public void updateScore(){
        score = game.getScore();
        scoreLebel.setText(String.format("%06d",score));
    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
