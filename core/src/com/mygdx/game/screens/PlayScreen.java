package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.JavaSimpleGame;
import com.mygdx.game.Scenes.Hud;

import Sprites.Player;


/**
 * Created by grzegorz on 06.12.17.
 */

public class PlayScreen implements Screen {

    private JavaSimpleGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Player player;
    private static final int TILED_GROUND_LAYER_INDEX = 1;
    private static final int TILED_PLATFORM_LAYER_INDEX = 2;
    private static final int TILED_LADDER_LAYER_INDEX = 4;
    private static final int TILED_UPPER_MARGIN_LAYER_INDEX = 6;

    public PlayScreen(JavaSimpleGame game) {
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
        player = new Player(world);


       int [] objectLayerIndexes = {TILED_GROUND_LAYER_INDEX, TILED_PLATFORM_LAYER_INDEX, TILED_LADDER_LAYER_INDEX, TILED_UPPER_MARGIN_LAYER_INDEX};

       for (int index : objectLayerIndexes) {
           addObjectLayerToTheWorld (index);
       }


    }

    private void addObjectLayerToTheWorld ( int indexOfLayer) {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get(indexOfLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() + rect.getWidth() / 2)/ JavaSimpleGame.PPM, (rect.getY() + rect.getHeight() / 2) / JavaSimpleGame.PPM );

            body = world.createBody(bdef);

            shape.setAsBox( rect.getWidth() / 2/ JavaSimpleGame.PPM, rect.getHeight() / 2 / JavaSimpleGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

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

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

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
