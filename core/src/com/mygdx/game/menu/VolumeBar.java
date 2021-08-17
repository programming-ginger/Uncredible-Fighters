package com.mygdx.game.menu;

import java.util.function.IntConsumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.my.gdx.game.textures.TextureLibrary;
import com.mygdx.game.data.Options;

public class VolumeBar extends MenuItem {

	private static final float LABEL_BAR_GAP = 0.025f;

	private Texture controller;
	private Texture bar;
	private Texture barSelected;

	private PassiveTexture label;

	private Rectangle barPosition;
	private Rectangle controllerPosition;

	private int value;

	private IntConsumer action;

	public VolumeBar(Texture labelTexture, float xCenter, float yCenter, float height, int value, IntConsumer action) {
		this.controller = TextureLibrary.getVolumeController();
		this.bar = TextureLibrary.getVolumeBar();
		this.barSelected = TextureLibrary.getSelectedVolumeBar();
		this.label = MenuFactory.makePassiveTextureToLeft(labelTexture, xCenter - Options.getWindowWidth() * LABEL_BAR_GAP,
				yCenter, height);
		this.action = action;

		this.value = value;
		action.accept(value);

		this.barPosition = MenuFactory.makeScaledRectangleForTextureToRight(bar, xCenter, yCenter, height);

		float xController = this.barPosition.getX()
				+ this.barPosition.getWidth() * this.value / (Options.MAX_VOLUME + 0f);
		this.controllerPosition = MenuFactory.makeScaledRectangleForTexture(controller, xCenter, yCenter, 2 * height);
		adjustControllerPosition();
		
		this.position = new Rectangle(this.label.getX(),barPosition.getY(),label.getWidth() + LABEL_BAR_GAP*Options.getWindowWidth() + barPosition.getWidth(), barPosition.getHeight());
	}

	@Override
	public void update(SpriteBatch batch, Vector2 mousePosition) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && value > 0) {
			this.value--;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && value < Options.MAX_VOLUME) {
			this.value++;
		}

		if (Gdx.input.isTouched()) {

			if (barPosition.getX() <= mousePosition.x
					&& mousePosition.x <= barPosition.getX() + barPosition.getWidth()) {

				float portion = (mousePosition.x - barPosition.getX()) / barPosition.getWidth();
				
				this.value = Math.round(Options.MAX_VOLUME * portion);
			}

		}

		adjustControllerPosition();
		action.accept(value);

	}

	@Override
	public void draw(SpriteBatch batch, boolean isSelected) {
		
		Texture barTexture;
		if (!isSelected) {
			barTexture = bar;
		}
		else {
			barTexture = barSelected;
		}
		
		label.draw(batch);		
		batch.draw(barTexture, barPosition.getX(), barPosition.getY(), barPosition.getWidth(), barPosition.getHeight());		
		batch.draw(controller, controllerPosition.getX(), controllerPosition.getY(), controllerPosition.getWidth(),
				controllerPosition.getHeight());

	}

	@Override
	public void dispose() {
		label.dispose();
		controller.dispose();
		bar.dispose();

	}

	@Override
	public boolean contains(float x, float y) {
		return this.position.contains(x, y) || controllerPosition.contains(x, y);
	}

	private void adjustControllerPosition() {
		this.controllerPosition.x = barPosition.getX()
				+ barPosition.getWidth() * (this.value / (Options.MAX_VOLUME + 0f)) - controllerPosition.getWidth() / 2;
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		
	}
}
