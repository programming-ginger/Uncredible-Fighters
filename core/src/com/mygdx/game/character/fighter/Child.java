package com.mygdx.game.character.fighter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.Character;

import java.util.Queue;

public class Child extends Character
{
	private static final int DEF_MAX_STONE_COUNT = 3;

	private Queue<Rectangle> stoneList;
	private int maxStoneCount;

	public Child(String name, int maxHP, float speed, Texture texture, Rectangle rectangle)
	{
		this(name, maxHP, speed, DEF_MAX_STONE_COUNT, texture, rectangle);
	}

	public Child(String name, int maxHP, float speed, int maxStoneCount, Texture texture, Rectangle rectangle)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setMaxStoneCount(maxStoneCount);
		setTexture(texture);
		setRectangle(rectangle);
	}

	public void addStone()
	{
		if (getStoneCount() < getMaxStoneCount())
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
		return maxStoneCount;
	}

	public void setMaxStoneCount(int maxStoneCount)
	{
		this.maxStoneCount = maxStoneCount;
	}

}