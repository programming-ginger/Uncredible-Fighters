package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuLogo{
	
	private Texture logo;
	private Rectangle position;
	
	public MenuLogo(Texture texture, Rectangle rectangle) {
		this.logo = texture;
		this.position = rectangle;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(logo, position.x, position.y, position.width, position.height);	
	}                                                                                                          
	
	public void dispose() {
		logo.dispose();		
	}

}
