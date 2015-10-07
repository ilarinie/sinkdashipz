/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.domain.highscores.HighScore;
import com.purkkapussi.sinkdashipz.domain.highscores.HighScoreWriter;
import com.purkkapussi.sinkdashipz.domain.highscores.HighScoreReader;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import com.purkkapussi.sinkdashipz.users.AI;
import com.purkkapussi.sinkdashipz.users.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author ile
 */
public class Game {

    //Main object variables
    private final int gameBoardSize;
    private ShipCreator creator;
    private AI ai;
    private Player player;

    //Runtime variables
    private Boolean endgame;
    private Location playerTargetLoc;
    private Location aiShootLoc;
    private HashSet<Location> playerShootLocs;
    private HashSet<Location> aiShootLocs;
    private HashSet<Location> initialAIShipLocs;
    private HashSet<Location> initialPlayerShipLocs;
    private ArrayList<HighScore> highscores = new ArrayList<>();

    //CONSTRUCTORS
    public Game(int gameboard) {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.gameBoardSize = gameboard;
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();
        readHighScores();
    }

    public Game(AI ai, Player player, int gameboard) {
        this.ai = ai;
        this.player = player;
        this.creator = new ShipCreator();
        this.gameBoardSize = gameboard;
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();
    }

    //GAME CONTROLS
    public void startGame() {
        addRandomFleets();
        initialAIShipLocs = ai.shipLocs();
        initialPlayerShipLocs = player.shipLocs();
    }

    public void resetGame() {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();
        initialAIShipLocs = ai.shipLocs();
        initialPlayerShipLocs = player.shipLocs();
        endgame = false;
    }

    public void endgame() {
        addPlayerHighScore();
        writeHighScores();
        this.endgame = true;
    }

    //FLEET CREATORS
    public void addRandomFleets() {
        creator.createRandomFleet(ai, gameBoardSize);
        creator.createRandomFleet(player, gameBoardSize);
    }

    //SHOOTING CONTROLS
    public void playerShoot() {
        if (this.playerTargetLoc != null) {
            playerShootLocs.add(playerTargetLoc);
            if (ai.hit(playerTargetLoc)) {
                player.scoreHit();
                if (ai.fleetSize() == 0) {
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

    public void aiShoot() {

        aiShootLoc = ai.shoot(gameBoardSize, player);
        aiShootLocs.add(aiShootLoc);
        while (player.hit(aiShootLoc)) {
            if (player.fleetSize() == 0) {
                endgame();
            }
            aiShootLoc = ai.shoot(gameBoardSize, player);
            aiShootLocs.add(aiShootLoc);
        }

    }

    //GETTERS AND SETTERS
    public Boolean getEndgame() {
        return endgame;
    }

    public Player getPlayer() {
        return this.player;
    }

    public AI getAI() {
        return this.ai;
    }

    public int getGameBoardSize() {
        return this.gameBoardSize;
    }

    public void setPlayerTargetLoc(Location playerTargetLoc) {
        this.playerTargetLoc = playerTargetLoc;
    }

    public Location getPlayerTargetLoc() {
        return playerTargetLoc;
    }

    public HashSet<Location> getAiShootLocs() {
        return aiShootLocs;
    }

    public HashSet<Location> getPlayerShootLocs() {
        return playerShootLocs;
    }

    public HashSet<Location> getInitialAIShipLocs() {
        return initialAIShipLocs;
    }

    public HashSet<Location> getInitialPlayerShipLocs() {
        return initialPlayerShipLocs;
    }

    public void setPlayerName(String name) {
        player.setName(name);
    }

    public void setDifficulty(int input) {
        this.ai.setDifficulty(Difficulty.values()[input]);
    }

    private void readHighScores() {
        HighScoreReader reader = new HighScoreReader();
        highscores = reader.readHighScores();
        Collections.sort(highscores);
    }
    
    public void addPlayerHighScore(){
       if(player.getName() == null){
           highscores.add(new HighScore("seppo",9));
       } 
       
        highscores.add(new HighScore(player.getName(),player.getScore()));
        
    }
    private void writeHighScores(){
        Collections.sort(highscores);
        HighScoreWriter writer = new HighScoreWriter();
        writer.writeHighScores(highscores);
    }
    
    public String tenBestHighScores(){
        String tenScores = "";
        int limit = 10;
        if (highscores.isEmpty()){
            return tenScores;
        }
        if (highscores.size() < 10){
            limit = highscores.size();
        }
        
        
        for (int i= 0; i<limit ; i++){
            tenScores += highscores.get(i).toString() + "\n";
        }
        return tenScores;
    }
    
}
