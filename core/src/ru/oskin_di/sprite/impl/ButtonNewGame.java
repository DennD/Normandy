package ru.oskin_di.sprite.impl;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.oskin_di.math.Rect;
import ru.oskin_di.screen.impl.GameScreen;
import ru.oskin_di.sprite.BaseButton;

public class ButtonNewGame extends BaseButton {

    private static final float HEIGHT = 0.08f;

    private final GameScreen gameScreen;

    public ButtonNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(0.4f);

    }

    @Override
    public void action() {
        gameScreen.reload();
    }
}