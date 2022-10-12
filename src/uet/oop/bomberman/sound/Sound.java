package uet.oop.bomberman.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sound {
    private String musicFile;
    private Media soundtrack;
    private MediaPlayer mediaPlayer;

    public Sound(String file) {
        musicFile = file;
        soundtrack = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(soundtrack);
    }


    public void play() {
        mediaPlayer.play();
    }
}
