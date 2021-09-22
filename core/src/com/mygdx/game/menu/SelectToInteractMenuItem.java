package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sound.SoundPlayer;

public abstract class SelectToInteractMenuItem extends MenuItem {
	
	protected boolean isSelected;
	
	public void draw(SpriteBatch batch, boolean isSelected) {
		this.isSelected = isSelected & this.isSelected;
	}
	
	@Override
	public void update(SpriteBatch batch, Vector2 mousePosition, boolean isClicked, boolean isJustClicked) {
		if ((Gdx.input.isKeyJustPressed(Input.Keys.ENTER)|| Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			SoundPlayer.playActionSound();
			isSelected = !isSelected;
			if (!isSelected) {
				performAction();
			}
		}
	}

	public MenuItem getItemAbove() {
		if (!isSelected) {
			return super.getItemAbove();
		}
		else return this;
	}

	public MenuItem getItemBelow() {
		if (!isSelected) {
			return super.getItemBelow();
		}
		else return this;
	}

	public MenuItem getItemLeft() {
		if (!isSelected) {
			return super.getItemLeft();
		}
		else return this;
	}

	public MenuItem getItemRight() {
		if (!isSelected) {
			return super.getItemRight();
		}
		else return this;
	}

}
