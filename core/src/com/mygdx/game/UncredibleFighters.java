package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.character.Child;
import com.mygdx.game.character.Maid;
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

//	public static void showFightingScreen() {
//		
//		//instance.setScreen(new PrototypeCharMove());
//	}

	public static void showCharacterChoice() {
		//showFightingScreen();
		instance.setScreen(MenuFactory.createCharacterChoiceScreen());
	}

	public static void startFight(UncredibleFighter player1, UncredibleFighter player2) {
		FightingGame game = new FightingGame(player1, player2);

		FightingScreen screen = new FightingScreen(game);
		instance.setScreen(screen);
		game.setScreen(screen);
		
	}
}
