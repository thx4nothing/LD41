package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.thx4nothing.ld41.util.Assets;
import net.dermetfan.gdx.maps.MapUtils;

import java.util.Comparator;

public class RenderingSystem extends IteratingSystem {

	private SpriteBatch batch;
	private ShapeRenderer sr;
	private Array<Entity> renderQueue;

	public int worldWidth = 100;
	public int worldHeight = 100;
	private final float UNIT_SIZE = 1.0f / 32.0f;

	private OrthogonalTiledMapRenderer mapRenderer;

	private int[] backgroundLayers = { 0 };
	private int[] foregroundLayers = { 1 };

	private FitViewport viewport;
	private OrthographicCamera camera;

	private Comparator<Entity> comparator = new Comparator<Entity>() {
		@Override public int compare(Entity o1, Entity o2) {
			return 0;
		}
	};

	@SuppressWarnings("unchecked") public RenderingSystem() {
		super(Family.all().get());
		this.batch = new SpriteBatch();
		sr = new ShapeRenderer();
		renderQueue = new Array<Entity>();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		worldWidth = (int) (MapUtils.size(Assets.MAP).x * UNIT_SIZE);
		worldHeight = (int) (MapUtils.size(Assets.MAP).y * UNIT_SIZE);
		camera = new OrthographicCamera(worldWidth, worldWidth * (h / w));
		viewport = new FitViewport(worldWidth, worldWidth * (h / w), camera);
		camera.position.set(0, 0, 0);//camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.zoom = 0.4f;
		camera.update();

		mapRenderer = new OrthogonalTiledMapRenderer(Assets.MAP, UNIT_SIZE, batch);
		mapRenderer.setView(camera);

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

		}

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
}
