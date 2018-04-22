package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.util.Mappers;
import com.thx4nothing.ld41.util.MyInput;

import java.util.Random;

public class BattleSystem extends IteratingSystem {

	private Random random = new Random();

	public BattleSystem() {
		super(Family.all(CardComponent.class).get());
	}

	@Override protected void processEntity(Entity entity, float deltaTime) {
		CardComponent card = Mappers.card.get(entity);
		if (entity instanceof Player) {
			if (MyInput.isKeyJustReleased(Input.Keys.Q)) {
				card.play(0);
			} else if (MyInput.isKeyJustReleased(Input.Keys.W)) {
				card.play(1);
			} else if (MyInput.isKeyJustReleased(Input.Keys.E) && !card.burn) {
				card.play(2);
			}
		} else if (entity instanceof Enemy && card.activeCard == null) {
			card.play(random.nextInt(card.hand.size));
		}

	}
}
