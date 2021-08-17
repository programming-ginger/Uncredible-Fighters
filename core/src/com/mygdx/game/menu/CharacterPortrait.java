package com.mygdx.game.menu;

import java.util.function.Supplier;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;

public class CharacterPortrait extends Button {
	
	private Supplier<UncredibleFighter> characterSupplier;

	public CharacterPortrait(Texture texture, Rectangle position, Supplier<UncredibleFighter> supplier) {
		super(texture, position);
		this.characterSupplier = supplier;
	}
	
	public UncredibleFighter getFighter() {
		return characterSupplier.get();
	}

}
