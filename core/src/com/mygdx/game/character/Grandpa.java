package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.GrandpaWalkingStickBlow;
import com.mygdx.game.textures.TextureLibrary;

public class Grandpa extends UncredibleFighter {

	private Rectangle rollator;

	public Grandpa()
	{
		setName("Grandpa");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Grandpa/GrandpaFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * 0.2f);
		setRectangle(rect);
		move1 = new GrandpaWalkingStickBlow();
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
	
	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getFrontYard();
	}

}