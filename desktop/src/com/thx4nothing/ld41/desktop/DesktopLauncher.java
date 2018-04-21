package com.thx4nothing.ld41.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thx4nothing.ld41.Game;

public class DesktopLauncher {
	public final static String GAME_TITLE = "LD41";

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 10;
		config.foregroundFPS = 60;
		config.fullscreen = false;
		config.resizable = false;
		config.title = GAME_TITLE;
		config.height = 720;
		config.width = 1280;
		config.x = -1;
		config.y = -1;
		config.vSyncEnabled = false;
		new LwjglApplication(new Game(), config);
	}
}
