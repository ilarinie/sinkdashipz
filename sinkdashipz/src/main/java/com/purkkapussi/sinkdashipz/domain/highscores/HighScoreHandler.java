/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ile
 */
public class HighScoreHandler {

    private HighScoreReader reader = new HighScoreReader();
    private HighScoreWriter writer = new HighScoreWriter();
    private ArrayList<HighScore> highscores;

    public HighScoreHandler() {
        highscores = reader.readHighScores();
    }

    public int addHighScore(String name, int points) {
        HighScore hscore = new HighScore(name, points);
        highscores.add(hscore);
        Collections.sort(highscores);
        writer.writeHighScores(highscores);
        return highscores.indexOf(hscore) + 1;
    }

    public ArrayList<HighScore> getHighScores() {
        return highscores;
    }

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
