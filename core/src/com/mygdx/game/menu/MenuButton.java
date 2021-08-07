package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Options;

public class MenuButton implements MenuItem{

	private final static float ARROW_OFFSET = 0.05f;

	private Rectangle position;
	private Texture texture;

	private Texture selectionArrow;
	
	ButtonAction action;
	
	public MenuButton (Texture texture, Rectangle position, ButtonAction action, Texture selectionArrow) {
		this.position = position;
		this.texture = texture;
		this.action = action;
		this.selectionArrow = selectionArrow;
	}

	public MenuButton (Texture texture, Rectangle position, Texture selectionArrow) {
		this(texture, position, null, selectionArrow);
	}

	public boolean contains(float x, float y) {
		return position.contains(x, y);
	}

	public void performAction() {
		if (action != null) {
			action.action();
		}
	}

	public void draw(SpriteBatch batch){
		batch.draw(texture, position.x, position.y, position.width, position.height);
	}

	public void select(SpriteBatch batch) {
		float width = selectionArrow.getWidth() / (selectionArrow.getHeight() + 0f) * position.getHeight();
		batch.draw(selectionArrow, position.x - width - ARROW_OFFSET * Options.getWindowHeight(), position.y, width, position.height);
	}

	public void dispose() {
		texture.dispose();
		selectionArrow.dispose();
	}

	@Override
	public void update(SpriteBatch batch) {
		if((Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			performAction();
		}		
	}
}
