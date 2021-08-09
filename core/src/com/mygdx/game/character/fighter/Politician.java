package com.mygdx.game.character.fighter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.Character;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Queue;

public class Politician extends Character {

	private static final int DEF_MAX_PEN_COUNT = 3;

	private Queue<Rectangle> penList;
	private int maxStoneCount;

	public Politician(String name, int maxHP, float speed, Texture texture)
	{
		this(name, maxHP, speed, DEF_MAX_PEN_COUNT, texture);
	}

	public Politician(String name, int maxHP, float speed, int maxStoneCount, Texture texture)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setMaxStoneCount(maxStoneCount);
		setTexture(texture);
		//setRectangle(rectangle);
	}

	public void addPen()
	{
		if (getPenCount() < getMaxPenCount())
			penList.add(new Rectangle());
	}

	public Rectangle getPen()
	{
		return penList.poll();
	}

	public int getPenCount()
	{
		return penList.size();
	}

	public int getMaxPenCount()
	{
		return maxStoneCount;
	}

	public void setMaxStoneCount(int maxStoneCount)
	{
		this.maxStoneCount = maxStoneCount;
	}

}
