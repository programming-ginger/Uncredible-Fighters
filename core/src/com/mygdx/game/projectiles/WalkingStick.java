package com.mygdx.game.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.sound.SoundPlayer;
import com.mygdx.game.textures.TextureLibrary;

public class WalkingStick extends Projectile{
	
	private final static float SPEED = 10;
	private final static int DAMAGE = 5;
	
	public WalkingStick(float x, float y, float height, int directionFactor) {
		super(TextureLibrary.getWalkingStick(), x, y, height, directionFactor * SPEED);
	}


	@Override
	public void applyEffect(UncredibleFighter enemy) {
		enemy.reduceHP(DAMAGE);		
	}

}
