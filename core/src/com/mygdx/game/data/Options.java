package com.mygdx.game.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Options implements Serializable{

	private int musicVolume;
	private int soundVolume;

	private int windowHeight;
	private int windowWidth;

	private int fightTime;
	
	private static Options instance;

	public final static int MAX_VOLUME = 100;
	private final static String SAVEFILE_NAME = "Options.txt";
	
	private Options() {
		this.musicVolume = 50;
		this.soundVolume = 50;
		
		this.windowHeight = 480;
		this.windowWidth = 800;
		
		this.fightTime = 300;
	}
	
	private static Options getInstance() {
		
		if (instance == null) {
			File saveFile = new File(SAVEFILE_NAME);
			if (!saveFile.exists()) {
				try {
					saveFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				instance = new Options();
				return instance;
			}
			
			try {
		        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(SAVEFILE_NAME));
		        instance = (Options) stream.readObject();
		        stream.close();
		        
		        if (instance == null) {
		        	instance = new Options();
		        }
		        return instance; 
		    } catch (ClassNotFoundException cnfex) {
		        System.err.println("Class Options was not found");
		    } catch (IOException ioex) {
		       instance = new Options();
		       return instance;
		    }
			catch (Exception e) {
				throw new Error("Could not load Options");
			}
		}
		return instance;
		
		 
	}
	
	public static void save() {
		try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(SAVEFILE_NAME));
            stream.writeObject(instance);
            stream.close();
        } catch (IOException ioex) {
            System.err.println("Error while saving Options to " + SAVEFILE_NAME);
            ioex.printStackTrace();
        }
	}

	public static int getMusicVolume() {
		return getInstance().musicVolume;
	}
	
	public static  float getMusicVolumeFloat() {
		return getInstance().musicVolume/100f;
	}

	public static  void setMusicVolume(int musicVolume) {
		if (isValidVolume(musicVolume)) {
			getInstance().musicVolume = musicVolume;
		} else
			throw new IllegalArgumentException("Invalid Music Volume " + musicVolume);
	}

	public static  int getSoundVolume() {
		return getInstance().soundVolume;
	}
	
	public static  float getSoundVolumeFloat() {
		return getInstance().soundVolume/100f;
	}

	public static  void setSoundVolume(int soundVolume) {
		if (isValidVolume(soundVolume)) {
			getInstance().soundVolume = soundVolume;
		} else
			throw new IllegalArgumentException("Invalid Sound Volume " + soundVolume);
	}

	public static  int getWindowHeight() {
		return getInstance().windowHeight;
	}

	public static  void setWindowHeight(int windowHeight) {
		getInstance().windowHeight = windowHeight;
	}

	public static  int getWindowWidth() {
		return getInstance().windowWidth;
	}

	public static  void setWindowWidth(int windowLength) {
		getInstance().windowWidth = windowLength;
	}

	private static  boolean isValidVolume(int volume) {
		return volume <= MAX_VOLUME && volume >= 0;
	}

	public static  int getFightTime(){
		return getInstance().fightTime;
	}

	public static void setFightTime(int fightTime){
		getInstance().fightTime = fightTime;
	}

}
