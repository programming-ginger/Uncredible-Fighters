package com.mygdx.game.character.fighter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.Character;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Grandpa extends Character {

	private Rectangle rollator;

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
		throw new NotImplementedException();
	}

	public void setRollator(Rectangle rollator)
	{
		throw new NotImplementedException();
	}

}