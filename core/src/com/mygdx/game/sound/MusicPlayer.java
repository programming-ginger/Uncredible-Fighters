package com.mygdx.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.data.Options;




    public class MusicPlayer{


    private static Music Menu = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
    private static Music Fight1 = Gdx.audio.newMusic(Gdx.files.internal("FightMusic1.mp3"));
    private static Music Fight2 = Gdx.audio.newMusic(Gdx.files.internal("FightMusic2.mp3"));
    private static Music CurrentMusic;

    public static void playMenuMusic(){

       CurrentMusic = Menu;
        Menu.setLooping(true);
        Menu.setVolume(Options.getMusicVolumeFloat());
        Menu.play();

    }

    public static void playFightMusic(){

        double random = Math.random();
        Music music;

        if (random > 0.5){

            CurrentMusic = Fight1;

        }
        else {
            CurrentMusic = Fight2;
        }

        CurrentMusic.setLooping(true);
        CurrentMusic.setVolume(Options.getMusicVolumeFloat());
        CurrentMusic.play();

    }

    public static void  setVolume(float Volume){

        CurrentMusic.setVolume(Volume);

    }

}