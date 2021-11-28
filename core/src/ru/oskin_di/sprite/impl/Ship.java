package ru.oskin_di.sprite.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.oskin_di.math.Rect;
import ru.oskin_di.sprite.Sprite;

public class Ship extends Sprite {


    public Ship(Texture texture) {
        super(new TextureRegion(texture));
        this.scale = 0.1f;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        this.pos.set(worldBounds.pos);
    }

    public void move(Vector2 speed) {
        setLeft(getLeft()+speed.x);
        setBottom(getBottom()+speed.y);
    }

    public void stop(Vector2 touch) {
        setLeft(touch.x - getHalfWidth());
        setBottom(touch.y - getHalfHeight());
    }

}
