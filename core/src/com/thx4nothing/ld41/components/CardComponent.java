package com.thx4nothing.ld41.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.thx4nothing.ld41.cards.Card;

import java.util.Random;

public class CardComponent implements Component {
	public Array<Card> deck = new Array<>();
	public Array<Card> graveyard = new Array<>();
	public Array<Card> hand = new Array<>();

	public Array<Card> overHand = new Array<>();

	public int dummyCards = 0;

	public Card activeCard;

	private Random random = new Random();
	public boolean burn = false;

	public void play(int i) {
		activeCard = hand.get(i);
		handToGrave();
	}

	private void handToGrave() {
		graveyard.addAll(hand);
		hand.clear();
	}

	public void killCard() {
		if (dummyCards > 0) dummyCards--;
		else if (graveyard.size > 0) {
			int a = random.nextInt(graveyard.size);
			graveyard.removeIndex(a);
		} else if (deck.size > 0) {
			int a = random.nextInt(deck.size);
			deck.removeIndex(a);
		}
	}

	public void addCard(Card card) {
		deck.add(card);
		deck.shuffle();
	}

	public void newHand() {
		hand.clear();
		int a = random.nextInt(deck.size);
		hand.add(deck.get(a));
		deck.removeIndex(a);
		a = random.nextInt(deck.size);
		hand.add(deck.get(a));
		deck.removeIndex(a);
	}
}
