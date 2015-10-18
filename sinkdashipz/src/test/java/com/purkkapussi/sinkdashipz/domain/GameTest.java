/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;




import com.purkkapussi.sinkdashipz.domain.highscores.HighScore;
import com.purkkapussi.sinkdashipz.domain.highscores.HighScoreHandler;
import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private final int gameboard = 10;
    private HighScoreHandler handler = new HighScoreHandler(new ArrayList<HighScore>());
    private Game game = new Game(gameboard,handler);

    
    @Before
    public void resetHighScores(){
        handler.resetHighScores();
    }
    
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
        Game game2 = new Game(1,handler);
        game2.getPlayer().addShip(new Ship(new Location(0,0)));
        game2.getAI().addShip(new Ship(new Location(0,0)));
        game2.playerShoot(new Location(2,4));
        assertEquals(true,game2.getEndgame());
        assertEquals(1, game2.getPlayerRank());
    }

}
