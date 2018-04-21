package com.thx4nothing.ld41.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.components.TextureComponent;
import com.thx4nothing.ld41.util.Assets;

public class Player extends Entity {

	public Player() {
		PositionComponent pos = new PositionComponent();
		TextureComponent tex = new TextureComponent();
		pos.setPos(new Vector2(1, 1));
		tex.region = Assets.PLAYER;
		add(pos);
		add(tex);
		Game.engine.addEntity(this);
	}
}
