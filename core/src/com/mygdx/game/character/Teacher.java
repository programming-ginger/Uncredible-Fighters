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
	private static final float SIZE = 0.35f;
	
	public Teacher()
	{
		setName("Lehrer");
		setMaxHP(100);
		setSpeed(5);
		setTexture(new Texture("Teacher/TeacherFightingSprite.png"));
		Rectangle rect = MenuFactory.makeScaledRectangleForTexture(texture, 0, 0, Options.getWindowHeight() * SIZE);
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

	@Override
	protected Texture getKOTexture() {
		return new Texture("Teacher/TeacherKO.png");
	}
	
	@Override
	public Texture getPortrait() {
		return TextureLibrary.getTeacherPortrait();
	}

}