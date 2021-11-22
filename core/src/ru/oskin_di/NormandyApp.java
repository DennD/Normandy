package ru.oskin_di;

import com.badlogic.gdx.Game;
import ru.oskin_di.screen.impl.MenuScreen;

public class NormandyApp extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen());
    }

}
