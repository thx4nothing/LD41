package com.thx4nothing.ld41.cards;

import com.badlogic.ashley.core.Entity;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.util.Mappers;

public class DefendCard extends Card {
	public DefendCard() {
		name = Name.DEFEND;
		effect = "Defend your deck with dummy cards";
		level = 0;
	}

	@Override public void doEffect(Entity entity) {
		CardComponent card = Mappers.card.get(entity);
		card.dummyCards += level;
	}
}
