/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class PlayerTest {
    
    Player player = new Player("tester");
    Player player2 = new Player();
    
    @Test
    public void nonamedPlayer(){
        assertEquals("Unnamed Player", player2.getName());
    }
    
    @Test
    public void setNameTest(){
        player.setName(null);
        assertEquals("Unnamed Player",player.getName());
        player = new Player();
        player.setName("");
        assertEquals("Unnamed Player",player.getName());
        player.setName("pleijeri");
        assertEquals("pleijeri",player.getName());
        player.setName("verylongnamethatdoesnotfit");
        assertEquals("verylong...", player.getName());
    }
    
    @Test
    public void nameTest(){
        assertEquals("tester", player.getName());
    }
    @Test
    public void pointTest(){
        player.getScore();
        assertEquals(0, player.getScore());
    }
    @Test
    public void scorePointTest(){
        player.scoreHit();
        player.scoreHit();
        player.scoreMiss();
        assertEquals(380, player.getScore());
    }
    
    
}
