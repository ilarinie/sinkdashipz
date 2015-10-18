/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.io.Serializable;

/**
 * HighScore class provides serializable high score objects that can be stored
 * in files to save high score progress.
 *
 * @author ile
 */
public class HighScore implements Serializable, Comparable<HighScore> {

    private final String name;
    private final int score;

    /**
     * HighScore constructor
     * 
     * @param name players name
     * @param score players score
     */
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.score;
    }

    @Override
    public int compareTo(HighScore o) {
        if (this == o) {
            return 0;
        }
        if (this.score == o.score) {
            return 0;
        }
        if (this.score > o.score) {
            return -1;
        } else {
            return 1;
        }
    }
}
