/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;



import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private final int gameboard = 10;

    private Game game = new Game(gameboard);

    @Test
    public void fleetsHaveRightAmountOfShips() {
        game.addRandomFleets();
        assertEquals(5, game.getAI().fleetSize());
        assertEquals(5, game.getPlayer().fleetSize());
    }
    @Test
    public void guiConstructorTest(){
        GraphicalUI gui = new GraphicalUI();
        game = new Game(gameboard, gui);
        assertEquals("Unnamed Player",game.getPlayer().getName());
    }

    @Test
    public void gameEndsWhenAIFleetDestroyed() {
        game.getAI().addShip(new Ship(new Location(1, 1)));
        game.playerShoot(new Location(1, 1));
        assertEquals(1,game.getPlayer().getShotLocs().size());
        assertEquals(200, game.getPlayer().getScore());
        assertEquals(true, game.getEndgame());
        assertEquals("Unnamed Player",game.getWinner());
        assertEquals(3,game.getPlayerRank());
    }

    @Test
    public void gameEndsWhenAIFleetDestroyedTwo() {
        
        game.getAI().addShip(new Ship(new Location(1, 1)));
        game.getPlayer().addShip(new Ship(new Location(-1,2)));
        game.playerShoot(new Location(0, 1));

        game.playerShoot(new Location(1, 1));
        
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
    public void gameEndsIfAIWins(){
        Game game2 = new Game(1);
        game2.getPlayer().addShip(new Ship(new Location(0,0)));
        game2.aiShoot();
        assertEquals(true,game2.getEndgame());
        
    }

}
