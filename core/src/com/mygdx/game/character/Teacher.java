package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.moves.MaidPuddle;
import com.mygdx.game.moves.TeacherRuler;
import com.mygdx.game.textures.TextureLibrary;

public class Teacher extends UncredibleFighter {

	private Rectangle ruler;
	
	public Teacher()
	{
		setName("Teacher");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Teacher/TeacherFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * 0.2f);
		setRectangle(rect);
		//setRectangle(rectangle);
		move1 = new TeacherRuler();
		move2 = new TeacherRuler();
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

}