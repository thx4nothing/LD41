package com.thx4nothing.ld41.levels;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.systems.BattleSystem;
import com.thx4nothing.ld41.systems.CardSystem;
import com.thx4nothing.ld41.systems.RenderingSystem;
import com.thx4nothing.ld41.systems.TurnSystem;
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
		Game.engine.getSystem(RenderingSystem.class).changeMap(Assets.BATTLEMAP);
		Game.engine.getSystem(RenderingSystem.class).getCamera().position.set(10, 5, 0);//camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		Game.engine.getSystem(RenderingSystem.class).getCamera().zoom = 1f;
		PositionComponent posP = Mappers.pos.get(player);
		posP.initBattle();
		posP.pos.x = 4;
		posP.pos.y = 1;
		posP = Mappers.pos.get(enemy);
		posP.initBattle();
		posP.pos.x = 13;
		posP.pos.y = 1;
		Game.engine.getSystem(BattleSystem.class).setProcessing(true);
		Game.engine.getSystem(CardSystem.class).setProcessing(true);
		Game.engine.getSystem(TurnSystem.class).setProcessing(false);
	}

	@Override public void update() {
		Game.engine.update(Gdx.graphics.getDeltaTime());
		MyInput.clear();
	}

	public static void wonBattle() {
		//TODO: Reward
		Game.engine.removeEntity(enemy);
		Game.g.endBattle();
	}

	public static void lostBattle() {
		//TODO: lose some cards
		Game.engine.removeEntity(enemy);
		Game.g.endBattle();
	}
}
