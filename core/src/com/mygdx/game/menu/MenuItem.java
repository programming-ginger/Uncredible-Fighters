package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MenuItem {
	
	private MenuItem itemAbove;
	private MenuItem itemBelow;
	private MenuItem itemLeft;
	private MenuItem itemRight;

	public void update(SpriteBatch batch, Vector2 mousePosition) {}

	public void draw(SpriteBatch batch, boolean isSelected) {}

	public void dispose() {}

	public boolean contains(float x, float y) {
		return false;
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
	
	

}
