package com.mygdx.game.character.fighter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.Character;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Teacher extends Character {

	private Rectangle ruler;

	public Teacher(String name, int maxHP, float speed, Texture texture)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setTexture(texture);
		//setRectangle(rectangle);
	}

	public Rectangle getRuler()
	{
		throw new NotImplementedException();
	}

	public void setRuler(Rectangle ruler)
	{
		throw new NotImplementedException();
	}

}