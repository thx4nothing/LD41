package com.thx4nothing.ld41.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.thx4nothing.ld41.cards.DefendCard;
import com.thx4nothing.ld41.cards.FireballCard;
import com.thx4nothing.ld41.cards.JumpAttackCard;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.components.ScoreComponent;
import com.thx4nothing.ld41.components.TextureComponent;
import com.thx4nothing.ld41.util.Assets;

public class Player extends Entity {

	public Player() {
		PositionComponent pos = new PositionComponent();
		TextureComponent tex = new TextureComponent();
		CardComponent card = new CardComponent();
		ScoreComponent score = new ScoreComponent();

		pos.setPos(new Vector2(1, 1));
		tex.region = Assets.PLAYER;

		card.addCard(new JumpAttackCard());
		card.addCard(new JumpAttackCard());
		card.addCard(new JumpAttackCard());
		card.addCard(new FireballCard());
		card.addCard(new FireballCard());
		card.addCard(new FireballCard());
		card.addCard(new DefendCard());
		card.addCard(new DefendCard());
		card.addCard(new DefendCard());

		card.newOverHand();

		add(pos);
		add(tex);
		add(card);
		add(score);
	}
}
