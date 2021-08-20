package com.mygdx.game.menu;

import java.util.function.IntConsumer;
import java.util.function.Supplier;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.screen.CharacterChoiceScreen;
import com.mygdx.game.screen.MenuScreen;
import com.mygdx.game.textures.TextureLibrary;
import com.mygdx.game.data.Options;
import com.mygdx.game.UncredibleFighters;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.character.Child;
import com.mygdx.game.character.Grandpa;
import com.mygdx.game.character.Maid;
import com.mygdx.game.character.Politician;
import com.mygdx.game.character.Teacher;

public class MenuFactory {
	
	private final static float LOGO_SIZE = 0.35f;
	private final static float LOGO_Y = 0.8f;
	
	private final static float BACKBUTTON_SIZE = 0.07f;
	private final static float BACKBUTTON_X = 0.8f;
	private final static float BACKBUTTON_Y = 0.1f;

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

		final float BUTTON_SIZE = 0.07f;
		final float SPIELEN_Y = 0.5f;
		final float EINSTELLUNGEN_Y = 0.35f;
		final float BEENDEN_Y = 0.2f;

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
		texture = new Texture("PlayButton.PNG");
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * SPIELEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showCharacterChoice();
			}
		};
		Button playButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(playButton);

		// "Einstellungen" Knopf
		texture = new Texture("SettingButton.PNG");
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * EINSTELLUNGEN_Y;

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showSettings();
			}
		};
		Button optionsButton = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(optionsButton);

		// "Beenden" Knopf
		texture = new Texture("EndButton.PNG");

		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		yCenter = Options.getWindowHeight() * BEENDEN_Y;

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
		texture = new Texture("VolumeLabel.png");
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
		texture = new Texture("MusicLabel.png");
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
		
		// Zurück-Button
		texture = new Texture("BackButton.PNG");

		height = Options.getWindowWidth() * BACKBUTTON_SIZE;
		x = Options.getWindowWidth() * BACKBUTTON_X;;
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
		
		musicBar.setItemAbove(soundBar);
		musicBar.setItemBelow(button);
		
		button.setItemAbove(musicBar);
		button.setItemBelow(soundBar);

		return menu;
	}
	
	public static MenuScreen createCharacterChoiceScreen() {

		final float PORTRAIT_SIZE = 0.2f;
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
		texture = new Texture("child-portrait.jpg");
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
		texture = new Texture("teacher-portrait.jpg");
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
		texture = new Texture("maid-portrait.jpg");
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
		texture = new Texture("politician-portrait.jpg");
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
		texture = new Texture("grandpa-portrait.jpg");
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
		
		// Zurück-Button
		texture = new Texture("BackButton.png");

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
