package com.mygdx.game.menu;

import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.data.Options;
import com.mygdx.game.sound.SoundPlayer;
import com.mygdx.game.textures.TextureLibrary;

public class ArrowButton<T> extends MenuItem{

	private final static float ARROW_WIDTH = 0.05f;
	private final static float ARROW_OFFSET = 0.055f;

	private Array<ArrowButtonOption<T>> values;
	private int currentOption;

	private Texture buttonBackground;
	private Texture buttonArrow;
	
	private Rectangle leftArrowHitBox;
	private Rectangle rightArrowHitBox;
	private Rectangle buttonPosition;

	Consumer<T> action;

	public ArrowButton(Rectangle position, Consumer<T> action) {
		currentOption = 0;
		
		buttonPosition = position;
		leftArrowHitBox = new Rectangle(position.x - ARROW_OFFSET*Options.getWindowHeight(), position.y, Options.getWindowHeight() * ARROW_WIDTH, position.getHeight());
		rightArrowHitBox = new Rectangle(position.x + position.getWidth() + Options.getWindowHeight() * ARROW_OFFSET, position.y, Options.getWindowHeight() * ARROW_WIDTH, position.getHeight());
		
		this.position = new Rectangle(leftArrowHitBox.getX(), leftArrowHitBox.getY(), rightArrowHitBox.getX() + rightArrowHitBox.getWidth() - leftArrowHitBox.getX(), leftArrowHitBox.getHeight());
		
		this.buttonBackground = TextureLibrary.getButtonBackground();
		this.action = action;
		this.buttonArrow = TextureLibrary.geButtonArrow();
		this.values = new Array<>();
	}

	public ArrowButton(Rectangle position) {
		this(position, null);
	}

	public void performAction() {
		if (action != null) {
			SoundPlayer.playActionSound();
			action.accept(values.get(currentOption).getValue());
		}
	}
	
	public void addOption(ArrowButtonOption option) {
		values.add(option);
	}

	public void draw(SpriteBatch batch, boolean isSelected) {
		batch.draw(buttonBackground, buttonPosition.x, buttonPosition.y, buttonPosition.width, buttonPosition.height);
		
		batch.draw(buttonArrow, leftArrowHitBox.x, leftArrowHitBox.y, leftArrowHitBox.getWidth(), leftArrowHitBox.getHeight(), 0, 0, buttonArrow.getWidth(), buttonArrow.getHeight(), true, false);
		batch.draw(buttonArrow, rightArrowHitBox.x, rightArrowHitBox.y, rightArrowHitBox.getWidth(), rightArrowHitBox.getHeight(), 0, 0, buttonArrow.getWidth(), buttonArrow.getHeight(), false, false);
		
		if (values.notEmpty()) {
			batch.draw(values.get(currentOption).getOptionText(), buttonPosition.x, buttonPosition.y, buttonPosition.width, buttonPosition.height);
		}
//		if (isSelected) {
//			float width = selectionArrow.getWidth() / (selectionArrow.getHeight() + 0f) * position.getHeight();
//			batch.draw(selectionArrow, position.x - width - ARROW_OFFSET * Options.getWindowHeight(), position.y, width,
//					position.height);
//		}
	}


	@Override
	public void update(SpriteBatch batch, Vector2 mousePosition, boolean isClicked) {
		if ((isClicked && this.contains(mousePosition) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
				|| Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			performAction();
		}
		
		if ((isClicked && leftArrowHitBox.contains(mousePosition) || Gdx.input.isKeyJustPressed(Input.Keys.A)
				|| Gdx.input.isKeyJustPressed(Input.Keys.LEFT))) {
			currentOption--;
			
			if (currentOption < 0) {
				currentOption += values.size;
			}
		}
		
		if ((isClicked && leftArrowHitBox.contains(mousePosition) || Gdx.input.isKeyJustPressed(Input.Keys.D)
				|| Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))) {
			currentOption = (currentOption + 1) % values.size;
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean contains(float x, float y) {
		return position.contains(x, y);
	}
}

