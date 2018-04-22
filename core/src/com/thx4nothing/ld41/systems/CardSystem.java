package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.thx4nothing.ld41.cards.LoseCard;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.util.Mappers;

import java.util.Random;

public class CardSystem extends IntervalIteratingSystem {

	public int tick = 0;

	private Random random = new Random();

	public CardSystem(float interval) {
		super(Family.all(CardComponent.class).get(), interval);
	}

	@Override protected void processEntity(Entity entity) {
		CardComponent card = Mappers.card.get(entity);
		if (card.hand.size == 0) newHand(card);

		if (card.activeCard != null) {
			card.activeCard.doEffect(entity);
			if (entity instanceof Player) System.out.println("Player played: " + card.activeCard.name);
			if (entity instanceof Enemy) System.out.println("Enemy played: " + card.activeCard.name);
			card.activeCard = null;
			newHand(card);
			if (entity instanceof Player) printHand(card);
			if (entity instanceof Player) printDeck(card);
		}
		if (entity instanceof Enemy && card.activeCard == null) {
			if (card.playedCard) card.playedCard = false;
			else card.play(random.nextInt(card.hand.size));
		}
	}

	private void newHand(CardComponent card) {
		if (card.deck.size == 0 && card.graveyard.size <= 2) {
			card.graveyard.add(new LoseCard());
		}
		draw(card);
	}

	private void shuffle(CardComponent card) {
		card.deck.shuffle();
	}

	private void draw(CardComponent card) {
		int amount = 3;
		if (card.burn) amount = 2;
		for (int i = 0; i < amount; i++) {
			if (card.deck.size == 0) {
				if (card.graveyard.size > 0) {
					card.deck.addAll(card.graveyard);
					card.graveyard.clear();
					shuffle(card);
				} else break;
			}
			card.hand.add(card.deck.pop());
		}
	}

	private void printHand(CardComponent card) {
		for (int i = 0; i < card.hand.size; i++) {
			if (i == 0) System.out.println("Q: " + card.hand.get(i).name);
			if (i == 1) System.out.println("W: " + card.hand.get(i).name);
			if (i == 2) System.out.println("E: " + card.hand.get(i).name);
		}
	}

	private void printDeck(CardComponent card) {
		System.out.println("Deck: " + card.deck.size);
		System.out.println("Graveyard: " + card.graveyard.size);
	}

}
