package com.thx4nothing.ld41;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.thx4nothing.ld41.levels.Level;
import com.thx4nothing.ld41.levels.Level01;
import com.thx4nothing.ld41.util.Assets;
import com.thx4nothing.ld41.util.MyInput;

public class Game extends ApplicationAdapter {

	public static Game g = null;

	public static MyInput input;

	private FPSLogger fpslogger;

	public AssetManager manager;

	public static Engine engine;

	public static MessageDispatcher dispatcher = new MessageDispatcher();

	public Level level;

	@Override public void create() {
		g = this;
		manager = new AssetManager();
		Assets.load();
		input = new MyInput(Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D, Input.Keys.Q, Input.Keys.E, Input.Keys.CONTROL_LEFT);
		fpslogger = new FPSLogger();
		Gdx.input.setInputProcessor(input);
		engine = new Engine();
		level = new Level01();
		level.init();
	}

	@Override public void render() {
		fpslogger.log();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		level.update();
	}

	public void resize(int width, int height) {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
		Assets.dispose();
		manager.dispose();
	}

}
