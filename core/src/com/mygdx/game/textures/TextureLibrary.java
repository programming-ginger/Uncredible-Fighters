package com.mygdx.game.textures;

import javax.xml.soap.Text;

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
	private static Texture buttonBackground;
	private static Texture buttonArrow;
	private static Texture selectionFrame;	
	
	private static Texture classroom;
	private static Texture electionCampaign;
	private static Texture frontYard;
	private static Texture hallway;
	private static Texture playground;
	
	private static Texture childPortrait;
	private static Texture grandpaPortrait;
	private static Texture teacherPortrait;
	private static Texture maidPortrait;
	private static Texture politicianPortrait;
	
	private static Texture pen;
	private static Texture walkingStick;
	private static Texture ruler;
	private static Texture stone;
	private static Texture puddle;	

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
			mainMenuBackground = new Texture("FightingWallpaper.png");
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
	
	public static Texture getChildPortrait() {
		if (childPortrait == null) {
			childPortrait = new Texture("Child/child-portrait.jpg");
		}
		return childPortrait;		
	}
	
	public static Texture getGrandpaPortrait() {
		if (grandpaPortrait == null) {
			grandpaPortrait = new Texture("Grandpa/grandpa-portrait.jpg");
		}
		return grandpaPortrait;		
	}
	
	public static Texture getTeacherPortrait() {
		if (teacherPortrait == null) {
			teacherPortrait = new Texture("Teacher/teacher-portrait.jpg");
		}
		return teacherPortrait;		
	}
	
	public static Texture getMaidPortrait() {
		if (maidPortrait == null) {
			maidPortrait = new Texture("Maid/maid-portrait.jpg");
		}
		return maidPortrait;		
	}
	
	public static Texture getPoliticianPortrait() {
		if (politicianPortrait == null) {
			politicianPortrait = new Texture("Politician/politician-portrait.jpg");
		}
		return politicianPortrait;		
	}
	
	public static Texture getPen() {
		if (pen == null) {
			pen = new Texture("Politician/Pen.png");
		}
		return pen;	
	}
	
	public static Texture getWalkingStick() {
		if(walkingStick == null) {
			walkingStick = new Texture("Grandpa/WalkingStick.png");
		}
		return walkingStick;
	}
	
	public static Texture getRuler() {
		if(ruler == null) {
			ruler = new Texture("Teacher/Ruler.png");
		}
		return ruler;
	}
	
	public static Texture getStone() {
		if(stone == null) {
			stone = new Texture("Child/Stone.png");
		}
		return stone;
	}
	
	public static Texture getPuddle() {
		if(puddle == null) {
			puddle = new Texture("Maid/Puddle.png");
		}
		return puddle;
	}

	public static Texture getButtonBackground() {
		if(buttonBackground == null) {
			buttonBackground = new Texture("ButtonBackground.png");
		}
		return buttonBackground;
	}
	

	public static Texture geButtonArrow() {
		if(buttonArrow == null) {
			buttonArrow = new Texture("ButtonArrow.png");
		}
		return buttonArrow;
	}
	
	public static Texture getSelectionFrame() {
		if(selectionFrame == null) {
			selectionFrame = new Texture("SelectionFrame.png");
		}
		return selectionFrame;
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
		
		if (childPortrait != null) {
			childPortrait.dispose();
		}
		
		if (grandpaPortrait != null) {
			grandpaPortrait.dispose();
		}
		
		if (teacherPortrait != null) {
			teacherPortrait.dispose();
		}
		
		if (maidPortrait != null) {
			maidPortrait.dispose();
		}
		
		if (politicianPortrait != null) {
			politicianPortrait.dispose();
		}
		
		if (pen != null) {
			pen.dispose();
		}
		
		if(walkingStick != null) {
			walkingStick.dispose();
		}
		
		if(stone != null) {
			stone.dispose();
		}
		
		if(puddle != null) {
			puddle.dispose();
		}
		
		if (buttonBackground != null) {
			buttonBackground.dispose();
		}
		
		if (buttonArrow != null) {
			buttonArrow.dispose();
		}
		
		if (selectionFrame != null) {
			selectionFrame.dispose();
		}		
	}
}
