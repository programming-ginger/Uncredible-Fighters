package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.projectiles.Projectile;

public abstract class Move extends Group {


	private Array<Texture> texturesBeforeEffect;
	private Array<Texture> texturesAfterEffect;

	private float timeBeforeEffect;
	private float timeAfterEffect;

	private float passedTime;
	private boolean effectWasApplied;

	public void use() {
		passedTime = 0;
		effectWasApplied = false;
	}

	/*
	 * Updates the state of the move And applies the effect at the right time.
	 * Returns of the move is still in the use
	 */
	public boolean updateMove(float delta, UncredibleFighter attacker, UncredibleFighter enemy) {
		passedTime += delta;

		if (passedTime > timeBeforeEffect && !effectWasApplied) {
			if (moveHits(attacker, enemy)) {
				applyEffect(attacker, enemy);
			}
			effectWasApplied = true;
		}

		return passedTime < timeAfterEffect + timeBeforeEffect;

	}

	protected abstract boolean moveHits(UncredibleFighter attacker, UncredibleFighter enemy);

	public abstract void applyEffect(UncredibleFighter self, UncredibleFighter enemy);

	public Texture getCurrentSprite() {
		if (passedTime < timeBeforeEffect) {
			int i = (int) (passedTime / timeBeforeEffect * (texturesBeforeEffect.size));
			return texturesBeforeEffect.get(i);
		} else {
			int i = (int) ((passedTime - timeBeforeEffect) / timeAfterEffect * (texturesAfterEffect.size));
			return texturesAfterEffect.get(i);
		}
	}

	protected void setTexturesBeforeEffect(Array<Texture> texturesBeforeEffect) {
		this.texturesBeforeEffect = texturesBeforeEffect;
	}

	protected void setTexturesAfterEffect(Array<Texture> texturesAfterEffect) {
		this.texturesAfterEffect = texturesAfterEffect;
	}

	protected void setTimeBeforeEffect(float timeBeforeEffect) {
		this.timeBeforeEffect = timeBeforeEffect;
	}

	protected void setTimeAfterEffect(float timeAfterEffect) {
		this.timeAfterEffect = timeAfterEffect;
	}
}
