package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Teacher extends UncredibleFighter {

	private Rectangle ruler;

	public Teacher() {
		this("teacher", 100, 5, new Texture("Badlogic.jpg"));
	}
	
	public Teacher(String name, int maxHP, float speed, Texture texture)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setTexture(texture);
		setRectangle(new Rectangle(0,0,50,50));
		//setRectangle(rectangle);
	}

	public Rectangle getRuler()
	{
		return null;
		//throw new NotImplementedException();
	}

	public void setRuler(Rectangle ruler)
	{
		//throw new NotImplementedException();
	}

}