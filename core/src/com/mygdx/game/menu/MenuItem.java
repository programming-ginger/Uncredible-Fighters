package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.textures.TextureLibrary;

public abstract class MenuItem {
	
	private MenuItem itemAbove;
	private MenuItem itemBelow;
	private MenuItem itemLeft;
	private MenuItem itemRight;
	
	protected Rectangle position;
	private Texture player1SelectionFrame;
	private Texture player2SelectionFrame;
	
	private final static float OFFSET = 0.05f;
	
	public MenuItem() {
		player1SelectionFrame = TextureLibrary.getPlayer1SelectionFrame();
		player2SelectionFrame = TextureLibrary.getPlayer2SelectionFrame();
	}

	public abstract void update(SpriteBatch batch, Vector2 mousePosition);

	public abstract void draw(SpriteBatch batch, boolean isSelected);

	public abstract void dispose();
	
	public abstract void performAction();

	public boolean contains(float x, float y) {
		return false;
	}
	
	public boolean contains(Vector2 vector) {
		return position.contains(vector);
	}

	public MenuItem getItemAbove() {
		return itemAbove;
	}

	public void setItemAbove(MenuItem itemAbove) {
		this.itemAbove = itemAbove;
	}

	public MenuItem getItemBelow() {
		return itemBelow;
	}

	public void setItemBelow(MenuItem itemBelow) {
		this.itemBelow = itemBelow;
	}

	public MenuItem getItemLeft() {
		return itemLeft;
	}

	public void setItemLeft(MenuItem itemLeft) {
		this.itemLeft = itemLeft;
	}

	public MenuItem getItemRight() {
		return itemRight;
	}

	public void setItemRight(MenuItem itemRight) {
		this.itemRight = itemRight;
	}

	public void draw(SpriteBatch batch, boolean selectedByPlayer1, boolean selectedByPlayer2) {
				
		if (selectedByPlayer1) {
			batch.draw(player1SelectionFrame, position.getX() - position.getWidth() * OFFSET, position.getY() - position.getHeight() * OFFSET, position.getWidth(), position.getHeight());	
		}
		if (selectedByPlayer2) {
			batch.draw(player2SelectionFrame, position.getX() + position.getWidth() * OFFSET, position.getY() + position.getHeight() * OFFSET, position.getWidth(), position.getHeight());	
		}
		draw(batch, false);
	}
	
	

}
