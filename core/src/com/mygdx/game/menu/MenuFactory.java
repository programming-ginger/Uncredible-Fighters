package com.mygdx.game.menu;

import java.util.function.IntConsumer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.my.gdx.game.textures.TextureLibrary;
import com.mygdx.game.screen.MenuScreen;
import com.mygdx.game.data.Options;
import com.mygdx.game.UncredibleFighters;

public class MenuFactory {

	public static Button makeButton(Texture texture, float xCenter, float yCenter, float height, ButtonAction action) {
		Rectangle rectangle = makeScaledRectangleForTexture(texture, xCenter, yCenter, height);
		return new Button(texture, rectangle, action);
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

		Button button;

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
		button = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(button);

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
		button = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(button);

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
		button = makeButton(texture, xCenter, yCenter, boxHeight, action);
		mainMenu.addMenuItem(button);

		return mainMenu;
	}

	public static MenuScreen createOptionsMenu() {
		MenuScreen menu = new MenuScreen();
		menu.setBackground(TextureLibrary.getMainMenuBackground());

		Texture texture;
		float x = Options.getWindowWidth() * 0.3f;
		float y;
		float height;
		VolumeBar bar;
		IntConsumer action;

		// Sounds-Regler
		texture = new Texture("VolumeLabel.png");
		y = Options.getWindowHeight() * 0.45f;
		height = Options.getWindowHeight() * 0.1f;
		action = new IntConsumer() {
			@Override
			public void accept(int value) {
				Options.setSoundVolume(value);
			}
		};
		bar = new VolumeBar(texture, x, y, height, Options.getSoundVolume(), action);
		menu.addMenuItem(bar);

		// Musik-Regler
		texture = new Texture("MusicLabel.png");
		y = Options.getWindowHeight() * 0.2f;
		height = Options.getWindowHeight() * 0.1f;
		action = new IntConsumer() {
			@Override
			public void accept(int value) {
				Options.setMusicVolume(value);
			}
		};
		bar = new VolumeBar(texture, x, y, height, Options.getSoundVolume(), action);
		menu.addMenuItem(bar);

		return menu;
	}

}
