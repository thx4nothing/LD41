package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.components.ScoreComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.util.Mappers;
import com.thx4nothing.ld41.util.MyInput;

import java.util.Random;

public class TurnSystem extends IteratingSystem {

	private enum Action {JUMP, DOUBLEJUMP, LEFT, RIGHT, CARD}

	private Action lastAction;

	private Random random = new Random();

	public TurnSystem() {
		super(Family.all(PositionComponent.class, CardComponent.class).get());
	}

	@Override protected void processEntity(Entity entity, float deltaTime) {
		if (entity instanceof Player) {
			PositionComponent pos = Mappers.pos.get(entity);
			CardComponent card = Mappers.card.get(entity);
			if (MyInput.isKeyJustReleased(Input.Keys.D)) {
				if (canMove(pos.pos, 1, 0)) {
					pos.pos.x++;
					lastAction = Action.RIGHT;
				}
			} else if (MyInput.isKeyJustReleased(Input.Keys.A)) {
				if (canMove(pos.pos, -1, 0)) {
					pos.pos.x--;
					lastAction = Action.LEFT;
				}
			} else if (MyInput.isKeyJustReleased((Input.Keys.W))) {
				if (lastAction == Action.JUMP) {
					if (canMove(pos.pos, 0, 1)) {
						pos.pos.y++;
						lastAction = Action.DOUBLEJUMP;
					}
				} else if (lastAction != Action.DOUBLEJUMP && lastAction != Action.JUMP) {
					if (canMove(pos.pos, 0, 1)) {
						pos.pos.y++;
						lastAction = Action.JUMP;
					}
				}
			} else if (MyInput.isKeyJustReleased(Input.Keys.Q)) {
				lastAction = Action.CARD;
			} else if (MyInput.isKeyJustReleased(Input.Keys.E)) {
				lastAction = Action.CARD;
			}

			//Game.engine.getSystem(RenderingSystem.class).getCamera().position.set(pos.pos.x, pos.pos.y, 0);
		}
		if (entity instanceof Enemy) {
			PositionComponent pos = Mappers.pos.get(entity);
			pos.timer -= deltaTime;
			if (pos.timer < 0) {
				int x = random.nextInt(3) - 1;
				if (canMove(pos.pos, x, 0)) {
					pos.pos.x += x;
				}
				pos.timer = 1;
			}
		}

	}

	private boolean canMove(Vector2 pos, int x, int y) {
		int worldHeight = Game.engine.getSystem(RenderingSystem.class).worldHeight;
		int worldWidth = Game.engine.getSystem(RenderingSystem.class).worldWidth;
		int newX = (int) pos.x + x;
		int newY = (int) pos.y + y;
		if (newX >= 0 && newX < worldWidth) {
			Entity e = getEntityAtMapPoint(new Vector2(newX, newY));
			if (e != null) {
				if (e instanceof Enemy && (newX != pos.x || newY != pos.y)) {
					ImmutableArray<Entity> entities = Game.engine.getEntitiesFor(Family.one(ScoreComponent.class).get());
					Game.g.initBattle((Player) entities.first(), (Enemy) e);
				} else if (e instanceof Player) return false;
			} else return true;
		}
		return false;
	}

	private Entity getEntityAtMapPoint(Vector2 vector2) {
		Entity e = null;
		Vector2 pos2;
		ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(PositionComponent.class).get());
		for (Entity entity : entities) {
			pos2 = Mappers.pos.get(entity).pos;
			if (pos2.x == vector2.x && pos2.y == vector2.y) {
				e = entity;
				break;
			}
		}
		return e;
	}
}
