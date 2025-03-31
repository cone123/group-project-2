package com.example.groupproject_2.Classes;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class MusicManager {
    private static MediaPlayer currentPlayer;
    private static boolean isMuted = false;

    public static void playMusic(String path, double volume, boolean loop) {
        stopMusic();
        Media media = new Media(Objects.requireNonNull(MusicManager.class.getResource(path)).toExternalForm());
        currentPlayer = new MediaPlayer(media);
        currentPlayer.setVolume(volume);
        currentPlayer.setMute(isMuted);
        if (loop) currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        currentPlayer.play();
    }

    public static void stopMusic() {
        if (currentPlayer != null) {
            currentPlayer.stop();
        }
    }

    public static void toggleMute() {
        isMuted = !isMuted;
        if (currentPlayer != null) {
            currentPlayer.setMute(isMuted);
        }
    }

    public static boolean isMuted() {
        return isMuted;
    }

    public static void setVolume(double volume) {
        if (currentPlayer != null) {
            currentPlayer.setVolume(volume);
        }
    }
}
