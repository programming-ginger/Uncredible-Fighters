package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.textures.TextureLibrary;

public class Stone extends Projectile {

	private final static float SPEED = 10;
	private final static int DAMAGE = 5;
	
	public Stone(float x, float y, float height, float directionFactor) {
		super(TextureLibrary.getStone(), x, y, height, directionFactor * SPEED);
	}

	@Override
	public void applyEffect(UncredibleFighter enemy) {
		enemy.reduceHP(DAMAGE);	
	}

}
