/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import com.purkkapussi.sinkdashipz.domain.Location;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class AITest {

    AI ai = new AI();
    int gameBoardSize = 10;

    @Test
    public void initialNameTest() {
        assertEquals("BRAINLESS AI", ai.getName());
    }

    @Test
    public void difficultyChangeTest() {
        ai.setDifficulty(Difficulty.CAPABLE);
        assertEquals(Difficulty.CAPABLE, ai.getDifficulty());
        assertEquals("CAPABLE AI", ai.getName());
    }

    @Test
    public void difficultyChangeTestTwo() {
        ai.setDifficulty(Difficulty.LITERALLYJESUS);
        assertEquals(Difficulty.LITERALLYJESUS, ai.getDifficulty());
        assertEquals("LITERALLYJESUS AI", ai.getName());
    }

    @Test
    public void createAiWithDifficulty() {
        AI ai2 = new AI(Difficulty.EASY);
        assertEquals(Difficulty.EASY, ai2.getDifficulty());
        assertEquals("EASY AI", ai2.getName());
    }

    @Test
    public void shootOnlyInsideBoard() {
        ai.addShip(new Ship(new Location(1, 1)));
        assertEquals(false, ai.shoot(gameBoardSize, ai).locOutOfBounds(gameBoardSize));
        assertEquals(1, ai.getShotLocs().size());
        ai.setDifficulty(Difficulty.EASY);
        assertEquals(false, ai.shoot(gameBoardSize, ai).locOutOfBounds(gameBoardSize));
        ai.setDifficulty(Difficulty.CAPABLE);
        assertEquals(false, ai.shoot(gameBoardSize, ai).locOutOfBounds(gameBoardSize));
        ai.setDifficulty(Difficulty.LITERALLYJESUS);
        assertEquals(false, ai.shoot(gameBoardSize, ai).locOutOfBounds(gameBoardSize));
    }

    @Test
    public void shootWithoutDifficulty() {
        ai.addShip(new Ship(new Location(1, 1)));
        ai.setDifficulty(null);
        assertEquals(false, ai.shoot(gameBoardSize, ai).locOutOfBounds(gameBoardSize));
    }

    @Test
    public void triesToShootWhenNoTargets() {
        ai.setDifficulty(Difficulty.LITERALLYJESUS);
        assertEquals(false, ai.shoot(gameBoardSize, ai).locOutOfBounds(gameBoardSize));
    }

    @Test
    public void shotTargeterTest() {
        ai = new AI();
        ai.addShip(new Ship(new Location(-1, 1)));
        assertEquals(new Location(-1, 1), ai.shotTargeter(gameBoardSize, ai, 10, 11));
        assertEquals(new Location(-1, 1), ai.shotTargeter(gameBoardSize, ai, 0, 1));
        assertEquals(new Location(-1, 1), ai.shotTargeter(gameBoardSize, ai, 95, 96));
        assertThat(new Location(-1,1), not(ai.shotTargeter(gameBoardSize, ai, 95,95)));
    }

}
