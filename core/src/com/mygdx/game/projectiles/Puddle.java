package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.screen.FightingScreen;
import com.mygdx.game.textures.TextureLibrary;

public class Puddle {

	private Sprite sprite;
	private static final int DAMAGE = 10;
	
	public Puddle(float x, float y, float height, boolean charIsLookingLeft) {
		
		sprite = new Sprite(TextureLibrary.getPuddle());
		
		Rectangle rect;
		if (charIsLookingLeft) {
			rect = MenuFactory.makeScaledRectangleForTextureToLeft(sprite.getTexture(), x, y, height);
		}
		else {
			rect = MenuFactory.makeScaledRectangleForTextureToRight(sprite.getTexture(), x, y, height);
		}
		
		sprite.setBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
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

		return true;
	}

	public void applyEffect(UncredibleFighter enemy) {
		enemy.reduceHP(DAMAGE);
	}
}
