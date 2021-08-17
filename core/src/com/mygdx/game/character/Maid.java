package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.Text;

public class Maid extends UncredibleFighter
{
	private Rectangle puddle;
	
	public Maid() {
		this("maid", 100, 5, new Texture("Badlogic.jpg"));
	}

	public Maid(String name, int maxHP, float speed, Texture texture)
	{
		setName(name);
		setMaxHP(maxHP);
		setCurrentHP(maxHP);
		setSpeed(speed);
		setTexture(texture);
		setRectangle(new Rectangle(0,0,50,50));
		//add puddle size (?)
	}

	public Rectangle getPuddle()
	{
		return null;
		//throw new NotImplementedException();
	}

	public void setPuddle(Rectangle puddle)
	{
		//throw new NotImplementedException();
	}
}