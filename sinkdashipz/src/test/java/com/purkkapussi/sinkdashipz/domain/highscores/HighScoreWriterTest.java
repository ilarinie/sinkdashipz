/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class HighScoreWriterTest {
    
    private final String filename = "highscores.ser";
    
    private ArrayList<HighScore> highscores = new ArrayList<>();
    private final HighScoreWriter writer = new HighScoreWriter(filename);
    private final HighScoreReader reader = new HighScoreReader(filename);
    
    @Before
    public void addHighScores(){
        highscores.add(new HighScore("seppo",200));
        highscores.add(new HighScore("matti",400));
    }
    
    @Test
    public void writeToFile(){
        writer.writeHighScores(highscores);
        
         highscores.add(new HighScore("asdasd",400));
        
        highscores = reader.readHighScores();
        
        assertEquals(2,highscores.size());
    }
    
    
    
}
