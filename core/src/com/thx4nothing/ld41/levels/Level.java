package com.thx4nothing.ld41.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Level {

	protected static SpriteBatch batch = new SpriteBatch();

	public abstract void init();

	public abstract void update();
}
