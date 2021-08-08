package com.my.gdx.game.textures;

import com.badlogic.gdx.graphics.Texture;

/*
 * �ber diese Klasse k�nnen wir im restlichen Programm unabh�ngig vom Dateinamen auf die gew�nschte Textur zugreifen.
 * So kann man die Dateien auch leichter umbenennen und erstellt nicht mehrere gleiche Texturobjekte.
 */
public class TextureLibrary {
	
	private static Texture volumeController;
	private static Texture volumeBar;
	private static Texture selectionArrow;
	private static Texture mainMenuBackground;

	public static Texture getVolumeController() {
		if (volumeController == null) {
			volumeController = new Texture("Lautst�rkeregler.png");
		}
		return volumeController;
	}
	
	public static Texture getVolumeBar() {
		if (volumeBar == null) {
			volumeBar = new Texture("Lautst�rke-Leiste.png");
		}
		return volumeBar;
	}
	
	public static Texture getSelectionArrow() {
		if (selectionArrow == null) {
			selectionArrow = new Texture("Menu Selection Arrow.png");
		}
		return selectionArrow;
	}
	
	public static Texture getMainMenuBackground() {
		if (mainMenuBackground == null) {
			mainMenuBackground = new Texture("Background Menu.png");
		}
		return mainMenuBackground;
	}
}
