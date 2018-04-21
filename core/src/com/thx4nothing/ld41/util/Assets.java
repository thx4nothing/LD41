package com.thx4nothing.ld41.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.thx4nothing.ld41.Game;

public class Assets {
	public static TiledMap MAP;

	public static void load() {
		AssetManager manager = Game.g.manager;
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load("level1.tmx", TiledMap.class);
		manager.finishLoading();
		get();
	}

	public static void get() {
		AssetManager manager = Game.g.manager;
		MAP = manager.get("level1.tmx");
	}

	public static void dispose() {
	}

}
