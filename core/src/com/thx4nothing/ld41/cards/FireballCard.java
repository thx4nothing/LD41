package com.thx4nothing.ld41.cards;

import com.badlogic.ashley.core.Entity;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.levels.BattleWorld;
import com.thx4nothing.ld41.util.Mappers;

public class FireballCard extends Card {
	public FireballCard() {
		name = Name.FIREBALL;
		effect = "Throw a fireball at your enemy";
		level = 0;
	}

	@Override public void doEffect(Entity entity) {
		CardComponent card = null;
		if (entity instanceof Player) card = Mappers.card.get(BattleWorld.enemy);
		else if (entity instanceof Enemy) card = Mappers.card.get(BattleWorld.player);
		if (card == null) return;
		for (int i = 0; i <= level; i++)
			card.killCard();

		card.burn = true;
	}
}
