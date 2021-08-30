package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;

public class Grandpa extends UncredibleFighter {

	private Rectangle rollator;

	public Grandpa()
	{
		setName("grandpa");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("GrandpaFightingSprite.jpg"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * 0.2f);
		setRectangle(rect);
		//setRectangle(rectangle);
	}

	public Rectangle getRollator()
	{
		return null;
		//throw new NotImplementedException();
	}

	public void setRollator(Rectangle rollator)
	{
		//throw new NotImplementedException();
	}

}