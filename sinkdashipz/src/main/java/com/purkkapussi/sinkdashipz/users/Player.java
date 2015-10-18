/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Location;
import com.purkkapussi.sinkdashipz.domain.Ship;
import java.util.ArrayList;

/**
 * Player class provides human player specific functionality for the Actor
 * Class.
 */
public class Player extends Actor {

    private final ArrayList<Ship> ships;
    private int score;
    private ArrayList<Location> shotLocs;

    /**
     * Constructs a new Player object with specified name
     *
     * @param nick nickname for the human player
     */
    public Player(String nick) {
        super.setName(nick);
        this.ships = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Constructs a new Player with a default name "unnamed player"
     */
    public Player() {
        super.setName("Unnamed Player");
        this.ships = new ArrayList<>();
        shotLocs = new ArrayList<>();
    }

    /**
     * Method adds 200 points to the players score
     */
    public void scoreHit() {
        this.score = this.score + 200;
    }

    /**
     * Method deducts 20 points from the players score.
     */
    public void scoreMiss() {
        this.score = this.score - 20;
    }

    public int getScore() {
        return this.score;
    }

    public ArrayList<Location> getShotLocs() {
        return shotLocs;
    }

    /**
     * Add the given location to players list of previous shots.
     *
     * @param loc Location to be added
     */
    public void addShotLoc(Location loc) {
        shotLocs.add(loc);
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            super.setName("Unnamed Player");
        } else {
            if (name.length() > 8) {
                name = name.substring(0, 8);
                name = name.concat("...");
                super.setName(name);
            } else {
                super.setName(name);
            }
        }
    }

}
