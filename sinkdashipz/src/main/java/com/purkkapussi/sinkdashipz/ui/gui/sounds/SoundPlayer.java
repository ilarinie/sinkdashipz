/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.sounds;

import java.io.InputStream;
import java.net.URL;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author ile
 */
public class SoundPlayer {

    public SoundPlayer() {

    }

    public void playHitSound() {
        URL url = this.getClass().getResource("/sounds/hitSound.wav");
        playSound(url);
    }

    public void playDestroySound() {
        URL url = this.getClass().getResource("/sounds/shipDestroySound.wav");
        playSound(url);
    }

    public void playAIDestroySound() {
        URL url = this.getClass().getResource("/sounds/badSound.wav");
        playSound(url);
    }

    public void playMissSound() {
        URL url = this.getClass().getResource("/sounds/missSound.wav");
        playSound(url);
    }

    public void playVictorySound() {
        URL url = this.getClass().getResource("/sounds/victorySound.wav");
        playSound(url);
    }

    public void playLosingSound() {
        URL url = this.getClass().getResource("/sounds/losingSound.wav");
        playSound(url);
    }

    public void playSound(URL url) {
        try {
            InputStream in = url.openStream();
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);

        } catch (Exception e) {
            System.out.println("Error reading sound file.");
        }

    }

}
