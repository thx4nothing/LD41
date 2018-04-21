package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class BattleSystem extends IteratingSystem {

	public BattleSystem() {
		super(Family.all().get());
	}

	@Override protected void processEntity(Entity entity, float deltaTime) {


	}
}
