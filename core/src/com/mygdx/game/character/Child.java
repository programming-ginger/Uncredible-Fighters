package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;

import java.util.Queue;

public class Child extends UncredibleFighter{
	
	private static final int DEF_MAX_STONE_COUNT = 3;

	private Queue<Rectangle> stoneList;

	public Child()
	{
		setName("child");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Child/ChildFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * 0.2f);
		setRectangle(rect);
		//setRectangle(rectangle);
	}

	public void addStone()
	{
		if (getStoneCount() < DEF_MAX_STONE_COUNT)
			stoneList.add(new Rectangle());
	}

	public Rectangle getStone()
	{
		return stoneList.poll();
	}

	public int getStoneCount()
	{
		return stoneList.size();
	}

	public int getMaxStoneCount()
	{
		return DEF_MAX_STONE_COUNT;
	}

}