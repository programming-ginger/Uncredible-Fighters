package com.mygdx.game.sound;
package playmusic;
public class MusicPlayer {



    public class PlayMusic {


        public static void main(String[] args){

            playMusicMenu("Hier Dateipfad");


        }

        public static void playMusicMenu(String filepath)
        {
            inputStream music;
            try
            {
                music = new FileInputStream(new File(filepath));
                AudioStream audios = new AudioStream(music);
                AudioPlayer.player.start(audio);
            }
            catch(Exception e)
            {
                jOptionPane.showMessageDialog(null,"Error");
            }
        }


        public static void playMusicFight(String filepath)
        {
            inputStream music;
            try
            {
                music = new FileInputStream(new File(filepath));
                AudioStream audios = new AudioStream(music);
                AudioPlayer.player.start(audio);
            }
            catch(Exception e)
            {
                jOptionPane.showMessageDialog(null,"Error");
            }
        }

}
