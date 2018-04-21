package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.util.Mappers;
import com.thx4nothing.ld41.util.MyInput;

import java.util.Random;

public class BattleSystem extends IteratingSystem {

	private boolean update = false;
	private Random random = new Random();

	public BattleSystem() {
		super(Family.all(CardComponent.class).get());
	}

	@Override protected void processEntity(Entity entity, float deltaTime) {
		if (Mappers.score.has(entity)) {
			update = false;
			CardComponent card = Mappers.card.get(entity);
			if (MyInput.isKeyJustReleased(Input.Keys.Q)) {
				card.play(0);
				update = true;
			} else if (MyInput.isKeyJustReleased(Input.Keys.W)) {
				card.play(1);
				update = true;
			} else if (MyInput.isKeyJustReleased(Input.Keys.E)) {
				card.play(2);
				update = true;
			}
		} else if (update) {
			CardComponent card = Mappers.card.get(entity);
			card.play(random.nextInt(3));
		}
	}

}
