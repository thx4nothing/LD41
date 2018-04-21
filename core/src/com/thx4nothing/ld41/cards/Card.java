package com.thx4nothing.ld41.cards;

public abstract class Card {
	public String name;
	public String effect;
	public int level;

	public enum Cards {
		JUMP_ATTACK, DEFEND, FIREBALL
	}
}
