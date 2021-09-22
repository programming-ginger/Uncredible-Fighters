package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.ChildHeadNut;
import com.mygdx.game.moves.ChildSlingshot;
import com.mygdx.game.projectiles.Stone;
import com.mygdx.game.textures.TextureLibrary;

public class Child extends UncredibleFighter{
	
	private static final int DEF_MAX_STONE_COUNT = 3;
	private static final float SIZE = 0.25f;
	
	private static final float STONE_Y = 0.8f;
	private static final float STONE_SIZE = 0.03f;

	private Array<Stone> stoneList;
	
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
		move2 = new ChildHeadNut();
		stoneList = new Array<>();
	}

	public void addStone()
	{
		if (getStoneCount() < DEF_MAX_STONE_COUNT) {
			
			float x = rectangle.getX();
			int directionFactor = -1;;
			
			if (!lookingLeft) {
				x += rectangle.getWidth();
				directionFactor = 1;
			}
		
			float y = rectangle.getY() + rectangle.getHeight() * STONE_Y;
			stoneList.add(new Stone(x, y, Options.getWindowHeight() * STONE_SIZE, directionFactor));
		}
	}

//	public Rectangle getStone()
//	{
//		return stoneList.poll();
//	}

	public int getStoneCount()
	{
		return stoneList.size;
	}

	public int getMaxStoneCount()
	{
		return DEF_MAX_STONE_COUNT;
	}

	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getPlayground();
	}

	@Override
	protected Texture getKOTexture() {
		return new Texture("Child/ChildKO.png");
	}

	@Override
	public Texture getPortrait() {
		return TextureLibrary.getChildPortrait();
	}
	
	@Override
	public void update(float delta, UncredibleFighter enemy) {
		super.update(delta, enemy);
		
		ArrayIterator<Stone> it = stoneList.iterator();
		
		while (it.hasNext()) {
			Stone stone = it.next();
			
			if (!stone.update(delta, enemy)) {
				it.remove();
			}
		}
	}
	
    @Override
	public void draw(SpriteBatch batch, Texture currentSprite) {
    	super.draw(batch, currentSprite);
    	
    	for (Stone stone : stoneList) {
    		stone.draw(batch);
    	}
    }

}