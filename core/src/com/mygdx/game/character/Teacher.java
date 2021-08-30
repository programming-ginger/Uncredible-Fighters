package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;

public class Teacher extends UncredibleFighter {

	private Rectangle ruler;
	
	public Teacher()
	{
		setName("teacher");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("TeacherFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * 0.2f);
		setRectangle(rect);
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