package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Queue;

public class Child extends UncredibleFighter{
	
	private static final int DEF_MAX_STONE_COUNT = 3;

	private Queue<Rectangle> stoneList;
	private int maxStoneCount;
	
	public Child() {
		this("child", 100, 5, DEF_MAX_STONE_COUNT, new Texture("Badlogic.jpg"));
	}

	public Child(String name, int maxHP, float speed, Texture texture)
	{
		this(name, maxHP, speed, DEF_MAX_STONE_COUNT, texture);
	}

	public Child(String name, int maxHP, float speed, int maxStoneCount, Texture texture)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setMaxStoneCount(maxStoneCount);
		setTexture(texture);
		setRectangle(new Rectangle(0,0,50,50));
		//setRectangle(rectangle);
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