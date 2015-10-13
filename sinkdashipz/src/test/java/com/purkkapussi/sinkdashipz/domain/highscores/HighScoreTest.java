/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class HighScoreTest {
    
    private HighScore highscore = new HighScore("matti",200);
    private HighScore highscore1 = new HighScore("keke",200);
    private HighScore highscore2 = new HighScore("pekka",201);
     private HighScore highscore3 = new HighScore("pekka",199);
    
    
    @Test
    public void toStringTest(){
        assertEquals("matti - 200",highscore.toString());
    }
    @Test
    public void compareTest(){
        assertEquals(1,highscore.compareTo(highscore2));
        assertEquals(0,highscore.compareTo(highscore1));
        assertEquals(-1,highscore.compareTo(highscore3));
        assertEquals(0,highscore.compareTo(highscore));
    }
    
    
}
