package com.thx4nothing.ld41.cards;

import com.badlogic.ashley.core.Entity;

public abstract class Card {
	public Name name;
	public String effect;
	public int level;

	public abstract void doEffect(Entity entity);

	public enum Name {
		JUMP_ATTACK, DEFEND, FIREBALL, LOSE
	}
}
