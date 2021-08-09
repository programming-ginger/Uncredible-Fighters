package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.prototype.PrototypeCharMove;
import com.mygdx.game.screen.CharacterChoiceScreen;
import com.mygdx.game.screen.FightingScreen;

public class UncredibleFighters extends Game {

	private static UncredibleFighters instance;

	public UncredibleFighters() {
		if (instance == null) {
			instance = this;
		} else
			throw new IllegalArgumentException("Game Object is created multiple times");
	}

	public static UncredibleFighters getInstance() {
		return instance;
	}

	public static void showSettings() {
		instance.setScreen(MenuFactory.createOptionsMenu());
	}

	public static void closeGame() {
		instance.dispose();
		Gdx.app.exit();
		System.exit(0);
	}

	@Override
	public void create() {
		showMainMenuScreen();
	}

	@Override
	public void dispose() {
		this.screen.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		this.screen.resize(width, height);
	}

	public static void showMainMenuScreen() {
		Screen menu = MenuFactory.createMainMenu();
		instance.setScreen(menu);
	}

	public static void showFightingScreen() {
		instance.screen.dispose();
		instance.setScreen(new PrototypeCharMove());
	}

	public static void showCharacterChoice() {
		instance.setScreen(MenuFactory.createCharacterChoiceScreen());
	}
}
