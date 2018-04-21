package com.thx4nothing.ld41.levels;

import com.badlogic.gdx.Gdx;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.systems.RenderingSystem;
import com.thx4nothing.ld41.util.MyInput;

public class Level01 extends Level {
	@Override public void init() {
		RenderingSystem renderingSystem = new RenderingSystem();

		Game.engine.addSystem(renderingSystem);
	}

	@Override public void update() {
		Game.engine.update(Gdx.graphics.getDeltaTime());
		MyInput.clear();
	}
}
