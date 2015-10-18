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
 * Sound player class for the graphical UI.
 *
 * @author ile
 */
public class SoundPlayer {

    /**
     * Method plays a sound indicating a miss
     */
    public void playHitSound() {
        URL url = this.getClass().getResource("/sounds/hitSound.wav");
        playSound(url);
    }

    /**
     * Method plays a sound indicating a destroyed ship
     */
    public void playDestroySound() {
        URL url = this.getClass().getResource("/sounds/shipDestroySound.wav");
        playSound(url);
    }

    /**
     * Method plays a sound indicating that AI has destroyed players ship.
     */
    public void playAIDestroySound() {
        URL url = this.getClass().getResource("/sounds/badSound.wav");
        playSound(url);
    }

    /**
     * Method plays a sound indicating a miss.
     */
    public void playMissSound() {
        URL url = this.getClass().getResource("/sounds/missSound.wav");
        playSound(url);
    }
    /**
     * Method plays the victory fanfare.
     */
    public void playVictorySound() {
        URL url = this.getClass().getResource("/sounds/victorySound.wav");
        playSound(url);
    }
    /**
     * Method plays a sound indicating players loss.
     */
    public void playLosingSound() {
        URL url = this.getClass().getResource("/sounds/losingSound.wav");
        playSound(url);
    }

    private void playSound(URL url) {
        try {
            InputStream in = url.openStream();
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);

        } catch (Exception e) {
            System.out.println("Error reading sound file.");
        }

    }

}
