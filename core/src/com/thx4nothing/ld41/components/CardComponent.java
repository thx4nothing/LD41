package com.thx4nothing.ld41.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.utils.Array;
import com.thx4nothing.ld41.Game;
import com.thx4nothing.ld41.cards.Card;
import com.thx4nothing.ld41.cards.JumpAttackCard;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;

public class CardComponent implements Component {
	public Array<Card> deck = new Array<>();
	public Array<Card> graveyard = new Array<>();
	public Array<Card> hand = new Array<>();

	public CardComponent() {
		deck.add(new JumpAttackCard());
		deck.add(new JumpAttackCard());
		deck.add(new JumpAttackCard());
		shuffle();
	}

	public void shuffle() {
		deck.shuffle();
	}

	public void draw() {
		for (int i = 0; i < 3; i++) {
			if (deck.size == 0) {
				deck.addAll(graveyard);
				graveyard.clear();
				shuffle();
				//TODO: decrease score
			}
			hand.add(deck.pop());
		}
	}

	public void handToGrave() {
		graveyard.addAll(hand);
		hand.clear();
	}

	public void play(int i) {
		Card card = hand.get(i);
		handToGrave();
		draw();
		for (Entity e : Game.engine.getEntitiesFor(Family.all(CardComponent.class).get())) {
			if (e.getComponent(CardComponent.class).equals(this)) {
				if (e instanceof Player) {
					printHand();
					printDeck();
					System.out.println("Player played: " + card.name);
				} else if (e instanceof Enemy) System.out.println("Enemy played: " + card.name);
			}
		}
	}

	public void printHand() {
		System.out.println("Q: " + hand.get(0).name);
		System.out.println("W: " + hand.get(1).name);
		System.out.println("E: " + hand.get(2).name);
	}

	public void printDeck() {
		System.out.println("Deck: " + deck.size);
		System.out.println("Graveyard: " + graveyard.size);
	}

}
