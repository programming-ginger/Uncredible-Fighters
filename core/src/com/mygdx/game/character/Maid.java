package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.ChildSlingshot;
import com.mygdx.game.moves.MaidPuddle;
import com.mygdx.game.textures.TextureLibrary;

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
		move1 = new MaidPuddle();
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
	
	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getHallway();
	}
}