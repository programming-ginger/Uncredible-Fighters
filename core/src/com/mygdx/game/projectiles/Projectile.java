package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Constants;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.screen.FightingScreen;
import com.mygdx.game.sound.SoundPlayer;

public abstract class Projectile {
	
	private Sprite sprite;
	private float speed;
	
	public Projectile(Texture texture, Rectangle rectangle, float speed) {
		sprite = new Sprite(texture);
		sprite.setBounds(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
		this.speed = speed;
	}
	
	public Projectile(Texture texture, float x, float y, float height, float speed) {
		this(texture, MenuFactory.makeScaledRectangleForTexture(texture, x, y, height), speed);
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		batch.draw(sprite.getTexture(), 0, 0, 100, 100);
	}
	
	/*
	 * Updates the position of the projectile and applies the effect if needed
	 * @return if Projectile still exists 
	 */
	public boolean update(float delta, UncredibleFighter enemy) {
		Rectangle enemyHitbox = enemy.getRectangle();
		
		if (enemyHitbox.overlaps(sprite.getBoundingRectangle())) {
			applyEffect(enemy);
			return false;
		}
		
		if (sprite.getX() + sprite.getWidth() < 0 || sprite.getX() > Options.getWindowWidth()) {
			return false;
		}
		
		float x = sprite.getX() + Constants.SPEED_FACTOR * delta * speed * Options.getWindowWidth();
		sprite.setX(x);
		return true;
	}

	public abstract void applyEffect(UncredibleFighter enemy);
}
