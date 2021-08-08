package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;
import com.mygdx.game.prototype.PrototypeCharMove;

public class DesktopLauncher {

	private final static int SCREEN_WIDTH = 800;
	private final static int SCREEN_HEIGHT = 480;

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.height = SCREEN_HEIGHT;
		Options.setWindowHeight(SCREEN_HEIGHT);

		config.width = SCREEN_WIDTH;
		Options.setWindowWidth(SCREEN_WIDTH);

		//new LwjglApplication(new UncredibleFighters(), config);
		new LwjglApplication(new PrototypeCharMove(), config);
	}
}
