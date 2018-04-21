package com.thx4nothing.ld41.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.thx4nothing.ld41.cards.Card;
import com.thx4nothing.ld41.cards.DefendCard;
import com.thx4nothing.ld41.cards.FireballCard;
import com.thx4nothing.ld41.cards.JumpAttackCard;

import java.util.Random;

public class CardComponent implements Component {
	public Array<Card> deck = new Array<>();
	public Array<Card> graveyard = new Array<>();
	public Array<Card> hand = new Array<>();
	public int dummyCards = 0;

	public Card activeCard;

	private Random random = new Random();
	public boolean burn = false;

	public CardComponent() {
		deck.add(new JumpAttackCard());
		deck.add(new JumpAttackCard());
		deck.add(new JumpAttackCard());
		deck.add(new DefendCard());
		deck.add(new DefendCard());
		deck.add(new DefendCard());
		deck.add(new FireballCard());
		deck.add(new FireballCard());
		deck.add(new FireballCard());
		deck.shuffle();
	}

	public void play(int i) {
		activeCard = hand.get(i);
	}

	public void killCardInDeck() {
		if (dummyCards > 0) dummyCards--;
		else if (deck.size > 0) {
			int a = random.nextInt(deck.size);
			deck.removeIndex(a);
		}
	}

}
