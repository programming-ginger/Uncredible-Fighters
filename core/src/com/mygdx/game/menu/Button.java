package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.data.Options;
import com.mygdx.game.sound.SoundPlayer;
import com.mygdx.game.textures.TextureLibrary;

public class Button extends MenuItem {

	private final static float ARROW_OFFSET = 0.05f;

	private Texture texture;

	private Texture selectionArrow;

	ButtonAction action;

	public Button(Texture texture, Rectangle position, ButtonAction action) {
		this.position = position;
		this.texture = texture;
		this.action = action;
		this.selectionArrow = TextureLibrary.getSelectionArrow();
	}

	public Button(Texture texture, Rectangle position) {
		this(texture, position, null);
	}

	public boolean contains(float x, float y) {
		return position.contains(x, y);
	}

	public void performAction() {
		if (action != null) {
			SoundPlayer.playActionSound();
			action.action();
		}
	}

	public void draw(SpriteBatch batch, boolean isSelected) {
		batch.draw(texture, position.x, position.y, position.width, position.height);
		if (isSelected) {
			float width = selectionArrow.getWidth() / (selectionArrow.getHeight() + 0f) * position.getHeight();
			batch.draw(selectionArrow, position.x - width - ARROW_OFFSET * Options.getWindowHeight(), position.y, width,
					position.height);
		}
	}

	public void dispose() {
		texture.dispose();
		selectionArrow.dispose();
	}

	@Override
	public void update(SpriteBatch batch, Vector2 mousePosition) {
		if ((Gdx.input.isTouched() && this.contains(mousePosition) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
				|| Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			performAction();
		}
	}
}
