package ru.oskin_di.sprite.impl;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.oskin_di.math.Rect;
import ru.oskin_di.sprite.Sprite;

public class Ship extends Sprite {

    private Rect worldBounds;
    private static final float HEIGHT = 0.1f;
    private static final float V_LEN = 0.01f;
    private Vector2 touch;
    private Vector2 v;


    public Ship(TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, atlas.findRegion("main_ship").getRegionWidth() / 2, atlas.findRegion("main_ship").getRegionHeight()));
        this.touch = new Vector2();
        this.v = new Vector2();
        this.pos.set(0, -1f);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setBottom(this.worldBounds.getBottom());
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (pos.dst(touch) > V_LEN) {
            pos.add(v);
        } else {
            pos.set(touch);
        }

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        selectSpeed(touch);
        return false;
    }

    public boolean keyUp(int keycode) {
        Vector2 touch = new Vector2();
        selectRoute(keycode, touch);
        selectSpeed(touch);
        return false;
    }

    public boolean keyDown(int keycode) {
        Vector2 touch = new Vector2();
        selectRoute(keycode, touch);
        selectSpeed(touch);
        return false;
    }

    private void checkBorder(Vector2 touch) {
        if (touch.x <= worldBounds.getLeft() + getHalfWidth()) {
            touch.x = worldBounds.getLeft() + getHalfWidth();
        }

        if (touch.x >= worldBounds.getRight() - getHalfWidth()) {
            touch.x = worldBounds.getRight() - getHalfWidth();
        }

        if (touch.y <= worldBounds.getBottom() + getHalfHeight()) {
            touch.y = worldBounds.getBottom() + getHalfHeight();
        }

        if (touch.y >= worldBounds.getTop() / 4 - getHalfHeight()) {
            touch.y = worldBounds.getTop() / 4 - getHalfHeight();
        }
    }

    private void selectRoute(int keycode, Vector2 touch) {

        if (keycode == 19) {
            touch.set(this.touch.x, this.touch.y + 0.1f);
        }
        if (keycode == 20) {
            touch.set(this.touch.x, this.touch.y - 0.1f);
        }
        if (keycode == 21) {
            touch.set(this.touch.x - 0.1f, this.touch.y);
        }
        if (keycode == 22) {
            touch.set(this.touch.x + 0.1f, this.touch.y);
        }
    }

    private void selectSpeed(Vector2 touch) {
        checkBorder(touch);
        this.touch.set(touch);
        this.v.set(touch.cpy().sub(pos)).setLength(V_LEN);
    }
}
