package com.thx4nothing.ld41.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.components.ScoreComponent;
import com.thx4nothing.ld41.components.TextureComponent;

public class Mappers {
	public static final ComponentMapper<PositionComponent> pos = ComponentMapper.getFor(PositionComponent.class);
	public static final ComponentMapper<TextureComponent> tex = ComponentMapper.getFor(TextureComponent.class);
	public static final ComponentMapper<ScoreComponent> score = ComponentMapper.getFor(ScoreComponent.class);
	public static final ComponentMapper<CardComponent> card = ComponentMapper.getFor(CardComponent.class);
}

