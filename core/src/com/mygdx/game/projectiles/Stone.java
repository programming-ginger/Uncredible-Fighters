package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;

public class Stone extends Projectile {

	private final static float SPEED = 10;
	private final static int DAMAGE = 5;
	
	public Stone(Texture texture, Rectangle rectangle, float speed) {
		super(texture, rectangle, speed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyEffect(UncredibleFighter enemy) {
		// TODO Auto-generated method stub
		
	}

}
