/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.UI.textUI.TextBasedUi;
import com.purkkapussi.sinkdashipz.tools.Location;
import com.purkkapussi.sinkdashipz.users.AI;
import com.purkkapussi.sinkdashipz.users.Player;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    private TextBasedUi ui;

    //Runtime variables
    private Boolean endgame;
    private Location playerShootLoc;
    private Location aiShootLoc;
    private HashSet<Location> playerShootLocs;
    private HashSet<Location> aiShootLocs;
    private HashSet<Location> initialAIShipLocs;
    private HashSet<Location> initialPlayerShipLocs;

    private ArrayList<Location> hitList = new ArrayList<>();

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
        this.ui = new TextBasedUi();
        this.gameboard = gameboard;
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();

    }

    public void addRandomFleets() {
        creator.createRandomFleet(ai, gameboard);
        creator.createRandomFleet(player, gameboard);
    }

    public void startGame() {
        addRandomFleets();
        initialAIShipLocs = ai.shipLocs();
        initialPlayerShipLocs = player.shipLocs();
        System.out.println(ai);
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

    public void setPlayerShootLoc(Location playerShootLoc) {
        this.playerShootLoc = playerShootLoc;
    }

    public void setAiShootLoc(Location aiShootLoc) {
        this.aiShootLoc = aiShootLoc;
    }

    public Location getPlayerShootLoc() {
        return playerShootLoc;
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

    public boolean isTherePlayerShip(int x, int y) {
        Ship tester = new Ship(new Hull(x, y));
        return player.getShips().contains(tester);
    }

    public void playerShoot() {
        if (this.playerShootLoc != null) {
            playerShootLocs.add(playerShootLoc);
            if (ai.hit(playerShootLoc)) {
                player.scoreHit();
                if (ai.fleetSize() == 0) {
                    endgame();
                }
                playerShootLoc = null;
            } else {
                player.scoreMiss();
                playerShootLoc = null;
                aiShoot();
            }
        }
    }

    private void aiShoot() {
        aiShootLoc = ai.shoot(gameboard, player);
        aiShootLocs.add(aiShootLoc);
        while (player.hit(aiShootLoc)) {
            if (player.fleetSize() == 0) {
                endgame();
            }
            aiShootLoc = ai.shoot(gameboard, player);
            aiShootLocs.add(aiShootLoc);
        }

    }

    private void endgame() {
        System.out.println("The game has ended.");
        this.endgame = true;
    }

    public void resetGame() {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.playerShootLocs = new HashSet<>();
        this.aiShootLocs = new HashSet<>();
        initialAIShipLocs = ai.shipLocs();
        initialPlayerShipLocs = player.shipLocs();
    }

    public Boolean getEndgame() {
        return endgame;
    }

    /*public void startTextGame() {

     ui.showScore(player.fleetSize(), ai.fleetSize());

     boolean endgame = false;

     while (player.fleetSize() != 0 && ai.fleetSize() != 0) {
     ui.showScore(player.fleetSize(), ai.fleetSize());

     Location location = ui.shoot();      //pyydetään pelaajalta ampumiskoordinaatit

     while (ai.hit(location)) {          //ammutaan niin kauan kun tulee osumia
     player.lastHit(location);
     ui.hit();
     hitList.add(location);
     if (ai.fleetSize() == 0) {      // jos tekoälyn laivat loppuvat, peli loppuu
     ui.youWon();
     endgame = true;
     break;
     }
     location = ui.shoot();
     }
     player.lastMiss(location);

     location = new Location(gameboard); //luo satunnaisen lokaation pelilaudalla
            
     try {
     while (player.hit(ai.shoot(gameboard, player))) {      //tekoäly ampuu niin kauan kun osuu, satunnaisiin 
     ui.aiHit();
     if (player.fleetSize() == 0) {
     ui.youLost();
     endgame = true;
     break;
     }
     location = new Location(gameboard);
     }
     }
     catch (IllegalArgumentException e){
     endgame = true;
     }

     if (!endgame) {
     ui.youMissed();
     ui.aiMissed(location);
     ui.printFleet(ai);
     }
     }

     }*/
}
