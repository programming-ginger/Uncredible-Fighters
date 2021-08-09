package com.mygdx.game.character.fighter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.Character;
import org.w3c.dom.Text;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Maid extends Character
{
	private Rectangle puddle;

	public Maid(String name, int maxHP, float speed, Texture texture)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setTexture(texture);
		//setRectangle(rectangle);
		//add puddle size (?)
	}

	public Rectangle getPuddle()
	{
		throw new NotImplementedException();
	}

	public void setPuddle(Rectangle puddle)
	{
		throw new NotImplementedException();
	}
}