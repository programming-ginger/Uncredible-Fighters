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
	private float angle = 0;
	private int rotationDirectionFactor;
	protected Move move1;
	protected Move move2;

	protected Move activeMove;
	protected Texture texture;
	protected Sprite sprite; //todo: Texture durch Sprite ersetzen, damit kann man vieles einfacher machen, wie drehen und Spiegeln
	protected Rectangle rectangle;
	protected boolean lookingLeft = false;
	public boolean jumping = false;
	public boolean falling = false;
	public float moveX = 0;
	public float moveY = 0;
	public final float jumpSpeed = 22;
	public Action action;
	
	private final static float FALL_SPEED_AFTER_KO = 100f;

	public void lookLeft() {
		lookingLeft = true;
	}

	public void lookRight() {
		lookingLeft = false;
	}

	public void draw(SpriteBatch batch) {
		Texture sprite;

		if (activeMove != null) {
			sprite = activeMove.getCurrentSprite();
		}

		else
			sprite = texture;

		draw(batch, sprite);
	}

	public void draw(SpriteBatch batch, Texture currentSprite) {
		float heightRatio = currentSprite.getHeight()/(texture.getHeight() + 0f);
		float widthRatio = currentSprite.getWidth()/(texture.getWidth() + 0f);
		
		float x = rectangle.getX();
		float y = rectangle.getY();
		
		if (lookingLeft) {
			x = x - rectangle.getWidth() * (widthRatio - 1);
		}
		
		if (angle == 0) {
		batch.draw(currentSprite, x, y, rectangle.getWidth() * widthRatio, rectangle.getHeight() * heightRatio, 0, 0,
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
		activeMove = move;
        activeMove.use();
	}

//    public void addAction() {
//    	action.act(getCurrentHP());
//    }

	public void update(float delta, UncredibleFighter enemy) {
		if (activeMove != null) {
			boolean state = activeMove.updateMove(delta, this, enemy);

			if (!state) {
				activeMove = null;
			}
		}
	}

	public void setPosition(float x, float y) {
		rectangle.x = x - rectangle.getWidth() / 2;
		rectangle.y = y;
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
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
		this.sprite = new Sprite(texture);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
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
			sprite.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			sprite.flip(lookingLeft, false);
			
			if (lookingLeft) {
				rotationDirectionFactor = -1;
			}
			else {
				rotationDirectionFactor = 1;
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
}
