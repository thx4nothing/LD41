package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.components.DeckComponent;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.components.ScoreComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.util.Mappers;
import com.thx4nothing.ld41.util.MyInput;

import java.util.Random;

public class TurnSystem extends IteratingSystem {

	public boolean update = false;
	private int worldWidth, worldHeight;

	private enum Action {JUMP, DOUBLEJUMP, LEFT, RIGHT, CARD}

	private Action lastAction;

	private Random random = new Random();

	public TurnSystem() {
		super(Family.all(PositionComponent.class, DeckComponent.class).get());
	}

	@Override protected void processEntity(Entity entity, float deltaTime) {
		if (Mappers.score.has(entity)) {
			update = false;
			PositionComponent pos = Mappers.pos.get(entity);
			if (MyInput.isKeyJustReleased(Input.Keys.D)) {
				if (move(pos.pos, 1, 0)) {
					pos.pos.x++;
					lastAction = Action.RIGHT;
					update = true;
				}
			} else if (MyInput.isKeyJustReleased(Input.Keys.A)) {
				if (move(pos.pos, -1, 0)) {
					pos.pos.x--;
					lastAction = Action.LEFT;
					update = true;
				}
			} else if (MyInput.isKeyJustReleased((Input.Keys.W))) {
				if (lastAction == Action.JUMP) {
					if (move(pos.pos, 0, 1)) {
						pos.pos.y++;
						lastAction = Action.DOUBLEJUMP;
						update = true;
					}
				} else if (lastAction != Action.DOUBLEJUMP && lastAction != Action.JUMP) {
					if (move(pos.pos, 0, 1)) {
						pos.pos.y++;
						lastAction = Action.JUMP;
						update = true;
					}
				}
			}
			if (update) {
				Game.engine.getSystem(RenderingSystem.class).getCamera().position.set(pos.pos.x, pos.pos.y, 0);
			}
		} else if (update) {
			PositionComponent pos = Mappers.pos.get(entity);
			int x = random.nextInt(3) - 1;
			if (move(pos.pos, x, 0)) {
				pos.pos.x += x;
			}
		}

	}

	private boolean move(Vector2 pos, int x, int y) {
		worldHeight = Game.engine.getSystem(RenderingSystem.class).worldHeight;
		worldWidth = Game.engine.getSystem(RenderingSystem.class).worldWidth;
		int newX = (int) pos.x + x;
		int newY = (int) pos.y + y;
		if (newX >= 0 && newX < worldWidth) {
			Entity e = getEntityAtMapPoint(new Vector2(newX, newY));
			if (e != null && e instanceof Enemy) {
				ImmutableArray<Entity> entities = Game.g.engine.getEntitiesFor(Family.one(ScoreComponent.class).get());
				Game.g.initBattle((Player) entities.first(), (Enemy) e);
			} else return true;
		}
		return false;
	}

	public Entity getEntityAtMapPoint(Vector2 vector2) {
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
