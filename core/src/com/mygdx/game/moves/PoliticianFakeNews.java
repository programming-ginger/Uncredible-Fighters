package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.UncredibleFighter;

public class PoliticianFakeNews extends Move {
	
	public PoliticianFakeNews() {
        Array<Texture> texturesAfterEffect = new Array<>();

        texturesAfterEffect.add(new Texture("Politician/PoliticianFakeNews.png"));

        setTexturesAfterEffect(texturesAfterEffect);

        setTimeBeforeEffect(0);
        setTimeAfterEffect(1);
	}

	@Override
	protected boolean moveHits(UncredibleFighter attacker, UncredibleFighter enemy) {
		return true;
	}

	@Override
	public void applyEffect(UncredibleFighter self, UncredibleFighter enemy) {
		self.setCurrentHP(10);
	}

}
