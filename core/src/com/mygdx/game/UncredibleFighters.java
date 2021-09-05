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
import com.mygdx.game.sound.MusicPlayer;
import com.mygdx.game.sound.SoundPlayer;
import com.mygdx.game.textures.TextureLibrary;

public class UncredibleFighters extends Game {

	private static UncredibleFighters instance;
	private static FightingScreen activeFight;

	private UncredibleFighters() {

	}

	public static UncredibleFighters getInstance() {
		if (instance == null) {
			instance = new UncredibleFighters();
		}
		return instance;
	}

	public static void showSettings() {
		instance.setScreen(MenuFactory.createOptionsMenu());
	}

	public static void closeGame() {
		getInstance().dispose();
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
		Options.save();
		TextureLibrary.dispose();
		SoundPlayer.dispose();
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
		getInstance().setScreen(menu);
		MusicPlayer.playMenuMusic();
	}

	public static void showCharacterChoice() {
		instance.setScreen(MenuFactory.createCharacterChoiceScreen());
	}

	public static void startFight(UncredibleFighter player1, UncredibleFighter player2) {
		FightingGame game = new FightingGame(player1, player2);

		activeFight = new FightingScreen(game);
		getInstance().setScreen(activeFight);
		game.setScreen(activeFight);
		MusicPlayer.playFightMusic();
		
	}
	
	public static void showFightingMenu() {
		activeFight.showMenu();
	}

	public static void continueFight() {
		activeFight.closeMenu();	
	}

	public static void showFightingSettings() {
		activeFight.showSettings();
		
	}
}
