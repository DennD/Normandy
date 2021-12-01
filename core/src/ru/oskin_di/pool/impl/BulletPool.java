package ru.oskin_di.pool.impl;

import ru.oskin_di.pool.SpritesPool;
import ru.oskin_di.sprite.impl.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
