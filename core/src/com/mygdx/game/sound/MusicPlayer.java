package com.mygdx.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.mygdx.game.data.Options;

    public class MusicPlayer{

    private static Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
    private static Music fightMusic1 = Gdx.audio.newMusic(Gdx.files.internal("FightMusic1.mp3"));
    private static Music fightMusic2 = Gdx.audio.newMusic(Gdx.files.internal("FightMusic2.mp3"));
    private static Music winningMelody = Gdx.audio.newMusic(Gdx.files.internal("WinningMelody.mp3"));
    
    private static Music currentMusic;

    public static void playMenuMusic(){
    	playMusicLoop(menuMusic);
    }

    public static void playFightMusic(){
        double random = Math.random();
        if (random > 0.5){
        	playMusicLoop(fightMusic1);
        }
        else {
        	playMusicLoop(fightMusic2);
        }
    }
    
    public static void playWinningMelody(){
    	playMusicOnce(winningMelody);
    }
    
    private static void playMusicLoop(Music musicToPlay) {
		playMusic(musicToPlay);
    	currentMusic.setLooping(true);
    }
    
    private static void playMusicOnce(Music musicToPlay) {
		playMusic(musicToPlay);
    	currentMusic.setLooping(false);
    }
    
    private static void playMusic(Music musicToPlay) {
    	if (currentMusic != musicToPlay) {
    		
    		if (currentMusic != null) {
    			currentMusic.stop();
    		}
    		
	    	currentMusic = musicToPlay;
	    	currentMusic.setLooping(true);
	    	currentMusic.setVolume(Options.getMusicVolumeFloat());
	    	currentMusic.play();
    	}
    }
    
    public static void addOnCompletionListener(OnCompletionListener listener) {
    	currentMusic.setOnCompletionListener(listener);
    }

    public static void  setVolume(float Volume){
        currentMusic.setVolume(Volume);
    }
    
    public static void dispose() {
    	menuMusic.dispose();
    	fightMusic1.dispose();
    	fightMusic2.dispose();
    	winningMelody.dispose();
    }

}