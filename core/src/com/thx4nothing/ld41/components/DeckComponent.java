package com.thx4nothing.ld41.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.thx4nothing.ld41.cards.Card;

public class DeckComponent implements Component {
	public Array<Card.Cards> cards = new Array<>();

	public DeckComponent() {
		cards.add(Card.Cards.DEFEND);
		cards.add(Card.Cards.DEFEND);
		cards.add(Card.Cards.DEFEND);
		cards.add(Card.Cards.JUMP_ATTACK);
		cards.add(Card.Cards.JUMP_ATTACK);
		cards.add(Card.Cards.JUMP_ATTACK);
		cards.add(Card.Cards.FIREBALL);
		cards.add(Card.Cards.FIREBALL);
		cards.add(Card.Cards.FIREBALL);
	}

	public void shuffle() {
		cards.shuffle();
	}

	public Card.Cards draw() {
		return cards.pop();
	}
}
