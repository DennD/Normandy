package ru.oskin_di.screen.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.oskin_di.screen.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture background;
    private Texture ship;
    private Vector2 touch;
    private Vector2 route;
    private Vector2 speed;
    private float positionX;
    private float positionY;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!checkVector(touch) || speed.len() != 0) {
            stopMovement();
            touch.set(screenX, Gdx.graphics.getHeight() - screenY);
            return super.touchDown(screenX, screenY, pointer, button);
        }
        if (!checkVector(route)) {
            route.set(screenX, Gdx.graphics.getHeight() - screenY);
            speed = new Vector2(route).sub(touch).nor();
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void show() {
        super.show();
        background = new Texture("galactic.jpg");
        ship = new Texture("normandy.png");
        touch = new Vector2(-1, -1);
        route = new Vector2(-1, -1);
        speed = new Vector2();
        positionX = Gdx.graphics.getWidth() / 2;
        positionY = Gdx.graphics.getHeight() / 2;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(ship, positionX - ship.getWidth() / 16, positionY - ship.getHeight() / 16, ship.getWidth() / 8, ship.getHeight() / 8);

        if (!checkVector(touch)) {
            batch.end();
            return;
        } else {
            positionX = touch.x;
            positionY = touch.y;
        }

        move();
        checkFinish();
        batch.end();
    }


    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
        ship.dispose();
    }

    private boolean checkVector(Vector2 vector) {
        if (vector.x == -1 && vector.y == -1) {
            return false;
        } else {
            return true;
        }
    }

    private void move() {
        if (checkVector(touch) && checkVector(route)) {
            touch.add(speed);
        }
    }

    private void checkFinish() {
        if (Math.abs(touch.x - route.x) < 1 && Math.abs(touch.y - route.y) < 1) {
            positionX = route.x;
            positionY = route.y;
            stopMovement();
        }
    }

    private void stopMovement() {
        touch.set(-1, -1);
        route.set(-1, -1);
        speed.set(0, 0);
    }
}
