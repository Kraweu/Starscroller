package at.kraweu.starscroller.desktop;

import at.kraweu.starscroller.Starscroller;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=480;
        config.height=854;
		new LwjglApplication(new Starscroller(), config);
	}
}
