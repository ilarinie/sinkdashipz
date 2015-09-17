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

    private ShipCreator creator;
    private AI ai;
    private Player player;
    private TextBasedUi ui;

    private ArrayList<Location> hitList = new ArrayList<>();

    public void initGame(GameBoard gameboard) {
        this.creator = new ShipCreator();
        this.ai = new AI();
        this.player = new Player();
        this.ui = new TextBasedUi();
    }

    public void startRandomizedGame(GameBoard gameboard) {

        initGame(gameboard);

        creator.createRandomFleet(ai, 9, gameboard);
        creator.createRandomFleet(player, 9, gameboard);

        int aiShipsLeft = ai.fleetSize();
        int playerShipsLeft = player.fleetSize();

        ui.showScore(player.fleetSize(), ai.fleetSize());

        while (player.fleetSize() != 0 && ai.fleetSize() != 0) {
            ui.showScore(player.fleetSize(), ai.fleetSize());

            Location location = ui.shoot();
            
            while (ai.hit(location)) {
                ui.hit();
                hitList.add(location);
                location = ui.shoot();
            }
            if (ai.fleetSize() == 0) {
                ui.youWon();
                break;
            }
            ui.youMissed();
            location = new Location(gameboard);
            while (player.hit(location)) {
                ui.aiHit();
                location = new Location(gameboard);
            }
            if (player.fleetSize() == 0) {
                ui.youLost();
                break;
            }
            ui.aiMissed(location);

        }

    }

}
