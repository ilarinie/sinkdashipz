/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Game;
import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.util.ArrayList;

public class AI extends Actor {

    private ArrayList<Ship> laivat;
    private Difficulty difficulty;
    private boolean lastHitSuccess;
    private Location lastHitLoc;

    public AI(Difficulty difficulty) {
        laivat = new ArrayList<>();
        super.setName("AI");
    }

    public AI() {
        laivat = new ArrayList<>();
        super.setName(difficulty + " - AI");
    }

    public Location shoot(GameBoard gameBoard, Actor actor) throws IllegalArgumentException {
        if (actor.getShips().isEmpty()) {
            throw new IllegalArgumentException("No ships to shoot at");
        }
        if (this.difficulty == Difficulty.BRAINLESS || !lastHitSuccess) {
            return new Location(gameBoard);
        }
        if (this.difficulty == Difficulty.EASY) {
           return new Location(gameBoard);
        }
        if (this.difficulty == Difficulty.CAPABLE) {
            return new Location(gameBoard);
        } else {
            return jesusShoot(actor);
        }

    }

    private Location easyShoot(Actor actor)  {
        return new Location(1,1);
    }

    private Location capableShoot(Actor actor)  {
        return new Location(1,1);
    }

    private Location jesusShoot(Actor actor) {
       return actor.getShips().get(0).getHulls().get(0).getLocation();
    }

}
