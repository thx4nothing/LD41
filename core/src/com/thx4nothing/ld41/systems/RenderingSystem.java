package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.components.TextureComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.util.Assets;
import com.thx4nothing.ld41.util.Mappers;
import net.dermetfan.gdx.maps.MapUtils;

import java.util.Comparator;

public class RenderingSystem extends IteratingSystem {

	private SpriteBatch batch;
	private ShapeRenderer sr;
	private Array<Entity> renderQueue;

	public int worldWidth;
	public int worldHeight;
	private final float UNIT_SIZE = 1.0f / 32.0f;

	private OrthogonalTiledMapRenderer mapRenderer;

	private int[] backgroundLayers = { 0, 1 };
	private int[] foregroundLayers = {};

	private FitViewport viewport;
	private OrthographicCamera camera;

	private Comparator<Entity> comparator = new Comparator<Entity>() {
		@Override public int compare(Entity o1, Entity o2) {
			return 0;
		}
	};

	@SuppressWarnings("unchecked") public RenderingSystem(TiledMap map) {
		super(Family.all(PositionComponent.class, TextureComponent.class).get());
		this.batch = new SpriteBatch();
		sr = new ShapeRenderer();
		renderQueue = new Array<Entity>();

		changeMap(map);

	}

	@Override public void update(float deltaTime) {
		super.update(deltaTime);
		renderQueue.sort(comparator);

		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render(backgroundLayers);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (Entity entity : renderQueue) {
			TextureComponent tex = Mappers.tex.get(entity);
			PositionComponent pos = Mappers.pos.get(entity);
			if (tex.region == null) continue;

			float width = tex.region.getRegionWidth() * UNIT_SIZE;
			float height = tex.region.getRegionHeight() * UNIT_SIZE;
			float originX = width * 0.25f;
			float originY = height * 0.5f;

			batch.draw(tex.region, pos.pos.x, pos.pos.y, width, height);

			if (entity instanceof Enemy) {
				width = Assets.CARDBACK.getRegionWidth() * UNIT_SIZE;
				height = Assets.CARDBACK.getRegionHeight() * UNIT_SIZE;
				//13,4
				for (int i = 0; i < 3; i++) {
					batch.draw(Assets.CARDBACK, 15 + i, 3, width, height);
				}
				CardComponent card = Mappers.card.get(entity);
				if (card.activeCard != null) {
					batch.draw(Assets.CARDFRONT, 11, 4, width, height);
				}
			} else if (entity instanceof Player) {
				width = Assets.CARDFRONT.getRegionWidth() * UNIT_SIZE;
				height = Assets.CARDFRONT.getRegionHeight() * UNIT_SIZE;
				//13,4
				for (int i = 0; i < 3; i++) {
					batch.draw(Assets.CARDFRONT, 3 + i, 3, width, height);
				}
				CardComponent card = Mappers.card.get(entity);
				if (card.activeCard != null) {
					batch.draw(Assets.CARDFRONT, 9, 4, width, height);
				}
			}
		}
		float width = Assets.TICK0.getRegionWidth() * UNIT_SIZE;
		float height = Assets.TICK0.getRegionHeight() * UNIT_SIZE;
		if (Game.engine.getSystem(RythmSystem.class).tick == 0) batch.draw(Assets.TICK0, 10 + 0.25f, 8 - 0.75f, width, height);
		if (Game.engine.getSystem(RythmSystem.class).tick == 1) batch.draw(Assets.TICK1, 10 + 0.25f, 8 - 0.75f, width, height);
		if (Game.engine.getSystem(RythmSystem.class).tick == 2) batch.draw(Assets.TICK2, 10 + 0.25f, 8 - 0.75f, width, height);
		batch.end();
		renderQueue.clear();
		mapRenderer.render(foregroundLayers);
	}

	@Override protected void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void changeMap(TiledMap map) {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		worldWidth = (int) (MapUtils.size(map).x * UNIT_SIZE);
		worldHeight = (int) (MapUtils.size(map).y * UNIT_SIZE);
		camera = new OrthographicCamera(worldWidth, worldWidth * (h / w));
		viewport = new FitViewport(worldWidth, worldWidth * (h / w), camera);
		camera.position.set(1, 1, 0);//camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.zoom = 0.4f;
		camera.update();

		mapRenderer = new OrthogonalTiledMapRenderer(map, UNIT_SIZE, batch);
		mapRenderer.setView(camera);
	}
}
