package com.mygdx.game.textures;

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
	private static Texture player1SelectionFrame;
	private static Texture player2SelectionFrame;
	private static Texture zero;
	private static Texture hundret;
	
	private static Texture classroom;
	private static Texture electionCampaign;
	private static Texture frontYard;
	private static Texture hallway;
	private static Texture playground;

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
	
	public static Texture getPlayer1SelectionFrame() {
		if (player1SelectionFrame == null) {
			player1SelectionFrame = new Texture("Player1SelectionFrame.png");
		}
		return player1SelectionFrame;
	}

	public static Texture getPlayer2SelectionFrame() {
		if (player2SelectionFrame == null) {
			player2SelectionFrame = new Texture("Player2SelectionFrame.png");
		}
		return player2SelectionFrame;		
	}
	
	public static Texture get0() {
		if (zero == null) {
			zero = new Texture("0.png");
		}
		return zero;
	}

	public static Texture get100() {
		if (hundret == null) {
			hundret = new Texture("100.png");
		}
		return hundret;		
	}
	
	public static Texture getClassroom() {
		if (classroom == null) {
			classroom = new Texture("Arenas/Classroom.jpg");
		}
		return classroom;		
	}
	
	public static Texture getElectionCampaign() {
		if (electionCampaign == null) {
			electionCampaign = new Texture("Arenas/Election Campaign.jpg");
		}
		return electionCampaign;		
	}
	
	public static Texture getFrontYard() {
		if (frontYard == null) {
			frontYard = new Texture("Arenas/Front Yard.jpg");
		}
		return frontYard;		
	}
	
	public static Texture getHallway() {
		if (hallway == null) {
			hallway = new Texture("Arenas/Hallway.jpg");
		}
		return hallway;		
	}
	
	public static Texture getPlayground() {
		if (playground == null) {
			playground = new Texture("Arenas/Playground.jpg");
		}
		return playground;		
	}
	
	public static void dispose() {
		if (volumeController != null) {
			volumeController.dispose();
		}
		
		if (volumeBar != null) {
			volumeBar.dispose();
		}
		
		if (selectionArrow != null) {
			selectionArrow.dispose();
		}
		
		if (mainMenuBackground != null) {
			mainMenuBackground.dispose();
		}
		
		if (selectedVolumeBar != null) {
			selectedVolumeBar.dispose();
		}
		
		if (logo != null) {
			logo.dispose();
		}
		
		if (player1SelectionFrame != null) {
			player1SelectionFrame.dispose();
		}
		
		if (player2SelectionFrame != null) {
			player2SelectionFrame.dispose();
		}
		
		if (zero != null) {
			zero.dispose();
		}
		
		if (hundret != null) {
			hundret.dispose();
		}
		
		if (classroom != null) {
			classroom.dispose();
		}
		
		if (electionCampaign != null) {
			electionCampaign.dispose();
		}
		
		if (frontYard != null) {
			frontYard.dispose();
		}
		
		if (hallway != null) {
			hallway.dispose();
		}
		
		if (playground != null) {
			playground.dispose();
		}
	}
}
