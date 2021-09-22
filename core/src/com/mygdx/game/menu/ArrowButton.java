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

public class ArrowButton<T> extends SelectToInteractMenuItem{

	private final static float ARROW_WIDTH = 0.05f;
	private final static float ARROW_OFFSET = 0.005f;
	private final static float TEXTPADDING = 0.2f;
	private final static float SELECTION_FRAME_SIZE = 0.1f;
	private final static float LABEL_OFFSET = 0.01f;

	private Array<ArrowButtonOption<T>> values;
	private int currentOption;

	private Texture buttonBackground;
	private Texture buttonArrow;
	private Texture selectionFrame;
	private PassiveTexture label;
	
	private Rectangle leftArrowHitBox;
	private Rectangle rightArrowHitBox;
	private Rectangle buttonPosition;

	Consumer<T> action;

	public ArrowButton(Texture label, Rectangle position, Consumer<T> action) {
		currentOption = 0;
		
		buttonPosition = position;
		leftArrowHitBox = new Rectangle(position.x - (ARROW_WIDTH+ARROW_OFFSET)*Options.getWindowHeight(), position.y, Options.getWindowHeight() * ARROW_WIDTH, position.getHeight());
		rightArrowHitBox = new Rectangle(position.x + position.getWidth() + Options.getWindowHeight() * ARROW_OFFSET, position.y, Options.getWindowHeight() * ARROW_WIDTH, position.getHeight());
		
		this.position = new Rectangle(leftArrowHitBox.getX(), leftArrowHitBox.getY(), rightArrowHitBox.getX() + rightArrowHitBox.getWidth() - leftArrowHitBox.getX(), leftArrowHitBox.getHeight());
		
		this.buttonBackground = TextureLibrary.getButtonBackground();
		this.action = action;
		this.buttonArrow = TextureLibrary.geButtonArrow();
		this.selectionFrame = TextureLibrary.getSelectionFrame();
		this.label = MenuFactory.makePassiveTextureToLeft(label, leftArrowHitBox.x - LABEL_OFFSET* Options.getWindowHeight() , position.y + position.height/2, position.height);
		this.values = new Array<>();
		
		super.position = new Rectangle(this.label.getX(), this.buttonPosition.getY(), rightArrowHitBox.getX() + rightArrowHitBox.getWidth() - this.label.getX(), buttonPosition.getHeight());
	}

	public ArrowButton(Texture label, Rectangle position) {
		this(label, position, null);
	}

	public void performAction() {
		if (action != null) {
			action.accept(values.get(currentOption).getValue());
		}
	}
	
	public void addOption(ArrowButtonOption option, boolean currentValue) {
		values.add(option);
		if (currentValue) {
			this.currentOption = values.size-1;
		}
	}

	public void draw(SpriteBatch batch, boolean isSelected) {
		super.draw(batch, isSelected);
		
		if (isSelected) {
			batch.draw(selectionFrame, buttonPosition.x - SELECTION_FRAME_SIZE * position.getHeight(), buttonPosition.y - SELECTION_FRAME_SIZE * position.getHeight(), 
					buttonPosition.width + 2*SELECTION_FRAME_SIZE * position.getHeight(), buttonPosition.height + 2*SELECTION_FRAME_SIZE * position.getHeight());
		}
		
		label.draw(batch);
		batch.draw(buttonBackground, buttonPosition.x, buttonPosition.y, buttonPosition.width, buttonPosition.height);
		
		batch.draw(buttonArrow, leftArrowHitBox.x, leftArrowHitBox.y, leftArrowHitBox.getWidth(), leftArrowHitBox.getHeight(), 0, 0, buttonArrow.getWidth(), buttonArrow.getHeight(), true, false);
		batch.draw(buttonArrow, rightArrowHitBox.x, rightArrowHitBox.y, rightArrowHitBox.getWidth(), rightArrowHitBox.getHeight(), 0, 0, buttonArrow.getWidth(), buttonArrow.getHeight(), false, false);
		
		if (values.notEmpty()) {
			batch.draw(values.get(currentOption).getOptionText(), buttonPosition.x + buttonPosition.getHeight()*TEXTPADDING, buttonPosition.y + buttonPosition.getHeight()*TEXTPADDING, buttonPosition.width - 2* buttonPosition.getHeight()*TEXTPADDING, buttonPosition.height - 2* buttonPosition.getHeight()*TEXTPADDING);
		}
	}


	@Override
	public void update(SpriteBatch batch, Vector2 mousePosition, boolean isClicked, boolean isJustClicked) {
		super.update(batch, mousePosition, isClicked, isJustClicked);
		if (isSelected) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.A)|| Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				currentOption--;
				
				if (currentOption < 0) {
					currentOption += values.size;
				}
			}
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.D)|| Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				currentOption = (currentOption + 1) % values.size;
			}
		}
		
		if (isJustClicked && leftArrowHitBox.contains(mousePosition)){
			currentOption--;
			
			if (currentOption < 0) {
				currentOption += values.size;
			}
		}
		
		if (isJustClicked && rightArrowHitBox.contains(mousePosition)) {
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

