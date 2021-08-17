package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Grandpa extends UncredibleFighter {

	private Rectangle rollator;
	
	public Grandpa() {
		this("grandpa", 100, 5, new Texture("Badlogic.jpg"));
	}

	public Grandpa(String name, int maxHP, float speed, Texture texture)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setTexture(texture);
		setRectangle(new Rectangle(0,0,50,50));
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