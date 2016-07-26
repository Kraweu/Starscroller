package at.kraweu.starscroller.android;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import at.kraweu.starscroller.Starscroller;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Starscroller(), config);
	}
}
