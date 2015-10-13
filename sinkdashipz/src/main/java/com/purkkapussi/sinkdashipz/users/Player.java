/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Location;
import com.purkkapussi.sinkdashipz.domain.Ship;
import java.util.ArrayList;
import java.util.List;

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

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void scoreHit() {
        this.score = this.score + 200;
    }

    public void scoreMiss() {
        this.score = this.score - 20;
    }

    public ArrayList<Location> getShotLocs() {
        return shotLocs;
    }

    public void addShotLoc(Location loc) {
        shotLocs.add(loc);
    }

    @Override
    public void setName(String name) {
        if (name == null) {
            super.setName("Unnamed Player");
        } else if (name.isEmpty()) {
            super.setName("Unnamed Player");
        } else {
            super.setName(name);
        }
    }

}
