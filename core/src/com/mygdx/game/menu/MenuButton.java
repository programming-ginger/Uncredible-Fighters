package com.mygdx.game.menu;

import java.util.function.Supplier;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MenuButton {

	private Rectangle position;
	private Texture texture;
	
	Supplier<Void> action;
	
	public MenuButton (Texture texture, Rectangle position, Supplier<Void> action) {
		this.position = position;
		this.texture = texture;
		this.action = action;
	}
}
