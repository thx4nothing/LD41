package com.thx4nothing.ld41.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {

	public final Vector2 pos = new Vector2();

	public void setPos(Vector2 pos) {
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
}
