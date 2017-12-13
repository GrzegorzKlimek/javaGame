package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.JavaSimpleGame;


/**
 * Created by grzegorz on 06.12.17.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label coundDownLabel;
    Label scoreLebel;
    Label timeLebel;
    Label levelLebel;
    Label worldLebel;
    Label gamerLebel;

    public  Hud (SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(JavaSimpleGame.V_HEIGHT, JavaSimpleGame.V_WIDTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        coundDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        scoreLebel = new Label(String.format("%06d", score), new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        timeLebel = new Label("TIME", new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        levelLebel= new Label("1-1", new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        worldLebel= new Label("WORLD", new Label.LabelStyle( new BitmapFont(), Color.WHITE));
        gamerLebel =new Label("GAMER", new Label.LabelStyle( new BitmapFont(), Color.WHITE));

        table.add(gamerLebel).expandX().padTop(10);
        table.add(worldLebel).expandX().padTop(10);
        table.add(timeLebel).expandX().padTop(10);
        table.row();
        table.add(scoreLebel).expandX();
        table.add(levelLebel).expandX();
        table.add(coundDownLabel).expandX();
        stage.addActor(table);

    }
}