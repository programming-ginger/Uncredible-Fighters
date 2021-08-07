package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Options;

public interface MenuItem {
	
	public void update(SpriteBatch batch);
	
	public void draw(SpriteBatch batch);

	public void select(SpriteBatch batch);

	public void dispose();

	public boolean contains(float x, float y);

}
