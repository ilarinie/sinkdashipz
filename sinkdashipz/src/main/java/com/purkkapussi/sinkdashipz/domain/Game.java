/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.GameBoard;
import com.purkkapussi.sinkdashipz.tools.Location;
import com.purkkapussi.sinkdashipz.users.AI;
import com.purkkapussi.sinkdashipz.users.Player;
import java.util.HashSet;

/**
 *
 * @author ile
 */
public class Game {

    //Main object variables
    private final GameBoard gameboard;
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

    //CONSTRUCTORS
    public Game(GameBoard gameboard) {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.gameboard = gameboard;
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();
    }

    public Game(AI ai, Player player, GameBoard gameboard) {
        this.ai = ai;
        this.player = player;
        this.creator = new ShipCreator();
        this.gameboard = gameboard;
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
        this.endgame = true;
    }

    //FLEET CREATORS
    public void addRandomFleets() {
        creator.createRandomFleet(ai, gameboard);
        creator.createRandomFleet(player, gameboard);
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

        aiShootLoc = ai.shoot(gameboard.getWidth(), player);
        aiShootLocs.add(aiShootLoc);
        while (player.hit(aiShootLoc)) {
            if (player.fleetSize() == 0) {
                endgame();
            }
            aiShootLoc = ai.shoot(gameboard.getWidth(), player);
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
        return this.gameboard.getWidth();
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

}
