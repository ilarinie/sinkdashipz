/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.UI.textUI.TextBasedUi;
import com.purkkapussi.sinkdashipz.tools.Location;
import com.purkkapussi.sinkdashipz.tools.ShipCreator;
import com.purkkapussi.sinkdashipz.users.AI;
import com.purkkapussi.sinkdashipz.users.Player;
import java.util.ArrayList;

/**
 *
 * @author ile
 */
public class Game {

    private GameBoard gameboard;
    private ShipCreator creator;
    private AI ai;
    private Player player;
    private TextBasedUi ui;

    private ArrayList<Location> hitList = new ArrayList<>();

    public Game(GameBoard gameboard) {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.ui = new TextBasedUi();
        this.gameboard = gameboard;
    }
    
    public Game(AI ai, Player player,GameBoard gameboard){
        this.ai = ai;
        this.player = player;
        this.creator = new ShipCreator();
        this.ui = new TextBasedUi();
        this.gameboard = gameboard;
    }
    
    public void addRandomFleets(int size){
        creator.createRandomFleet(ai, size, gameboard);
        creator.createRandomFleet(player, size, gameboard);
    }

    public void startGame() {

        ui.showScore(player.fleetSize(), ai.fleetSize());

        boolean endgame = false;

        while (player.fleetSize() != 0 && ai.fleetSize() != 0) {
            ui.showScore(player.fleetSize(), ai.fleetSize());

            Location location = ui.shoot();

            while (ai.hit(location)) {
                ui.hit();
                hitList.add(location);
                if (ai.fleetSize() == 0) {
                    ui.youWon();
                    endgame = true;
                    break;
                }
                location = ui.shoot();
            }

            
            location = new Location(gameboard);
            while (player.hit(location)) {
                ui.aiHit();
                if (player.fleetSize() == 0) {
                    ui.youLost();
                    endgame = true;
                    break;
                }
                location = new Location(gameboard);
            }

            if (!endgame) {
                ui.youMissed();
                
                ui.aiMissed(location);

                ui.printFleet(ai);
            }
        }

    }

}
