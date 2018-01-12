package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.JavaSimpleGame;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Tools.B2WorldCreator;

import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Tools.WorldContactListener;


/**
 * Created by grzegorz on 06.12.17.
 */

public class PlayScreen implements Screen {

    private JavaSimpleGame game;
    private TextureAtlas atlas;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Player player;

    public PlayScreen(JavaSimpleGame game) {
        atlas = new TextureAtlas("PostacItp.pack");
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(JavaSimpleGame.V_WIDTH / JavaSimpleGame.PPM, JavaSimpleGame.V_HEIGHT / JavaSimpleGame.PPM, gameCam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("mapa-do-gry.tmx");
        renderer = new OrthogonalTiledMapRenderer(map , 1/JavaSimpleGame.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10 ), true);
        b2dr = new Box2DDebugRenderer();
        new B2WorldCreator(this);
        player = new Player(world, this);

        world.setContactListener(new WorldContactListener());

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }


    public  void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.b2body.applyLinearImpulse( new Vector2(0, 4f) , player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse( new Vector2(0.1f, 0) , player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
    }
    public void update(float dt) {
        handleInput(dt);
        world.step(1 / 60f, 6, 2);

        player.update(dt);
        hud.update(dt);

        gameCam.position.x = player.b2body.getPosition().x;
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

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
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
