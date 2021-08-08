package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PassiveTexture{
	
	private Texture logo;
	private Rectangle position;
	
	public PassiveTexture(Texture texture, Rectangle rectangle) {
		this.logo = texture;
		this.position = rectangle;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(logo, position.x, position.y, position.width, position.height);	
	}                                                                                                          
	
	public void dispose() {
		logo.dispose();		
	}

	public boolean contains(float x, float y) {
		return position.contains(x, y);
	}

}
