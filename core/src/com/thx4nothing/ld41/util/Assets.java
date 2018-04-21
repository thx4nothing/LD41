package com.thx4nothing.ld41.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.thx4nothing.ld41.Game;

public class Assets {
	public static TextureRegion PLAYER;
	public static TextureRegion ENEMY;
	public static TiledMap MAP;
	public static TiledMap BATTLEMAP;

	public static void load() {
		AssetManager manager = Game.g.manager;
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load("level1.tmx", TiledMap.class);
		manager.load("battle.tmx", TiledMap.class);
		manager.load("player.png", Texture.class);
		manager.load("enemy.png", Texture.class);
		manager.finishLoading();
		get();
	}

	public static void get() {
		AssetManager manager = Game.g.manager;
		MAP = manager.get("level1.tmx");
		BATTLEMAP = manager.get("battle.tmx");
		PLAYER = new TextureRegion((Texture) manager.get("player.png"));
		ENEMY = new TextureRegion((Texture) manager.get("enemy.png"));
	}

	public static void dispose() {
	}

}
