package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.MaidPuddle;
import com.mygdx.game.moves.PoliticianPen;
import com.mygdx.game.textures.TextureLibrary;

import java.util.Queue;

public class Politician extends UncredibleFighter {

	private static final int DEF_MAX_PEN_COUNT = 3;
	private static final float SIZE = 0.35f;
	
	private Queue<Rectangle> penList;

	public Politician()
	{
		setName("Politiker");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Politician/PoliticianFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * SIZE);
		setRectangle(rect);
		//setRectangle(rectangle);
		move1 = new PoliticianPen();
		move2 = new PoliticianPen();
	}

	public void addPen()
	{
		if (getPenCount() < DEF_MAX_PEN_COUNT)
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
		return DEF_MAX_PEN_COUNT;
	}
	
	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getElectionCampaign();
	}

	@Override
	protected Texture getKOTexture() {
		return new Texture("Politician/PoliticianKO.png");
	}

}
