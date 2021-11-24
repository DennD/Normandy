package ru.oskin_di.screen.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.oskin_di.math.Rect;
import ru.oskin_di.screen.BaseScreen;
import ru.oskin_di.sprite.impl.Background;
import ru.oskin_di.sprite.impl.Ship;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Vector2 pos;
    private Vector2 v;

    private Background background;
    private Ship ship;

    private final float V_LEN = 0.01f;

    @Override
    public void show() {
        super.show();
        img = new Texture("normandy.png");
        bg = new Texture("galactic.jpg");
        pos = new Vector2();
        v = new Vector2();
        background = new Background(bg);
        ship = new Ship(img);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        ship.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        ship.draw(batch);

        pos.set(ship.getLeft() + ship.getHalfWidth(), ship.getBottom() + ship.getHalfHeight());


        if (pos.dst(getTouch()) > V_LEN) {
            ship.move(v);
        } else {
            ship.stop(getTouch());
        }

        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        v.set(touch.cpy().sub(pos).setLength(V_LEN));
        return super.touchDown(touch, pointer, button);
    }
}
