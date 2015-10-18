/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to handle reading and writing and printing highs cores using the writer
 * and reader classes.
 *
 */
public class HighScoreHandler {
    
    private final String filename = "highscores.ser";

    private final HighScoreReader reader = new HighScoreReader(filename);
    private final HighScoreWriter writer = new HighScoreWriter(filename);
    private final ArrayList<HighScore> highscores;

    /**
     * Constructor that tries to read highs cores using the reader class.
     */
    public HighScoreHandler() {
        highscores = reader.readHighScores();
    }

    /**
     * Constructor with high score list as parameter. Used for testing.
     *
     * @param highscores ArrayList of HighScores
     */
    public HighScoreHandler(ArrayList<HighScore> highscores) {
        this.highscores = highscores;
    }

    /**
     * Adds a new high score to the high score list, sorts the list and writes
     * to file using the writer class. Returns the rank of the player compared
     * to other high scores.
     *
     * @param name Players name
     * @param points Players score
     * @return rank of the player as Integer
     */
    public int addHighScore(String name, int points) {
        HighScore hscore = new HighScore(name, points);
        highscores.add(hscore);
        Collections.sort(highscores);
        writer.writeHighScores(highscores);
        return highscores.indexOf(hscore) + 1;
    }

    /**
     * Method wipes the high score list and writes the (empty) list into file.
     */
    public void resetHighScores() {
        if (highscores != null) {
            highscores.clear();
        }
        writer.writeHighScores(new ArrayList<HighScore>());
    }

    /**
     * Method returns a string consisting of the top ten high scores preceeded
     * by rank and separated by line change.
     *
     * @return String of high scores in ranked order separated by line change.
     */
    public String tenBestHighScores() {
        String tenScores = "";
        int limit = 10;
        if (highscores == null) {
            return tenScores;
        }
        if (highscores.isEmpty()) {
            return tenScores;
        }

        if (highscores.size() < 10) {
            limit = highscores.size();
        }
        for (int i = 1; i <= limit; i++) {
            tenScores += i + ". " + highscores.get(i - 1).toString() + "\n";
        }
        return tenScores;
    }
}
