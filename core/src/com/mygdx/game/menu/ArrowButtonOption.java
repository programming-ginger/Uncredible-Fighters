package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.Texture;

public class ArrowButtonOption<T> {
	
	private Texture optionText;
	private T value;
	
	public ArrowButtonOption(Texture optionText, T value) {
		this.optionText = optionText;
		this.value = value;
	}

	public Texture getOptionText() {
		return optionText;
	}

	public T getValue() {
		return value;
	}
}
