package com.thx4nothing.ld41.cards;

public abstract class Card {
	public Name name;
	public String effect;
	public int level;

	public enum Name {
		JUMP_ATTACK, DEFEND, FIREBALL
	}
}
