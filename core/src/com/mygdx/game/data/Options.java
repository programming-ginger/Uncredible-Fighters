package com.mygdx.game.data;

public class Options {

	private static int musicVolume = 50;
	private static int soundVolume = 50;

	private static int WindowHeight = 480;
	private static int windowWidth = 800;

	private static int fightTime = 300;

	public final static int MAX_VOLUME = 100;
	

	public static int getMusicVolume() {
		return musicVolume;
	}

	public static void setMusicVolume(int musicVolume) {
		if (isValidVolume(musicVolume)) {
			Options.musicVolume = musicVolume;
		} else
			throw new IllegalArgumentException("Invalid Music Volume " + musicVolume);
	}

	public static int getSoundVolume() {
		return soundVolume;
	}

	public static void setSoundVolume(int soundVolume) {
		if (isValidVolume(soundVolume)) {
			Options.soundVolume = soundVolume;
		} else
			throw new IllegalArgumentException("Invalid Sound Volume " + musicVolume);
	}

	public static int getWindowHeight() {
		return WindowHeight;
	}

	public static void setWindowHeight(int windowHeight) {
		WindowHeight = windowHeight;
	}

	public static int getWindowWidth() {
		return windowWidth;
	}

	public static void setWindowWidth(int windowLength) {
		Options.windowWidth = windowLength;
	}

	private static boolean isValidVolume(int volume) {
		return volume <= MAX_VOLUME && volume >= 0;
	}

	public static int getFightTime()
	{
		return fightTime;
	}

	public static void setFightTime(int fightTime)
	{
		Options.fightTime = fightTime;
	}
}
