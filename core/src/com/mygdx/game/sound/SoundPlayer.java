package com.mygdx.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.data.Options;

public class SoundPlayer {
	
	private static Sound selectionSound = Gdx.audio.newSound(Gdx.files.internal("SoundEffects/MenuSelectSound.mp3"));
	private static Sound actionSound = Gdx.audio.newSound(Gdx.files.internal("SoundEffects/MenuActionSound.mp3"));
	
	private static Sound hitSound = Gdx.audio.newSound(Gdx.files.internal("SoundEffects/HitSound.mp3"));
	
	public static void playSelectionSound() {
		selectionSound.play(Options.getSoundVolumeFloat());
	}
	
	public static void playActionSound() {
		actionSound.play(Options.getSoundVolumeFloat());
	}
	
	public static void playHitSound() {
		hitSound.play(Options.getSoundVolumeFloat());
	}
	
	public static void dispose() {
		selectionSound.dispose();
		actionSound.dispose();
		hitSound.dispose();
	}
}
