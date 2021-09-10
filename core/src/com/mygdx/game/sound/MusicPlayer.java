package com.mygdx.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.data.Options;




    public class MusicPlayer{


    private static Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
    private static Music fightMusic1 = Gdx.audio.newMusic(Gdx.files.internal("FightMusic1.mp3"));
    private static Music fightMusic2 = Gdx.audio.newMusic(Gdx.files.internal("FightMusic2.mp3"));
    private static Music currentMusic;

    public static void playMenuMusic(){
    	playMusic(menuMusic);
    }

    public static void playFightMusic(){
        double random = Math.random();
        if (random > 0.5){
        	playMusic(fightMusic1);
        }
        else {
        	playMusic(fightMusic2);
        }


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

    public static void  setVolume(float Volume){
        currentMusic.setVolume(Volume);
    }

}