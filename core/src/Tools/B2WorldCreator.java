package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.JavaSimpleGame;

/**
 * Created by grzegorz on 03.01.18.
 */

public class B2WorldCreator {
    /*
    public B2WorldCreator(World world, TiledMap map){
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
    }
    */
}
