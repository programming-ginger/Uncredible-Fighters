package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.sound.SoundPlayer;
import com.mygdx.game.textures.TextureLibrary;

public class Pen extends Projectile {
	
	private final static float SPEED = 10;
	private final static int DAMAGE = 5;

	public Pen(float x, float y, float height, int directionFactor) {		
		super(TextureLibrary.getPen(), x, y, height, directionFactor * SPEED);
	}

	@Override
	public void applyEffect(UncredibleFighter enemy) {
		enemy.reduceHP(DAMAGE);
		SoundPlayer.playHitSound();
	}

}
