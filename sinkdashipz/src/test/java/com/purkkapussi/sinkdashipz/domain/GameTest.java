/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;


import com.purkkapussi.sinkdashipz.users.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private final int gameboard = 10;
    private final Game game = new Game(gameboard);

    @Test
    public void fleetsHaveRightAmountOfShips() {
        game.addRandomFleets();
        assertEquals(5, game.getAI().fleetSize());
        assertEquals(5, game.getPlayer().fleetSize());
    }
    

    @Test
    public void gameEndsWhenAIFleetDestroyed() {
        game.getAI().addShip(new Ship(new Location(1, 1)));
        game.setPlayerTargetLoc(new Location(1, 1));
        game.playerShoot();
        assertEquals(200, game.getPlayer().getScore());
        assertEquals(true, game.getEndgame());
    }

    @Test
    public void gameEndsWhenAIFleetDestroyedTwo() {
        
        game.getAI().addShip(new Ship(new Location(1, 1)));
        game.getPlayer().addShip(new Ship(new Location(-1,2)));
        game.setPlayerTargetLoc(new Location(0, 1));
        game.playerShoot();
        assertEquals(1,game.getAiShootLocs().size());
        game.setPlayerTargetLoc(new Location(1, 1));
        game.playerShoot();
        
        assertEquals(180, game.getPlayer().getScore());
        assertEquals(true, game.getEndgame());
    }


    @Test
    public void endGameTest() {

        game.endgame();
        assertEquals(true, game.getEndgame());
    }

    @Test
    public void startGameTest() {
        game.startGame();
        assertEquals(5, game.getAI().fleetSize());
    }

    @Test
    public void testAnotherConstructor() {
        Game game2 = new Game(new AI(), new Player(), gameboard);
        assertEquals(0, game2.getAI().fleetSize());
        game2.startGame();
        assertEquals(5, game2.getAI().fleetSize());
    }
    @Test
    public void gameEndsIfAIWins(){
        Game game2 = new Game(1);
        game2.getPlayer().addShip(new Ship(new Location(0,0)));
        game2.aiShoot();
        assertEquals(true,game2.getEndgame());
        
    }

}
