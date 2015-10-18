/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class HighScoreHandlerTest {

    private ArrayList<HighScore> highscores = new ArrayList<>();
    private HighScoreHandler handler;

    @Test
    public void testPrintingEmptyList() {
        handler = new HighScoreHandler(highscores);
        assertEquals("", handler.tenBestHighScores());
    }

    @Test
    public void testPrintingSingleHighScore() {
        highscores.add(new HighScore("Seppo", 200));
        handler = new HighScoreHandler(highscores);
        assertEquals("1. Seppo - 200\n", handler.tenBestHighScores());

    }

    @Test
    public void onlyTenScoresArePrinted() {
        for (int i = 0; i < 10; i++) {
            highscores.add(new HighScore("Seppo", 200));
        }
        handler = new HighScoreHandler(highscores);
        String[] lines = handler.tenBestHighScores().split("\r\n|\r|\n");
        assertEquals(10, lines.length);
    }

    @Test
    public void resetHighScoreTest() {
        HighScore matti = new HighScore("Matti", 200);
        handler = new HighScoreHandler();
        handler.resetHighScores();
        assertEquals("", handler.tenBestHighScores());
        highscores.add(matti);
        assertEquals(1,handler.addHighScore("pekka", 300));
    }
}
