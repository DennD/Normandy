package ru.oskin_di.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.oskin_di.NormandyApp;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1500;
        config.height = 1000;
        config.resizable = false;
        new LwjglApplication(new NormandyApp(), config);
    }
}
