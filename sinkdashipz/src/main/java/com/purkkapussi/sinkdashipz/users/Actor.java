/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Superclass for players (human player and AI) Provides players name and ships.
 */
public class Actor {

    private ArrayList<Ship> ships;
    private String name;


    /**
     * Constructs a new Actor and creates a new ArrayList for it's ships.
     */
    public Actor() {
        this.ships = new ArrayList<>();
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

    /**
     * The method adds a ship to the actor.
     *
     * @param ship A Ship to add to the actor..
     */
    public void addShip(Ship ship) {
        ships.add(ship);
    }

    /**
     * Method constructs a String of players ships. Each ship prints to it's own
     * line as Location ([x, y]).
     *
     * @return Actors ships in human readable (location) form.
     */
    @Override
    public String toString() {
        String text = "";
        for (Ship ship : this.ships) {
            text = text + ship.toString() + "\n";
        }
        return text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method returns the size of the Actor's fleet
     *
     * @return size of the Actor's fleet
     */
    public int fleetSize() {
        return ships.size();
    }

    /**
     * Method removes the given Ship from Actor's fleet.
     *
     * @param ship Ship to remove
     */
    public void removeShip(Ship ship) {
        ships.remove(ship);
    }


    public HashSet<Location> shipLocs() {
        HashSet<Location> shipLocs = new HashSet<Location>();
        for (Ship ship : ships) {
            for (Location hull : ship.getHulls()) {
                shipLocs.add(hull);
            }
        }
        return shipLocs;
    }

    /**
     * Method receives a location to "shoot" at. Method goes trough the Actors
     * ships and tries to find hits by creating a dummy ship in the location
     * parameter. It then compares the dummy ship against the actors real ships
     * to determine a hit. If a hit is found, the method removes the Hull that
     * was hit from the affected Ship. If the Ship has no Hulls left, the method
     * removes the whole ship from the actor.
     *
     * @param location Location of the desired target coordinates.
     *
     * @return re true if there was a hit, false if there wasn't.
     *
     * @see Ship
     * @see Location
     * @see Hull
     */
    public boolean hit(Location location) {
        Ship ship = new Ship(new Location(location.getX(), location.getY()));  //Luo uuden laivan ampumalokaatioon vertailua varten

        boolean re = false;

        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).equals(ship)) {    // Ship luokan oliot ovat samat, jos niissä on yksikin runkopalikka samassa sijainnissa

                ships.get(i).removeHull(ship.getHulls().get(0));

                if (ships.get(i).getHulls().isEmpty()) {
                    ships.remove(i);
                }

                re = true;
            }
        }
        return re;
    }
}
