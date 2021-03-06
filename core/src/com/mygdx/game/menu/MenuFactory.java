package com.mygdx.game.menu;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.screen.CharacterChoiceScreen;
import com.mygdx.game.screen.MenuScreen;
import com.mygdx.game.textures.TextureLibrary;
import com.mygdx.game.data.Options;
import com.mygdx.game.UncredibleFighters;
import com.mygdx.game.character.*;

public class MenuFactory {
	
	private final static float LOGO_SIZE = 0.35f;
	private final static float LOGO_Y = 0.8f;
	
	private final static float BACKBUTTON_SIZE = 0.07f;
	private final static float BACKBUTTON_X = 0.5f;
	private final static float BACKBUTTON_Y = 0.15f;
	
	final static float MAIN_MENU_BUTTON_SIZE = 0.07f;
	final static float MAIN_MENU_SPIELEN_Y = 0.5f;
	final static float MAIN_MENU_EINSTELLUNGEN_Y = 0.35f;
	final static float MAIN_MENU_BEENDEN_Y = 0.2f;

	public static Button makeButton(Texture texture, float xCenter, float yCenter, float height, ButtonAction action) {
		Rectangle rectangle = makeScaledRectangleForTexture(texture, xCenter, yCenter, height);
		return new Button(texture, rectangle, action);
	}
	
	public static CharacterPortrait makeCharacterPortrait(Texture texture, float xCenter, float yCenter, float height, Supplier<UncredibleFighter> supplier) {
		Rectangle rectangle = makeScaledRectangleForTexture(texture, xCenter, yCenter, height);
		return new CharacterPortrait(texture, rectangle, supplier);
	}

	public static PassiveTexture makePassiveTexture(Texture texture, float xCenter, float yCenter, float height) {
		Rectangle rectangle = makeScaledRectangleForTexture(texture, xCenter, yCenter, height);
		return new PassiveTexture(texture, rectangle);
	}

	public static PassiveTexture makePassiveTextureToLeft(Texture texture, float xRight, float yCenter, float height) {
		Rectangle rectangle = makeScaledRectangleForTextureToLeft(texture, xRight, yCenter, height);
		return new PassiveTexture(texture, rectangle);
	}

	public static Rectangle makeScaledRectangleForTexture(Texture texture, float xCenter, float yCenter, float height) {
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		float boxWidth = height * ratio;
		float x = xCenter - boxWidth / 2f;
		float y = yCenter - height / 2f;

		return new Rectangle(x, y, boxWidth, height);
	}

	public static Rectangle makeScaledRectangleForTextureToRight(Texture texture, float xLeft, float yCenter,
			float height) {
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		float boxWidth = height * ratio;
		float y = yCenter - height / 2f;

		return new Rectangle(xLeft, y, boxWidth, height);
	}

	public static Rectangle makeScaledRectangleForTextureToLeft(Texture texture, float xRight, float yCenter,
			float height) {
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		float boxWidth = height * ratio;
		float x = xRight - boxWidth;
		float y = yCenter - height / 2f;

		return new Rectangle(x, y, boxWidth, height);
	}

