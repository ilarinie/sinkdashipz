/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.domain.highscores.HighScore;
import com.purkkapussi.sinkdashipz.domain.highscores.HighScoreWriter;
import com.purkkapussi.sinkdashipz.domain.highscores.HighScoreReader;
import com.purkkapussi.sinkdashipz.users.AI;
import com.purkkapussi.sinkdashipz.users.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Class contains the main game functionality, including tracking player and AI
 * progress and shooting.
 *
 * @author ile
 */
public class Game {

    //Main object variables
    private final int gameBoardSize;
    private final ShipCreator creator;
    private final AI ai;
    private final Player player;
    private GUI gui;

    //Runtime variables
    private Boolean endgame = false;
    private Location playerTargetLoc;
    private Location aiShootLoc;
    private final HashSet<Location> playerShootLocs;
    private final HashSet<Location> aiShootLocs;
    private HashSet<Location> initialAIShipLocs;
    private HashSet<Location> initialPlayerShipLocs;
    protected ArrayList<HighScore> highscores = new ArrayList<>();
    private String winner;
    private int playerRank;
    private int aiFleetSize;
    private int playerFleetSize;

    //CONSTRUCTORS
    /**
     * Main Game constructor. Initializes a new game with the given game board
     * size. Creates new AI and a Player objects. Tries to read high scores from
     * file using HighScoreReader if high score file is present.
     *
     * @see
     * com.purkkapussi.sinkdashipz.domain.highscores.HighScoreReader#readHighScores()
     *
     * @param gameboard size of the game board.
     */
    public Game(int gameboard) {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.gameBoardSize = gameboard;
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();

        readHighScores();
    }

    /**
     * Constructor used for testing.
     *
     * @param gameboard
     * @param gui
     */
    public Game(int gameboard, GUI gui) {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.gameBoardSize = gameboard;
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();
        this.gui = gui;
        readHighScores();
    }

    /**
     * Constructor used for testing.
     *
     * @param ai
     * @param player
     * @param gameboard
     */
    public Game(AI ai, Player player, int gameboard) {
        this.ai = ai;
        this.player = player;
        this.creator = new ShipCreator();
        this.gameBoardSize = gameboard;
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();
    }

    //GAME CONTROLS
    /**
     * Method starts a new game with the default rule set and creates randomized
     * fleets for both the AI and the Player.
     */
    public void startGame() {
        addRandomFleets();
        initialAIShipLocs = ai.shipLocs();
        initialPlayerShipLocs = player.shipLocs();
        playerFleetSize = player.fleetSize();
        aiFleetSize = ai.fleetSize();
    }

    /**
     * Method used to end the game. Method adds the players score to high
     * scores, writes the high scores to file and sets endgame variable to true;
     */
    public void endgame() {
        addPlayerHighScore();
        writeHighScores();
        this.endgame = true;
    }

    //FLEET CREATORS
    /**
     * Method creates randomized fleets for both the player and the AI.
     *
     * @see
     * com.purkkapussi.sinkdashipz.domain.ShipCreator#createRandomFleet(AI,int)
     */
    public void addRandomFleets() {
        creator.createRandomFleet(ai, gameBoardSize);
        creator.createRandomFleet(player, gameBoardSize);
    }

    //SHOOTING CONTROLS
    /**
     * Player shooting method. The method will fire at target location (defined
     * in the playerTargetLoc) variable. If a hit is scored a message is shown
     * and player added points accordingly. If the AI fleet is destroyed
     * completely, endgame method is called. If the player misses, points are
     * deducted and the aiShoot method is called.
     */
    public void playerShoot() {
        if (this.playerTargetLoc != null) {
            playerShootLocs.add(playerTargetLoc);
            if (ai.hit(playerTargetLoc)) {
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
                playerTargetLoc = null;
            } else {
                player.scoreMiss();
                playerTargetLoc = null;
                aiShoot();
            }
        }
    }

    /**
     * AI shooting method, called after player misses a shot. Method request a
     * location from the AI-class for every shot and will keep on firing until a
     * miss happens. If the player's fleet gets destroyed, endgame method is
     * called.
     */
    public void aiShoot() {
        aiShootLoc = ai.shoot(gameBoardSize, player);
        aiShootLocs.add(aiShootLoc);
        while (player.hit(aiShootLoc)) {
            if (gui != null) {
                if (player.fleetSize() < playerFleetSize) {
                    gui.showAISinkPlayerShipMessage();
                    playerFleetSize = player.fleetSize();
                }
            }
            if (player.fleetSize() == 0) {
                winner = ai.getName();
                endgame();
            }
            aiShootLoc = ai.shoot(gameBoardSize, player);
            aiShootLocs.add(aiShootLoc);
        }
    }

    //GETTERS AND SETTERS
    /**
     *
     * @return true if game has ended
     */
    public Boolean getEndgame() {
        return endgame;
    }

    /**
     *
     * @return Player object of the current game
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     *
     * @return AI object of the current game
     */
    public AI getAI() {
        return this.ai;
    }

    /**
     * Sets the players target to the given location.
     *
     * @param playerTargetLoc location the player is targeting
     */
    public void setPlayerTargetLoc(Location playerTargetLoc) {
        this.playerTargetLoc = playerTargetLoc;
    }

    /**
     *
     * @return the coordinates player is currently targeting at.
     */
    public Location getPlayerTargetLoc() {
        return playerTargetLoc;
    }

    /**
     *
     * @return HashSet of Locations the AI has shot at.
     */
    public HashSet<Location> getAiShootLocs() {
        return aiShootLocs;
    }

    /**
     *
     * @return HashSet of Locations the Player has shot at.
     */
    public HashSet<Location> getPlayerShootLocs() {
        return playerShootLocs;
    }

    /**
     *
     * @return HashSet of (initial) Locations of AI's Ships.
     */
    public HashSet<Location> getInitialAIShipLocs() {
        return initialAIShipLocs;
    }

    /**
     *
     * @return HashSet of (initial) Locations of Players Ships.
     */
    public HashSet<Location> getInitialPlayerShipLocs() {
        return initialPlayerShipLocs;
    }

    /**
     *
     * @return the name of the winner of the current game
     */
    public String getWinner() {
        return this.winner;
    }

    //HIGHSCORE METHODS 
    /**
     * Method returns player's rank versus those already in high scores
     *
     * @see com.purkkapussi.sinkdashipz.domain.Game#addPlayerHighScore()
     *
     * @return rank of the player versus previous high scores
     */
    public int getPlayerRank() {
        return this.playerRank;
    }

    /**
     * Adds Players score and name to high scores and sets playerRank variable
     * to indicate the rank of the player compared to previous high scores
     */
    public void addPlayerHighScore() {
        if (player.getName() == null) {
            highscores.add(new HighScore("seppo", 9));
        }
        HighScore playerHighScore = new HighScore(player.getName(), player.getScore());
        highscores.add(playerHighScore);
        Collections.sort(highscores);

        this.playerRank = highscores.indexOf(playerHighScore) + 1;

    }

    protected void readHighScores() {
        HighScoreReader reader = new HighScoreReader();
        highscores = reader.readHighScores();
        Collections.sort(highscores);
    }

    protected void writeHighScores() {
        Collections.sort(highscores);
        HighScoreWriter writer = new HighScoreWriter();
        writer.writeHighScores(highscores);
    }

    /**
     * Method returns a string consisting of the top 10 High Scores
     *
     * @return top ten high scores separated by line change
     */
    public String tenBestHighScores() {
        String tenScores = "";
        int limit = 10;
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
