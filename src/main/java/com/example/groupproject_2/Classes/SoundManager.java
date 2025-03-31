package com.example.groupproject_2.Classes;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private static boolean isMuted = false;

    public static void playClickSound() {
        if (!isMuted) {
            try {
                AudioClip click = new AudioClip(SoundManager.class.getResource("/Audio/clickSound.mp3").toExternalForm());
                click.play();
            } catch (Exception e) {
                System.out.println("Could not play click sound: " + e.getMessage());
            }
        }
    }

    public static void toggleMute() {
        isMuted = !isMuted;
    }

    public static boolean isMuted() {
        return isMuted;
    }
}
