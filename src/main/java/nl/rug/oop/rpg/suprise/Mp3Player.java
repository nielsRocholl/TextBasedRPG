package nl.rug.oop.rpg.suprise;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * A class for the sound effects
 */
public class Mp3Player {

    public Mp3Player(String songPath){
        playMusic(songPath);
    }

    /**
     * Plays music
     * @param songPath The path of the .wav file
     */
    public void playMusic(String songPath) {
        try {
            File musicPath = new File("suprise" + File.separator + songPath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("cant find the file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
