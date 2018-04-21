package com.thx4nothing.ld41.levels;

import com.badlogic.gdx.Gdx;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.systems.RenderingSystem;
import com.thx4nothing.ld41.systems.TurnSystem;
import com.thx4nothing.ld41.util.MyInput;

public class OverWorld extends Level {
	@Override public void init() {
		RenderingSystem renderingSystem = new RenderingSystem();
		TurnSystem turnSystem = new TurnSystem();
		Game.engine.addSystem(renderingSystem);
		Game.engine.addSystem(turnSystem);
		new Player();
		new Enemy();
	}

	@Override public void update() {
		Game.engine.update(Gdx.graphics.getDeltaTime());
		MyInput.clear();
	}
}
