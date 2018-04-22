package com.thx4nothing.ld41.levels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.systems.*;
import com.thx4nothing.ld41.util.Assets;
import com.thx4nothing.ld41.util.Mappers;
import com.thx4nothing.ld41.util.MyInput;

public class BattleWorld extends Level {

	public static Entity player;
	public static Entity enemy;

	public BattleWorld(Entity player, Entity enemy) {
		BattleWorld.player = player;
		BattleWorld.enemy = enemy;
	}

	@Override public void init() {

		RenderingSystem renderingSystem = new RenderingSystem(Assets.BATTLEMAP);

		TurnSystem turnSystem = new TurnSystem();
		BattleSystem battleSystem = new BattleSystem();
		CardSystem cardSystem = new CardSystem(2);
		RythmSystem rythmSystem = new RythmSystem(2);

		renderingSystem.getCamera().position.set(10.5f, 5, 0);//camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		renderingSystem.getCamera().zoom = 1f;

		turnSystem.setProcessing(false);

		Game.engine.addSystem(renderingSystem);
		Game.engine.addSystem(turnSystem);
		Game.engine.addSystem(cardSystem);
		Game.engine.addSystem(rythmSystem);
		Game.engine.addSystem(battleSystem);

		PositionComponent posP = Mappers.pos.get(player);
		posP.initBattle();
		posP.pos.x = 4;
		posP.pos.y = 1;
		posP = Mappers.pos.get(enemy);
		posP.initBattle();
		posP.pos.x = 16;
		posP.pos.y = 1;

		Game.engine.addEntity(player);
		Game.engine.addEntity(enemy);
	}

	@Override public void update() {
		Game.engine.update(Gdx.graphics.getDeltaTime());
		MyInput.clear();
	}

	public static void wonBattle() {
		//TODO: Reward
		Game.engine.removeEntity(enemy);
		//Game.g.endBattle();
	}

	public static void lostBattle() {
		//TODO: lose some cards
		Game.engine.removeEntity(enemy);
		//Game.g.endBattle();
	}
}
