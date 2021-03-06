package com.thx4nothing.ld41.levels;

import com.badlogic.gdx.Gdx;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.systems.*;
import com.thx4nothing.ld41.util.Assets;
import com.thx4nothing.ld41.util.Mappers;
import com.thx4nothing.ld41.util.MyInput;

public class OverWorld extends Level {

	public Player player;
	public Enemy enemy;

	@Override public void init() {
		RenderingSystem renderingSystem = new RenderingSystem(Assets.MAP);
		TurnSystem turnSystem = new TurnSystem();
		BattleSystem battleSystem = new BattleSystem();
		CardSystem cardSystem = new CardSystem(2);
		RythmSystem rythmSystem = new RythmSystem(2);
		Game.engine.addSystem(renderingSystem);
		Game.engine.addSystem(turnSystem);
		Game.engine.addSystem(cardSystem);
		Game.engine.addSystem(rythmSystem);
		Game.engine.addSystem(battleSystem);
		cardSystem.setProcessing(false);
		battleSystem.setProcessing(false);
		rythmSystem.setProcessing(false);

		player = new Player();
		enemy = new Enemy();
		Game.engine.addEntity(player);
		Game.engine.addEntity(enemy);
	}

	@Override public void update() {
		Game.engine.update(Gdx.graphics.getDeltaTime());
		MyInput.clear();
	}

	public void returnFromBattle() {
		Game.engine.getSystem(RenderingSystem.class).changeMap(Assets.MAP);
		PositionComponent pos = Mappers.pos.get(player);
		pos.revertBattle();
		Game.engine.getSystem(RenderingSystem.class).getCamera().position.set(pos.pos.x, pos.pos.y, 0);
		Game.engine.getSystem(RenderingSystem.class).getCamera().zoom = 0.4f;
		Game.engine.getSystem(BattleSystem.class).setProcessing(false);
		Game.engine.getSystem(CardSystem.class).setProcessing(false);
		Game.engine.getSystem(TurnSystem.class).setProcessing(true);
		Game.engine.getSystem(RythmSystem.class).setProcessing(false);
	}
}
