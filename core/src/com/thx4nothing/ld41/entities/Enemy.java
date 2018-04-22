package com.thx4nothing.ld41.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.thx4nothing.ld41.cards.JumpAttackCard;
import com.thx4nothing.ld41.components.CardComponent;
import com.thx4nothing.ld41.components.PositionComponent;
import com.thx4nothing.ld41.components.TextureComponent;
import com.thx4nothing.ld41.util.Assets;

public class Enemy extends Entity {

	public Enemy() {
		PositionComponent pos = new PositionComponent();
		TextureComponent tex = new TextureComponent();
		CardComponent card = new CardComponent();

		card.addCard(new JumpAttackCard());
		card.addCard(new JumpAttackCard());
		card.addCard(new JumpAttackCard());

		card.newHand();

		pos.setPos(new Vector2(5, 1));
		tex.region = Assets.ENEMY;

		add(pos);
		add(tex);
		add(card);
	}
}
