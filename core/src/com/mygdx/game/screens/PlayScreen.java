package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AndroidAdventuresGame;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.agents.SpriteAgent;
import com.mygdx.game.Sprites.agents.enemies.DarkKnight;
import com.mygdx.game.Tools.B2WorldCreator;

import com.mygdx.game.Sprites.agents.protagonist.Player;
import com.mygdx.game.Tools.Map;
import com.mygdx.game.Tools.WorldContactListener;


/**
 * Created by grzegorz on 06.12.17.
 */

public class PlayScreen implements Screen {

    private String pathToFirstTileMap = "level1.tmx";
    private String pathToPacks = "packs/android_and_enemies.pack";
    private AndroidAdventuresGame game;
    private TextureAtlas atlas;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private Map map;
    private OrthogonalTiledMapRenderer renderer;
    private  World world;
    private Box2DDebugRenderer b2dr;
    private SpriteAgent player;
    private SpriteAgent enemy;
    private Arrive<Vector2> arrive;

    public PlayScreen(AndroidAdventuresGame game) {
        atlas = new TextureAtlas(pathToPacks);
        this.game = game;
        gameCam = new OrthographicCamera();
        map = new Map(pathToFirstTileMap);
        gamePort = new FitViewport(map.getWidth() / AndroidAdventuresGame.PPM,  map.getHeight()/ AndroidAdventuresGame.PPM, gameCam);
        hud = new Hud(game, map);
        renderer = new OrthogonalTiledMapRenderer(map.getTiledMap() , 1/ AndroidAdventuresGame.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10 ), true);
        b2dr = new Box2DDebugRenderer();
        //b2dr.setDrawBodies(false);
        new B2WorldCreator(this);
        player = new Player(this, new Vector2(32,32));
        enemy = new DarkKnight(this, new Vector2(250,50));

        world.setContactListener(new WorldContactListener());

        arrive = new Arrive<Vector2>(enemy.getSteerableBody(), player.getSteerableBody())
                .setTimeToTarget(0.01f)
                .setArrivalTolerance(0.5f)
                .setDecelerationRadius(3);

        enemy.setBehaviour(arrive);

    }

    public TextureAtlas getAtlas(){

        return atlas;
    }


    public void update(float dt) {

        world.step(1 / 60f, 6, 2);

        player.update(dt);
        enemy.update(dt);
        hud.update(dt);

        gameCam.position.x = player.steerableB2body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, gameCam.combined);

        Batch gameBatch = game.getBatch();

        gameBatch.setProjectionMatrix(gameCam.combined);
        gameBatch.begin();
        player.draw(gameBatch);
        enemy.draw(gameBatch);
        gameBatch.end();

        gameBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    public Map getMap(){

        return map;
    }
    public AndroidAdventuresGame getGame() {

        return game;
    }

    public  World getWorld() {
            return world;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}

