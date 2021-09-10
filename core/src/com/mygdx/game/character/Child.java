package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.ChildSlingshot;
import com.mygdx.game.moves.GrandpaWalkingStickBlow;
import com.mygdx.game.textures.TextureLibrary;

import java.util.Queue;

public class Child extends UncredibleFighter{
	
	private static final int DEF_MAX_STONE_COUNT = 3;
	private static final float SIZE = 0.25f;

	private Queue<Rectangle> stoneList;
	
	Pixmap mask = new Pixmap(128, 128, Pixmap.Format.Alpha);
	

	public Child()
	{
//		mask.setBlending(Pixmap.Blending.None);
//		mask.setColor(new Color(0f, 0f, 0f, 0f));
//		mask.fillRectangle(0, 0, 32, 32);
//		Pixmap fg = new Pixmap(Gdx.files.internal("Child/ChildFightingSprite.png"));
//		fg.drawPixmap(mask, fg.getWidth(), fg.getHeight());
//		mask.setBlending(Pixmap.Blending.SourceOver);
		setName("Kind");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Child/ChildFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * SIZE);
		setRectangle(rect);
		//setRectangle(rectangle);
		move1 = new ChildSlingshot();
		move2 = new ChildSlingshot();

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

	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getPlayground();
	}

}