/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.io.Serializable;

/**
 *
 * @author ile
 */
public class HighScore implements Serializable, Comparable<HighScore> {

    private String name;
    private int score;

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
