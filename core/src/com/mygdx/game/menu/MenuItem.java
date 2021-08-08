package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Options;

public interface MenuItem {

	public void update(SpriteBatch batch, Vector2 mousePosition);

	public void draw(SpriteBatch batch);

	public void select(SpriteBatch batch);

	public void dispose();

	public boolean contains(float x, float y);

}
