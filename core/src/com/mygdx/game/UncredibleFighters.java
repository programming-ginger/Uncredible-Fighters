package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.Character;
import com.mygdx.game.character.fighter.Child;
import com.mygdx.game.character.fighter.Maid;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.prototype.FightingGame;
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
		Character characterA = new Child("Kind", 150, 8, 3, new Texture("badlogic.jpg"));
		Character characterB = new Maid("Putzfrau", 200, 8, new Texture("badlogic.jpg"));
		FightingGame game = new FightingGame(characterA, characterB);

		FightingScreen screen = new FightingScreen(game);
		instance.setScreen(screen);
		game.setScreen(screen);
		//instance.setScreen(new PrototypeCharMove());
	}

	public static void showCharacterChoice() {
		//showFightingScreen();
		instance.setScreen(MenuFactory.createCharacterChoiceScreen());
	}
}
