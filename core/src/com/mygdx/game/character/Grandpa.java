package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.GrandpaPills;
import com.mygdx.game.moves.GrandpaWalkingStickBlow;
import com.mygdx.game.projectiles.WalkingStick;
import com.mygdx.game.textures.TextureLibrary;

public class Grandpa extends UncredibleFighter {
	
	private static final int DEF_MAX_PEN_COUNT = 3;
	private static final float SIZE = 0.35f;
	
	private static final float WALKINGSTICK_THROWING_Y = 0.7f;
	private static final float WALKINGSTICK_SIZE = 0.01f;
	
	private static final float DAMAGE_REDUCTION = 0.8f;

	private Array<WalkingStick> walkingStickList;
	
	public Grandpa()
	{
		super();
		setName("Grossvater");
		setMaxHP(100);
		setSpeed(4);
		setTextureInitial(new Texture("Grandpa/GrandpaFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(sprite.getTexture(), 0, 0, Options.getWindowHeight() * SIZE);
		setRectangle(rect);
		walkingStickList = new Array<WalkingStick>();
		move1 = new GrandpaWalkingStickBlow();
		move2 = new GrandpaPills();
		//setRectangle(rectangle);
	}
	
	public void addWalkingStick()
	{
		if (getWalkingStickCount() < DEF_MAX_PEN_COUNT) {
			
			float x = sprite.getX();
			int directionFactor = -1;;
			
			if (!looksLeft()) {
				x += sprite.getWidth();
				directionFactor = 1;
			}
			
			float y = sprite.getY() + sprite.getHeight() * WALKINGSTICK_THROWING_Y;
			
			walkingStickList.add(new WalkingStick(x, y, WALKINGSTICK_SIZE * Options.getWindowHeight(), directionFactor));
		}
	}

	
	public int getWalkingStickCount()
	{
		return walkingStickList.size;
	}

	public int getMaxWalkingStickCount()
	{
		return DEF_MAX_PEN_COUNT;
	}
	
	@Override
	public void update(float delta, UncredibleFighter enemy) {
		super.update(delta, enemy);
		
		ArrayIterator<WalkingStick> it = walkingStickList.iterator();
		
		while (it.hasNext()) {
			WalkingStick stick = it.next();
			
			if (!stick.update(delta, enemy)) {
				it.remove();
			}
		}
	}
	
    @Override
	public void draw(SpriteBatch batch) {
    	super.draw(batch);
    	
    	for (WalkingStick stick : walkingStickList) {
    		stick.draw(batch);
    	}
    }
	
	public Rectangle getRollator()
	{
		return null;
		//throw new NotImplementedException();
	}

	public void setRollator(Rectangle rollator)
	{
		//throw new NotImplementedException();
	}
	
	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getFrontYard();
	}

	@Override
	protected Texture getKOTexture() {
		return new Texture("Grandpa/GrandpaKO.png");
	}
	
	@Override
	public Texture getPortrait() {
		return TextureLibrary.getGrandpaPortrait();
	}
	
    @Override
    public void reduceHP(int damage) {
    	super.reduceHP((int)(damage * DAMAGE_REDUCTION));
    }

}