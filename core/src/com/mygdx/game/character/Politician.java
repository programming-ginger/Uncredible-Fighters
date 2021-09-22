package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.MaidPuddle;
import com.mygdx.game.moves.PoliticianConfusionSpeech;
import com.mygdx.game.moves.PoliticianPen;
import com.mygdx.game.projectiles.Pen;
import com.mygdx.game.textures.TextureLibrary;

import java.util.Queue;

public class Politician extends UncredibleFighter {

	private static final int DEF_MAX_PEN_COUNT = 3;
	private static final float SIZE = 0.35f;
	
	private static final float PEN_THROWING_Y = 0.7f;
	private static final float PEN_SIZE = 0.005f;
	
	private Array<Pen> penList;

	public Politician()
	{
		setName("Politiker");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Politician/PoliticianFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * SIZE);
		setRectangle(rect);
		penList = new Array<>();
		move1 = new PoliticianPen();
		move2 = new PoliticianConfusionSpeech();
	}

	public void addPen()
	{
		if (getPenCount() < DEF_MAX_PEN_COUNT) {
			
			float x = rectangle.getX();
			int directionFactor = -1;;
			
			if (!lookingLeft) {
				x += rectangle.getWidth();
				directionFactor = 1;
			}
			
			float y = rectangle.getY() + rectangle.getHeight() * PEN_THROWING_Y;
			
			penList.add(new Pen(x, y, PEN_SIZE * Options.getWindowHeight(), directionFactor));
		}
	}
	
	@Override
	public void update(float delta, UncredibleFighter enemy) {
		super.update(delta, enemy);
		
		ArrayIterator<Pen> it = penList.iterator();
		
		while (it.hasNext()) {
			Pen pen = it.next();
			
			if (!pen.update(delta, enemy)) {
				it.remove();
			}
		}
	}
	
    @Override
	public void draw(SpriteBatch batch, Texture currentSprite) {
    	super.draw(batch, currentSprite);
    	
    	for (Pen pen : penList) {
    		pen.draw(batch);
    	}
    }

	public int getPenCount()
	{
		return penList.size;
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
	
	@Override
	public Texture getPortrait() {
		return TextureLibrary.getPoliticianPortrait();
	}

}
