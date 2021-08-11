package com.my.gdx.game.textures;

import com.badlogic.gdx.graphics.Texture;

/*
 * Ueber diese Klasse koennen wir im restlichen Programm unabhaengig vom Dateinamen auf die gewuenschte Textur zugreifen.
 * So kann man die Dateien auch leichter umbenennen und erstellt nicht mehrere gleiche Texturobjekte.
 */
public class TextureLibrary {

	private static Texture volumeController;
	private static Texture volumeBar;
	private static Texture selectionArrow;
	private static Texture mainMenuBackground;
	private static Texture selectedVolumeBar;
	private static Texture logo;

	public static Texture getVolumeController() {
		if (volumeController == null) {
			volumeController = new Texture("MusicNVolumeController.png");
		}
		return volumeController;
	}

	public static Texture getVolumeBar() {
		if (volumeBar == null) {
			volumeBar = new Texture("MusicNVolumeBar.png");
		}
		return volumeBar;
	}

	public static Texture getSelectionArrow() {
		if (selectionArrow == null) {
			selectionArrow = new Texture("MenuSelectionArrow.png");
		}
		return selectionArrow;
	}

	public static Texture getMainMenuBackground() {
		if (mainMenuBackground == null) {
			mainMenuBackground = new Texture("MenuBackground.png");
		}
		return mainMenuBackground;
	}

	public static Texture getSelectedVolumeBar() {
		if (selectedVolumeBar == null) {
			selectedVolumeBar = new Texture("MusicNVolumeBar Selected.png");
		}
		return selectedVolumeBar;
	}

	public static Texture getLogo() {
		if (logo == null) {
			logo = new Texture("Logo.png");
		}
		return logo;		
	}
}
