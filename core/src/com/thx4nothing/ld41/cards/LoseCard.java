package com.thx4nothing.ld41.cards;

import com.badlogic.ashley.core.Entity;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.levels.BattleWorld;

public class LoseCard extends Card {

	public LoseCard() {
		name = Name.LOSE;
		effect = "Lose the battle";
		level = 0;
	}

	@Override public void doEffect(Entity entity) {
		if (entity instanceof Enemy) BattleWorld.wonBattle();
		else if (entity instanceof Player) BattleWorld.lostBattle();
	}
}
