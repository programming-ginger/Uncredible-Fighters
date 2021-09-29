package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.UncredibleFighter;

public class ChildCrying extends Move {

	private static final float CRYING_TIME = 1.5f;
	
	public ChildCrying() {
        Array<Texture> texturesBeforeEffect = new Array<>();

        texturesBeforeEffect.add(new Texture("Child/ChildCrying.png"));

        setTexturesBeforeEffect(texturesBeforeEffect);

        setTimeBeforeEffect(1.5f);
        setTimeAfterEffect(0);
	}
	
	@Override
	protected boolean moveHits(UncredibleFighter attacker, UncredibleFighter enemy) {
		return false;
	}

	@Override
	public void applyEffect(UncredibleFighter self, UncredibleFighter enemy) {
		return;
	}

}
