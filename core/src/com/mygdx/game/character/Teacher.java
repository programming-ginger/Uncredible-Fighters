package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.TeacherChalkCloud;
import com.mygdx.game.moves.TeacherRuler;
import com.mygdx.game.projectiles.Ruler;
import com.mygdx.game.textures.TextureLibrary;

public class Teacher extends UncredibleFighter {

	private static final int DEF_MAX_PEN_COUNT = 3;
	private static final float SIZE = 0.35f;
	private static final float RULER_THROWING_Y = 0.7f;
	private static final float RULER_SIZE = 0.02f;
	private Array<Ruler> rulerStickList;
	
	private boolean passiveAbilityWasApplied;
	private static final float BOREDOM_SPEED_FACTOR = 0.7f;
	
	public Teacher()
	{
		super();
		setName("Lehrer");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Teacher/TeacherFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(sprite.getTexture(), 0, 0, Options.getWindowHeight() * SIZE);
		setRectangle(rect);
		rulerStickList = new Array<>();
		move1 = new TeacherRuler();
		//move2 = new TeacherChalkCloud();
		
		passiveAbilityWasApplied = false;
	}
	
	
	public void addRuler()
	{
		if (getRulerCount() < DEF_MAX_PEN_COUNT) {
			
			float x = sprite.getX();
			int directionFactor = -1;;
			
			if (!looksLeft()) {
				x += sprite.getWidth();
				directionFactor = 1;
			}
			
			float y = sprite.getY() + sprite.getHeight() * RULER_THROWING_Y;
			
			rulerStickList.add(new Ruler(x, y, RULER_SIZE * Options.getWindowHeight(), directionFactor));
		}
	}

	
	public int getRulerCount()
	{
		return rulerStickList.size;
	}

	public int getMaxRulerCount()
	{
		return DEF_MAX_PEN_COUNT;
	}
	
	@Override
	public void update(float delta, UncredibleFighter enemy) {
		super.update(delta, enemy);
		
		if (!passiveAbilityWasApplied) {
			boreEnemy(enemy);
			passiveAbilityWasApplied = true;
		}
		
		ArrayIterator<Ruler> it = rulerStickList.iterator();
		
		while (it.hasNext()) {
			Ruler ruler = it.next();
			
			if (!ruler.update(delta, enemy)) {
				it.remove();
			}
		}
	}
	
    @Override
	public void draw(SpriteBatch batch) {
    	super.draw(batch);
    	
    	for (Ruler ruler : rulerStickList) {
    		ruler.draw(batch);
    	}
    }
	public Rectangle getRuler()
	{
		return null;
		//throw new NotImplementedException();
	}

	public void setRuler(Rectangle ruler)
	{
		//throw new NotImplementedException();
	}
	
	@Override
	public Texture getSpecificBackground() {
		return TextureLibrary.getClassroom();
	}

	@Override
	protected Texture getKOTexture() {
		return new Texture("Teacher/TeacherKO.png");
	}
	
	@Override
	public Texture getPortrait() {
		return TextureLibrary.getTeacherPortrait();
	}
	
	private void boreEnemy(UncredibleFighter enemy) {
		enemy.bore(BOREDOM_SPEED_FACTOR);
	}

}