	public static MenuScreen createMainMenu() {

		MenuScreen mainMenu = new MenuScreen();
		mainMenu.setBackground(TextureLibrary.getMainMenuBackground());

		Texture texture;
		ButtonAction action;

		float boxHeight;
		float xCenter = Options.getWindowWidth() / 2f;
		float yCenter;
		
		// Logo
		texture = TextureLibrary.getLogo();
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		boxHeight = Options.getWindowHeight() * LOGO_SIZE;
		xCenter = Options.getWindowWidth() / 2f;
		yCenter = Options.getWindowHeight() * LOGO_Y;

		PassiveTexture logo = makePassiveTexture(texture, xCenter, yCenter, boxHeight);
		mainMenu.addPassiveTexture(logo);

		// "Spielen" Knopf
		texture = new Texture("Buttons/ButtonSpielen.PNG");
		boxHeight = Options.getWindowWidth() * MAIN_MENU_BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * MAIN_MENU_SPIELEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showCharacterChoice();
			}
		};
		Button playButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(playButton);

		// "Einstellungen" Knopf
		texture = new Texture("Buttons/ButtonEinstellungen.PNG");
		boxHeight = Options.getWindowWidth() * MAIN_MENU_BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * MAIN_MENU_EINSTELLUNGEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showSettings();
			}
		};
		Button optionsButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(optionsButton);

		// "Beenden" Knopf
		texture = new Texture("Buttons/ButtonBeenden.PNG");

		boxHeight = Options.getWindowWidth() * MAIN_MENU_BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * MAIN_MENU_BEENDEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.closeGame();
			}
		};
		Button endButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(endButton);
		
		// Linking for comfortable selection with arrow keys
		
		playButton.setItemAbove(endButton);
		playButton.setItemBelow(optionsButton);
		
		optionsButton.setItemAbove(playButton);
		optionsButton.setItemBelow(endButton);
		
		endButton.setItemAbove(optionsButton);
		endButton.setItemBelow(playButton);

		return mainMenu;
	}
	
	
	public static MenuScreen turnIntoFightingPauseMenu(MenuScreen mainMenu) {

		mainMenu.clear();
		
		Texture texture;
		ButtonAction action;

		float boxHeight;
		float xCenter = Options.getWindowWidth() / 2f;
		float yCenter;
		
		// Logo
		texture = TextureLibrary.getLogo();
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		boxHeight = Options.getWindowHeight() * LOGO_SIZE;
		xCenter = Options.getWindowWidth() / 2f;
		yCenter = Options.getWindowHeight() * LOGO_Y;

		PassiveTexture logo = makePassiveTexture(texture, xCenter, yCenter, boxHeight);
		mainMenu.addPassiveTexture(logo);

		// "Fortsetzen" Knopf
		texture = new Texture("Buttons/ButtonFortsetzen.PNG");
		boxHeight = Options.getWindowWidth() * MAIN_MENU_BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * MAIN_MENU_SPIELEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.continueFight();
			}
		};
		Button playButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(playButton);

		// "Einstellungen" Knopf
		texture = new Texture("Buttons/ButtonEinstellungen.PNG");
		boxHeight = Options.getWindowWidth() * MAIN_MENU_BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * MAIN_MENU_EINSTELLUNGEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showFightingSettings();
			}
		};
		Button optionsButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(optionsButton);

		// "Beenden" Knopf
		texture = new Texture("Buttons/ButtonSpielBeenden.PNG");

		boxHeight = Options.getWindowWidth() * MAIN_MENU_BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * MAIN_MENU_BEENDEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showMainMenuScreen();
			}
		};
		Button endButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(endButton);
		
		// Linking for comfortable selection with arrow keys
		
		playButton.setItemAbove(endButton);
		playButton.setItemBelow(optionsButton);
		
		optionsButton.setItemAbove(playButton);
		optionsButton.setItemBelow(endButton);
		
		endButton.setItemAbove(optionsButton);
		endButton.setItemBelow(playButton);

		return mainMenu;
	}

	public static MenuScreen createOptionsMenu() {
		final float itemHeight = 0.1f;
		
		MenuScreen menu = new MenuScreen();
		menu.setBackground(TextureLibrary.getMainMenuBackground());

		Texture texture;
		float x;
		float y;
		float height;;
		IntConsumer action;
		
		// Logo
		texture = TextureLibrary.getLogo();
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		height = Options.getWindowHeight() * LOGO_SIZE;
		x = Options.getWindowWidth() / 2f;
		y = Options.getWindowHeight() * LOGO_Y;

		PassiveTexture logo = makePassiveTexture(texture, x, y, height);
		menu.addPassiveTexture(logo);

		// Sounds-Regler
		texture = new Texture("Buttons/ButtonSound.png");
		x = Options.getWindowWidth() * 0.3f;
		y = Options.getWindowHeight() * 0.45f;
		height = Options.getWindowHeight() * itemHeight;
		action = new IntConsumer() {
			@Override
			public void accept(int value) {
				Options.setSoundVolume(value);
			}
		};
		VolumeBar soundBar = new VolumeBar(texture, x, y, height, Options.getSoundVolume(), action);
		menu.addMenuItem(soundBar);

		// Musik-Regler
		texture = new Texture("Buttons/ButtonMusik.png");
		y = Options.getWindowHeight() * 0.2f;
		height = Options.getWindowHeight() * itemHeight;
		action = new IntConsumer() {
			@Override
			public void accept(int value) {
				Options.setMusicVolume(value);
			}
		};
		VolumeBar musicBar = new VolumeBar(texture, x, y, height, Options.getMusicVolume(), action);
		menu.addMenuItem(musicBar);
		
		// Kampfzeit-Selector
		x = Options.getWindowWidth() * 0.75f;
		y = Options.getWindowHeight() * 0.45f;
		height = Options.getWindowHeight() * itemHeight;
		Rectangle rect = new Rectangle(x, y - height/2, 1.5f*height, height);
		
		Consumer<Integer> consumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer fightTime) {
				Options.setFightTime(fightTime);				
			}
		};
		ArrowButton<Integer> arrowButton = new ArrowButton<>(new Texture("Buttons/FightTime.png"), rect, consumer);
		arrowButton.addOption(new ArrowButtonOption<Integer>(new Texture("60.png"), 60), Options.getFightTime() == 60);
		arrowButton.addOption(new ArrowButtonOption<Integer>(new Texture("100.png"), 100), Options.getFightTime() == 100);
		arrowButton.addOption(new ArrowButtonOption<Integer>(new Texture("150.png"), 150), Options.getFightTime() == 150);
		arrowButton.addOption(new ArrowButtonOption<Integer>(new Texture("200.png"), 200), Options.getFightTime() == 200);
		arrowButton.addOption(new ArrowButtonOption<Integer>(new Texture("300.png"), 300), Options.getFightTime() == 300);
		
		menu.addMenuItem(arrowButton);
		
		// Zurueck-Button
		texture = new Texture("Buttons/ButtonZurueck.PNG");

		height = Options.getWindowWidth() * BACKBUTTON_SIZE;
		x = Options.getWindowWidth() * 0.75f;
		y = Options.getWindowHeight() * BACKBUTTON_Y;

		ButtonAction buttonAction = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showMainMenuScreen();
			}
		};
		Button button = makeButton(texture, x, y, height, buttonAction);
		menu.addMenuItem(button);
		
		// linking
		
		soundBar.setItemAbove(button);
		soundBar.setItemBelow(musicBar);
		soundBar.setItemRight(arrowButton);
		soundBar.setItemLeft(arrowButton);
		
		musicBar.setItemAbove(soundBar);
		musicBar.setItemBelow(button);
		
		button.setItemAbove(musicBar);
		button.setItemBelow(soundBar);
		
		arrowButton.setItemBelow(button);
		arrowButton.setItemAbove(button);
		arrowButton.setItemLeft(soundBar);
		arrowButton.setItemRight(soundBar);

		return menu;
	}
	
	public static MenuScreen turnIntoOptionsMenu(MenuScreen menu) {
			
		final float itemHeight = 0.1f;
		menu.clear();

		Texture texture;
		float x;
		float y;
		float height;;
		IntConsumer action;
		
		// Logo
		texture = TextureLibrary.getLogo();
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		height = Options.getWindowHeight() * LOGO_SIZE;
		x = Options.getWindowWidth() / 2f;
		y = Options.getWindowHeight() * LOGO_Y;

		PassiveTexture logo = makePassiveTexture(texture, x, y, height);
		menu.addPassiveTexture(logo);

		// Sounds-Regler
		texture = new Texture("Buttons/ButtonSound.png");
		x = Options.getWindowWidth() * 0.3f;
		y = Options.getWindowHeight() * 0.45f;
		height = Options.getWindowHeight() * itemHeight;
		action = new IntConsumer() {
			@Override
			public void accept(int value) {
				Options.setSoundVolume(value);
			}
		};
		VolumeBar soundBar = new VolumeBar(texture, x, y, height, Options.getSoundVolume(), action);
		menu.addMenuItem(soundBar);

		// Musik-Regler
		texture = new Texture("Buttons/ButtonMusik.png");
		y = Options.getWindowHeight() * 0.2f;
		height = Options.getWindowHeight() * itemHeight;
		action = new IntConsumer() {
			@Override
			public void accept(int value) {
				Options.setMusicVolume(value);
			}
		};
		VolumeBar musicBar = new VolumeBar(texture, x, y, height, Options.getMusicVolume(), action);
		menu.addMenuItem(musicBar);
		
		// Zurueck-Button
		texture = new Texture("Buttons/ButtonZurueck.PNG");

		height = Options.getWindowWidth() * BACKBUTTON_SIZE;
		x = Options.getWindowWidth() * 0.75f;
		y = Options.getWindowHeight() * BACKBUTTON_Y;

		ButtonAction buttonAction = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showFightingMenu();
			}
		};
		Button button = makeButton(texture, x, y, height, buttonAction);
		menu.addMenuItem(button);
		
		// linking
		
		soundBar.setItemAbove(button);
		soundBar.setItemBelow(musicBar);
		
		musicBar.setItemAbove(soundBar);
		musicBar.setItemBelow(button);
		
		button.setItemAbove(musicBar);
		button.setItemBelow(soundBar);

		return menu;
	}
	
	public static MenuScreen createCharacterChoiceScreen() {

		final float PORTRAIT_SIZE = 0.15f;
		final float FIRST_ROW_Y = 0.75f;
		final float SECOND_ROW_Y = 0.4f;
		
		MenuScreen menu = new CharacterChoiceScreen();
		menu.setBackground(TextureLibrary.getMainMenuBackground());

		Texture texture;
		Supplier<UncredibleFighter> supplier;
		ButtonAction action;

		float boxHeight;
		float xCenter;
		float yCenter;
		
		// Child-Portrait
		texture = TextureLibrary.getChildPortrait();;
		boxHeight = Options.getWindowWidth() * PORTRAIT_SIZE;
		xCenter = Options.getWindowWidth() * 0.2f;
		yCenter = Options.getWindowHeight() * FIRST_ROW_Y;

		supplier = new Supplier<UncredibleFighter>() {
			@Override
			public UncredibleFighter get() {
				return new Child();
			}
		};
		CharacterPortrait childPortrait = makeCharacterPortrait(texture, xCenter, yCenter, boxHeight, supplier);
		menu.addMenuItem(childPortrait);
		
		// Teacher-Portrait
		texture = TextureLibrary.getTeacherPortrait();;
		boxHeight = Options.getWindowWidth() * PORTRAIT_SIZE;
		xCenter = Options.getWindowWidth() * 0.5f;
		yCenter = Options.getWindowHeight() * FIRST_ROW_Y;

		supplier = new Supplier<UncredibleFighter>() {
			@Override
			public UncredibleFighter get() {
				return new Teacher();
			}
		};
		Button teacherPortrait = makeCharacterPortrait(texture, xCenter, yCenter, boxHeight, supplier);
		menu.addMenuItem(teacherPortrait);
		
		// Maid-Portrait
		texture = TextureLibrary.getMaidPortrait();;
		boxHeight = Options.getWindowWidth() * PORTRAIT_SIZE;
		xCenter = Options.getWindowWidth() * 0.8f;
		yCenter = Options.getWindowHeight() * FIRST_ROW_Y;

		supplier = new Supplier<UncredibleFighter>() {
			@Override
			public UncredibleFighter get() {
				return new Maid();
			}
		};
		CharacterPortrait maidPortrait = makeCharacterPortrait(texture, xCenter, yCenter, boxHeight, supplier);
		menu.addMenuItem(maidPortrait);
		
		// Politician-Portrait
		texture = TextureLibrary.getPoliticianPortrait();;
		boxHeight = Options.getWindowWidth() * PORTRAIT_SIZE;
		xCenter = Options.getWindowWidth() * 0.33f;
		yCenter = Options.getWindowHeight() * SECOND_ROW_Y;

		supplier = new Supplier<UncredibleFighter>() {
			@Override
			public UncredibleFighter get() {
				return new Politician();
			}
		};
		CharacterPortrait politicianPortrait = makeCharacterPortrait(texture, xCenter, yCenter, boxHeight, supplier);
		menu.addMenuItem(politicianPortrait);
		
		// Grandpa-Portrait
		texture = TextureLibrary.getGrandpaPortrait();
		boxHeight = Options.getWindowWidth() * PORTRAIT_SIZE;
		xCenter = Options.getWindowWidth() * 0.66f;
		yCenter = Options.getWindowHeight() * SECOND_ROW_Y;

		supplier = new Supplier<UncredibleFighter>() {
			@Override
			public UncredibleFighter get() {
				return new Grandpa();
			}
		};
		
		CharacterPortrait grandpaPortrait = makeCharacterPortrait(texture, xCenter, yCenter, boxHeight, supplier);
		menu.addMenuItem(grandpaPortrait);
		
		// Zurueck-Button
		texture = new Texture("Buttons/ButtonZurueck.png");

		boxHeight = Options.getWindowWidth() * BACKBUTTON_SIZE;
		xCenter = Options.getWindowWidth() * BACKBUTTON_X;;
		yCenter = Options.getWindowHeight() * BACKBUTTON_Y;

		ButtonAction buttonAction = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showMainMenuScreen();
			}
		};
		Button backButton = makeButton(texture, xCenter, yCenter, boxHeight, buttonAction);
		menu.addMenuItem(backButton);
		
		// linking
		
		childPortrait.setItemAbove(backButton);
		childPortrait.setItemBelow(politicianPortrait);
		childPortrait.setItemRight(teacherPortrait);
		childPortrait.setItemLeft(maidPortrait);
		
		teacherPortrait.setItemAbove(backButton);
		teacherPortrait.setItemBelow(grandpaPortrait);
		teacherPortrait.setItemRight(maidPortrait);
		teacherPortrait.setItemLeft(childPortrait);
		
		maidPortrait.setItemAbove(backButton);
		maidPortrait.setItemBelow(grandpaPortrait);
		maidPortrait.setItemRight(childPortrait);
		maidPortrait.setItemLeft(teacherPortrait);
				
		politicianPortrait.setItemAbove(childPortrait);
		politicianPortrait.setItemBelow(backButton);
		politicianPortrait.setItemRight(grandpaPortrait);
		politicianPortrait.setItemLeft(grandpaPortrait);
		
		grandpaPortrait.setItemAbove(teacherPortrait);
		grandpaPortrait.setItemBelow(backButton);
		grandpaPortrait.setItemRight(politicianPortrait);
		grandpaPortrait.setItemLeft(politicianPortrait);
		
		backButton.setItemAbove(grandpaPortrait);
		backButton.setItemBelow(maidPortrait);

		
		return menu;
	}

}
