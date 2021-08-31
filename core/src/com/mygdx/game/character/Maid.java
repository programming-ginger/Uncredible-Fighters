package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import org.w3c.dom.Text;

public class Maid extends UncredibleFighter
{
	private Rectangle puddle;

	public Maid()
	{
		setName("Maid");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Maid/MaidFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * 0.2f);
		setRectangle(rect);
		//add puddle size (?)
	}

	public Rectangle getPuddle()
	{
		return null;
		//throw new NotImplementedException();
	}

	public void setPuddle(Rectangle puddle)
	{
		//throw new NotImplementedException();
	}
}