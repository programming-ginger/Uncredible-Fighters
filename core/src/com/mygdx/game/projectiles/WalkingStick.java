package com.mygdx.game.projectiles;

import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.textures.TextureLibrary;

public class WalkingStick extends Projectile{
	
	public WalkingStick(float x, float y, float height, int directionFactor) {
		super(TextureLibrary.getWalkingStick(), x, y, height, directionFactor * SPEED);
		// TODO Auto-generated constructor stub
	}


	private final static float SPEED = 10;
	private final static int DAMAGE = 5;


	@Override
	public void applyEffect(UncredibleFighter enemy) {
		enemy.reduceHP(DAMAGE);
		
	}

}
