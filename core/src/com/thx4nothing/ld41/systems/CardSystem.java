package com.thx4nothing.ld41.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.thx4nothing.ld41.cards.LoseCard;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.entities.Enemy;
import com.thx4nothing.ld41.entities.Player;
import com.thx4nothing.ld41.util.Mappers;

public class CardSystem extends IteratingSystem {

	public CardSystem() {
		super(Family.all(CardComponent.class).get());
	}

	@Override protected void processEntity(Entity entity, float deltaTime) {
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
	}

	private void newHand(CardComponent card) {
		handToGrave(card);
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
				} else card.hand.add(new LoseCard());
			}
			card.hand.add(card.deck.pop());
		}
	}

	private void handToGrave(CardComponent card) {
		card.graveyard.addAll(card.hand);
		card.hand.clear();
	}

	private void printHand(CardComponent card) {
		for (int i = 0; i < card.hand.size; i++) {
			if (i == 0) System.out.println("Q: " + card.hand.get(i).name);
			if (i == 1) System.out.println("W: " + card.hand.get(i).name);
			if (i == 2) {
				if (!card.burn) System.out.println("E: " + card.hand.get(i).name);
				else System.out.println("E: Place is burned");
			}
		}
	}

	private void printDeck(CardComponent card) {
		System.out.println("Deck: " + card.deck.size);
		System.out.println("Graveyard: " + card.graveyard.size);
	}

}
