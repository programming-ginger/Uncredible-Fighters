package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;
import com.mygdx.game.data.Options;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.height = Options.getWindowHeight();

		config.width = Options.getWindowWidth();;

		new LwjglApplication(UncredibleFighters.getInstance(), config);
	}
}
