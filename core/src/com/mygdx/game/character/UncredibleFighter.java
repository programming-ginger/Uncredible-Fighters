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
import com.mygdx.game.data.Constants;
import com.mygdx.game.data.Options;
import com.mygdx.game.moves.Move;
import com.mygdx.game.screen.FightingScreen;

public abstract class UncredibleFighter {
	private String name;
	private int maxHP;
	private int currentHP;
	private float speed;
	private float speedBonus;
	private float speedBonusDuration;
	
	private boolean isConfused;
	private float confusionDuration;
	
	private float stunDuration;
	
	private float angle = 0;
	private int rotationDirectionFactor;
	protected Move move1;
	protected Move move2;

	protected Move activeMove;
	protected Sprite sprite;
	public boolean jumping = false;
	public boolean falling = false;
	public float moveX = 0;
	public float moveY = 0;
	public final float jumpSpeed = 22;
	public Action action;

	public void lookLeft() {
		if (!looksLeft()) {
			sprite.flip(true, false);
		}
	}

	public void lookRight() {
		if (looksLeft()) {
			sprite.flip(true, false);
		}
	}
	
	public UncredibleFighter() {
		this.sprite = new Sprite();
		
		isConfused = false;
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
		
		if (looksLeft()) {
			x = x - sprite.getWidth() * (widthRatio - 1);
		}
		
		if (angle == 0) {
		batch.draw(currentSprite, x, y, sprite.getWidth() * widthRatio, sprite.getHeight() * heightRatio, 0, 0,
				currentSprite.getWidth(), currentSprite.getHeight(), looksLeft(), false);
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
	
	protected void useMove(Move move) {
		if (activeMove == null) {
			activeMove = move;
	        activeMove.use();
		}
	}

//    public void addAction() {
//    	action.act(getCurrentHP());
//    }

	public void update(float delta, UncredibleFighter enemy) {
		
		if (canMove()) {
			move(delta, enemy);
		}
		
		if (activeMove != null) {
			if (!activeMove.updateMove(delta, this, enemy)) {
				activeMove = null;
			}
		}
		
		if (speedBonus > Constants.EPSILON) {
			speedBonusDuration -= delta;
			
			if (speedBonusDuration <= 0) {
				speedBonusDuration = 0;
				speedBonus = 0;
			}
		}
		
		if (isConfused) {
			confusionDuration -= delta;
			
			if (confusionDuration <= 0) {
				confusionDuration = 0;
				isConfused = false;;
			}
		}
		
		if (stunDuration > Constants.EPSILON) {
			stunDuration -= delta;
			
			if (stunDuration <= 0) {
				stunDuration = 0;
			}
		}
	}
	
	private void move(float delta, UncredibleFighter enemy) {
		
		Rectangle rectA = sprite.getBoundingRectangle();
		Rectangle rectB = enemy.getRectangle();
		if (this.jumping) {
			this.moveY -= Constants.GRAVITY * delta;
			if (this.moveY <= 0) {
				this.moveY = 0;
				this.falling = true;
				this.jumping = false;
			}
		} else if (this.falling) {
			this.moveY -= Constants.GRAVITY * delta;
			if (rectA.y + this.moveY < FightingScreen.paddingBottom) {
				this.falling = false;
			}
		} else {
			this.moveY = 0;
		}

		float tmp = rectA.x;
		rectA.x += this.moveX * delta * Options.getWindowWidth() * Constants.SPEED_FACTOR;
		if (rectA.overlaps(rectB))
			rectA.x = tmp;

		tmp = rectA.y;
		rectA.y = Math.max(rectA.y + this.moveY * delta * Options.getWindowHeight() * Constants.SPEED_FACTOR,
				FightingScreen.paddingBottom);
		if (rectA.overlaps(rectB)) {
			rectA.y = tmp;
			
			if (rectB.getX() > rectA.getX()) {
				rectA.x -= Constants.SIDE_PUSH_SPEED_FOR_STACKED_FIGHTERS * delta * Options.getWindowWidth() * Constants.SPEED_FACTOR;
			}
			else {
				rectA.x += Constants.SIDE_PUSH_SPEED_FOR_STACKED_FIGHTERS * delta * Options.getWindowWidth() * Constants.SPEED_FACTOR;
			}
		}
		sprite.setPosition(rectA.x, rectA.y);
	}
	
	public void jump() {
		if (!isConfused) {
			actuallyJump();			
		}
		else {
			actuallyMoveDown();
		}
	}
	public void moveLeft() {
		if (!isConfused) {
			actuallyMoveLeft();			
		}
		else {
			actuallyMoveRight();
		}
	}
	public void moveDown() {
		if (!isConfused) {
			actuallyMoveDown();			
		}
		else {
			actuallyJump();
		}
	}
	public void moveRight() {
		if (!isConfused) {
			actuallyMoveRight();		
		}
		else {
			actuallyMoveLeft();	
		}
	}
	
	public void actuallyJump() {
		if (this.moveY == 0 && !this.jumping && !this.falling) {
			this.jumping = true;
			this.moveY = this.jumpSpeed;
		}
	}
	public void actuallyMoveLeft() {
		lookLeft();
		if ((sprite.getX() - this.moveX) >= FightingScreen.paddingLeft) {
			this.moveX = -1 * this.getSpeed();
		}
	}
	public void actuallyMoveDown() {
		if (this.jumping) {
			this.jumping = false;
			this.falling = true;
		}
		if (this.falling)
			this.moveY -= 0.5;
	}
	public void actuallyMoveRight() {
		lookRight();
		if ((sprite.getX() + this.moveX) < Options.getWindowWidth() - FightingScreen.paddingRight * 2) {
			this.moveX = 1 * this.getSpeed();
		}
	}
	
	public boolean canMove() {
		return activeMove == null && this.stunDuration < Constants.EPSILON;
	}

	public void setPosition(float x, float y) {
		sprite.setPosition(x - sprite.getWidth()/2, y);
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
		return (speed + speedBonus);
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
		return sprite.isFlipX();
	}

	public abstract Texture getSpecificBackground();

	/*
	 * returns if Character is still falling
	 */
	public boolean fallToTheGround(float delta) {
		if (angle == 0) {
			sprite.setTexture(getKOTexture());
			sprite.flip(looksLeft(), false);
			
			
			if (looksLeft()) {
				rotationDirectionFactor = -1;
				sprite.setOrigin(sprite.getWidth(), 0);
			}
			else {
				rotationDirectionFactor = 1;
				sprite.setOrigin(0, 0);
			}
		}
		
		angle += delta * Constants.FALL_SPEED_AFTER_KO * rotationDirectionFactor;
		
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

	public void confuse(float duration) {
		confusionDuration = duration;
		isConfused = true;
	}

	protected void bore(float boredomSpeedFactor) {
		this.setSpeed(speed * boredomSpeedFactor);
	}

	public void stun(float duration) {
		this.stunDuration = duration;
		
	}
}
