package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.ChildSlingshot;
import com.mygdx.game.moves.MaidDirtyMop;
import com.mygdx.game.moves.MaidPuddle;
import com.mygdx.game.projectiles.Puddle;
import com.mygdx.game.projectiles.Stone;
import com.mygdx.game.textures.TextureLibrary;

import org.w3c.dom.Text;

public class Maid extends UncredibleFighter
{
	private Puddle puddle;
	private static final float SIZE = 0.35f;
	
	private static final float PUDDLE_SIZE = 0.1f;
	
	public Maid(){
		super();
		setName("Putzfrau");
		setMaxHP(120);
		setSpeed(5);
		setTextureInitial(new Texture("Maid/MaidFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(sprite.getTexture(), 0, 0, Options.getWindowHeight() * SIZE);
		setRectangle(rect);
		//add puddle size (?)
		move1 = new MaidPuddle();
		move2 = new MaidDirtyMop();
	}

	public Rectangle getPuddle(){
		return null;
	}

	public void makePuddle(){
		float x = sprite.getX();
		
		if (!looksLeft()) {
			x += sprite.getWidth();
		}
		puddle = new Puddle(x, sprite.getY(), PUDDLE_SIZE * Options.getWindowHeight(), looksLeft());
	}
	
	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getHallway();
	}

	@Override
	protected Texture getKOTexture() {
		return new Texture("Maid/MaidKO.png");
	}
	
	@Override
	public Texture getPortrait() {
		return TextureLibrary.getMaidPortrait();
	}
	
	@Override
	public void update(float delta, UncredibleFighter enemy) {
		super.update(delta, enemy);
			
		if (puddle != null && !puddle.update(delta, enemy)) {
			puddle = null;
		}
	}
	
    @Override
	public void draw(SpriteBatch batch) {
    	super.draw(batch);
    	
    	if (puddle != null) {
    		puddle.draw(batch);
    	}
    }
}