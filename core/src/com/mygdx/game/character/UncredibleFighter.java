package com.mygdx.game.character;

import javax.sound.midi.Sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.UncredibleFighters;
import com.mygdx.game.moves.Move;

public abstract class UncredibleFighter {
	private String name;
	private int maxHP;
	private int currentHP;
	private float speed;
	private float speedBonus;
	private float speedBonusDuration;
	
	private float confusionSpeedFactor;
	private float confusionDuration;
	
	private float angle = 0;
	private int rotationDirectionFactor;
	protected Move move1;
	protected Move move2;

	protected Move activeMove;
	protected Sprite sprite;
	protected boolean lookingLeft = false;
	public boolean jumping = false;
	public boolean falling = false;
	public float moveX = 0;
	public float moveY = 0;
	public final float jumpSpeed = 22;
	public Action action;
	
	private final static float FALL_SPEED_AFTER_KO = 100f;
	private final static float EPSILON = 0.000001f;

	public void lookLeft() {
		lookingLeft = true;
	}

	public void lookRight() {
		lookingLeft = false;
	}
	
	public UncredibleFighter() {
		this.sprite = new Sprite();
		
		confusionSpeedFactor = 1;
		confusionDuration = 0;
		
		speedBonus = 0;
		speedBonusDuration = 0;
	}

	public void draw(SpriteBatch batch) {

		if (activeMove != null) {
			Texture sprite = activeMove.getCurrentSprite();
			draw(batch, sprite);
		}

		else {
			sprite.draw(batch);		
		}
	}

	public void draw(SpriteBatch batch, Texture currentSprite) {
		float heightRatio = currentSprite.getHeight()/(sprite.getTexture().getHeight() + 0f);
		float widthRatio = currentSprite.getWidth()/(sprite.getTexture().getWidth() + 0f);
		
		float x = sprite.getX();
		float y = sprite.getY();
		
		if (lookingLeft) {
			x = x - sprite.getWidth() * (widthRatio - 1);
		}
		
		if (angle == 0) {
		batch.draw(currentSprite, x, y, sprite.getWidth() * widthRatio, sprite.getHeight() * heightRatio, 0, 0,
				currentSprite.getWidth(), currentSprite.getHeight(), lookingLeft, false);
		}
		else {
			sprite.draw(batch);
		}
	}

	public void useMove1() {
		useMove(move1);
	}
	
	public void useMove2() {
		useMove(move2);
	}
	
	private void useMove(Move move) {
		if (activeMove == null) {
			activeMove = move;
	        activeMove.use();
		}
	}

//    public void addAction() {
//    	action.act(getCurrentHP());
//    }

	public void update(float delta, UncredibleFighter enemy) {
		if (activeMove != null) {
			if (!activeMove.updateMove(delta, this, enemy)) {
				activeMove = null;
			}
		}
		
		if (speedBonus < EPSILON) {
			speedBonusDuration -= delta;
			
			if (speedBonusDuration <= 0) {
				speedBonusDuration = 0;
				speedBonus = 0;
			}
		}
		
		if (confusionSpeedFactor > EPSILON) {
			confusionDuration -= delta;
			
			if (confusionDuration <= 0) {
				confusionDuration = 0;
				confusionSpeedFactor = 0;
			}
		}
	}

	public void setPosition(float x, float y) {
		sprite.setCenter(x, y);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		this.setCurrentHP(maxHP);
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public float getSpeed() {
		return (speed + speedBonus) * confusionSpeedFactor;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Texture getTexture() {
		if (activeMove != null) {
			return activeMove.getCurrentSprite();
		}
		return sprite.getTexture();
	}

	public void setTexture(Texture texture) {
		sprite.setRegion(texture);
	}

	public Rectangle getRectangle() {
		return sprite.getBoundingRectangle();
	}

	public void setRectangle(Rectangle rectangle) {
		this.sprite.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public void setMove1(Move move) {
		move1 = move;
	}

	public void reduceHP(int damage) {
		currentHP -= damage;
		
		if (currentHP <= 0) {
			currentHP = 0;
			UncredibleFighters.showWinnerScreen();
		}
		
	}

	public boolean looksLeft() {
		return this.lookingLeft;
	}

	public abstract Texture getSpecificBackground();

	/*
	 * returns if Character is still falling
	 */
	public boolean fallToTheGround(float delta) {
		if (angle == 0) {
			sprite = new Sprite(getKOTexture());
			sprite.flip(lookingLeft, false);
			
			
			if (lookingLeft) {
				rotationDirectionFactor = -1;
				sprite.setOrigin(sprite.getWidth(), 0);
			}
			else {
				rotationDirectionFactor = 1;
				sprite.setOrigin(0, 0);
			}
		}
		
		angle += delta * FALL_SPEED_AFTER_KO * rotationDirectionFactor;
		
		if (Math.abs(angle) >= 90) {
			angle = 90 * rotationDirectionFactor;
			sprite.setRotation(angle);
			return false;
		}
		sprite.setRotation(angle);
		return true;		
	}

	protected abstract Texture getKOTexture();

	public abstract Texture getPortrait();

	public void changeSpeedTemporarily(float bonus, float duration) {
		this.speedBonus = bonus;
		this.speedBonusDuration = duration;
	}

	public void invertControllsTemporarily(float duration) {
		// TODO Auto-generated method stub
		
	}
}
