/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import com.purkkapussi.sinkdashipz.domain.highscores.HighScoreHandler;
import com.purkkapussi.sinkdashipz.users.AI;
import com.purkkapussi.sinkdashipz.users.Player;

/**
 * Class contains the main game functionality, including tracking player and AI
 * progress and shooting.
 *
 * @author ile
 */
public class Game {

    private final int gameBoardSize;
    private ShipCreator creator;
    private AI ai;
    private Player player;
    private GraphicalUI gui;
    private HighScoreHandler handler;
    private Boolean endgame = false;
    private String winner;
    private int playerRank;
    private int aiFleetSize;
    private int playerFleetSize;

    /**
     * Main Game constructor. Initializes a new game with the given game board
     * size.
     *
     * @param gameboard size of the game board
     * @param gui Graphical UI to be used.
     */
    public Game(int gameboard, GraphicalUI gui) {
        this.gameBoardSize = gameboard;
        this.gui = gui;
        initializeGame();
    }

    /**
     * Constructor used in testing.
     *
     * @param gameboard game board size
     * @param handler HighScoreHandler
     */
    public Game(int gameboard, HighScoreHandler handler) {
        this.gameBoardSize = gameboard;
        this.handler = handler;
        initializeGame();
    }

    private void initializeGame() {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        handler = new HighScoreHandler();
    }

    /**
     * Method starts a new game with the default rule set and creates randomized
     * fleets for both the AI and the Player.
     */
    public void startGame() {
        addRandomFleets();
        playerFleetSize = player.fleetSize();
        aiFleetSize = ai.fleetSize();
    }

    /**
     * Method used to end the game. Method adds the players score to high
     * scores, writes the high scores to file and sets endgame variable to true;
     */
    public void endgame() {
        this.playerRank = handler.addHighScore(player.getName(), player.getScore());
        this.endgame = true;
    }

    /**
     * Method creates randomized fleets for both the player and the AI.
     */
    public void addRandomFleets() {
        creator.createRandomFleet(ai, gameBoardSize);
        creator.createRandomFleet(player, gameBoardSize);
    }

    /**
     * Player shooting method. The method will fire at the given location. If a
     * hit is scored, appropriate message is shown and the player is given
     * points. If AI's fleet size hits 0, the game ends with the endgame()
     * method. If the player misses, appropriate message is shown and points
     * deducted and aiShoot() method called.
     *
     * @param loc Location to shoot at
     */
    public void playerShoot(Location loc) {
        player.addShotLoc(loc);
        if (ai.hit(loc)) {
            if (gui != null) {
                if (ai.fleetSize() < aiFleetSize) {
                    gui.showPlayerSinkAIShipMessage();
                    aiFleetSize = ai.fleetSize();
                } else {
                    gui.showPlayerHitMessage();
                }
            }
            player.scoreHit();
            if (ai.fleetSize() == 0) {
                winner = player.getName();
                endgame();
            }
        } else {
            if (gui != null) {
                gui.playMissSound();
            }
            player.scoreMiss();
            aiShoot();
        }
    }

    /**
     * AI shooting method, called after player misses a shot. Method request a
     * location from the AI-class for every shot and will keep on firing until a
     * miss happens. If the player's fleet gets destroyed, endgame method is
     * called.
     */
    public void aiShoot() {
        Location aiShotLoc = ai.shoot(gameBoardSize, player);
        while (player.hit(aiShotLoc)) {
            if (gui != null) {
                if (player.fleetSize() < playerFleetSize) {
                    if (player.fleetSize() != 0) {
                        gui.showAISinkPlayerShipMessage();
                    }
                    playerFleetSize = player.fleetSize();
                }
            }
            if (player.fleetSize() == 0) {
                winner = "AI";
                endgame();
            }
            aiShotLoc = ai.shoot(gameBoardSize, player);
        }
    }

    /**
     * @return true if game has ended
     */
    public Boolean getEndgame() {
        return endgame;
    }

    /**
     * @return Player object of the current game
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return AI object of the current game
     */
    public AI getAI() {
        return this.ai;
    }

    /**
     * @return the name of the winner of the current game
     */
    public String getWinner() {
        return this.winner;
    }

    /**
     * Method returns player's rank versus those already in high scores
     *
     * @return rank of the player versus previous high scores
     */
    public int getPlayerRank() {
        return this.playerRank;
    }

    /**
     * Method returns a string consisting of the top 10 High Scores
     *
     * @return top ten high scores separated by line change
     */
    public String tenBestHighScores() {
        return handler.tenBestHighScores();
    }
}
