package com.thx4nothing.ld41.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.thx4nothing.ld41.Game;

public class Assets {
	public static TiledMap MAP;

	public static void load() {
		AssetManager manager = Game.g.manager;

		get();
	}

	public static void get() {
		AssetManager manager = Game.g.manager;
	}

	public static void dispose() {
	}

}
