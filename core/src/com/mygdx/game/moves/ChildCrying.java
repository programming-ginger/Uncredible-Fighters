package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.UncredibleFighter;

public class ChildCrying extends Move {

	private static final float CRYING_TIME = 1.5f;
	
	public ChildCrying() {
        setTimeBeforeEffect(0);
        setTimeAfterEffect(1.5f);
	}
	
	public boolean updateMove(float delta, UncredibleFighter attacker, UncredibleFighter enemy) {
		boolean result = super.updateMove(delta, attacker, enemy);
		
		if (!result) {
			attacker.setTexture(new Texture("Child/ChildFightingSprite.png"));
		}
		return result;
	}
	
	@Override
	protected boolean moveHits(UncredibleFighter attacker, UncredibleFighter enemy) {
		return true;
	}

	@Override
	public void applyEffect(UncredibleFighter self, UncredibleFighter enemy) {
		self.setTexture(new Texture("Child/ChildCrying.png"));
	}

}
