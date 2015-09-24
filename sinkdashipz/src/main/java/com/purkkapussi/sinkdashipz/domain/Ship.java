package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.GameBoard;
import com.purkkapussi.sinkdashipz.tools.Direction;
import java.util.ArrayList;
import java.util.Collections;

public class Ship {

    private ArrayList<Hull> hullPieces;
    private Direction direction;

    /**
     * Creates a new Ship with no Hull pieces.
     */
    public Ship() {
        this.hullPieces = new ArrayList<>();
    }

    /**
     * Creates a new Ship with the given Hull and the Direction SOUTH. Used for
     * in-game testing purposes.
     *
     * @param hull the Hull of the new ship
     */
    public Ship(Hull hull) {
        hullPieces = new ArrayList<>();
        hullPieces.add(hull);
        this.direction = Direction.SOUTH;
    }

    /**
     * Method checks if the Ship is out of bounds of the GameBoard given as
     * parameter.
     *
     * @param gameBoard GameBoard to be used
     *
     * @return true if ship is out of bounds, false if not
     */
    public boolean outOfBounds(GameBoard gameBoard) {
        if (hullPieces.isEmpty()) {
            return false;
        }
        for (Hull hull : hullPieces) {
            if (hull.getLocation().getX() < 0 || hull.getLocation().getX() > gameBoard.getWidth() - 1 || hull.getLocation().getY() < 0 || hull.getLocation().getY() > gameBoard.getWidth() - 1) {
                return true;
            }
        }
        return false;
    }
    /**
     * Method adds the given hull to the Ship.
     *
     * @param hull hull to be added.
     */
    public void addHull(Hull hull) {
        hullPieces.add(hull);
    }

    /**
     * Method adds the given ArrayList of Hulls on the Ship. Used for in-game
     * testing purposes.
     *
     * @param hulls Hull to add to the Ship
     */
    public void addHullList(ArrayList<Hull> hulls) {
        this.hullPieces.addAll(hulls);
        Collections.sort(this.hullPieces);
    }

    /**
     * Method removes the given Hull from the Ship.
     *
     * @param hull hull to remove
     */
    public void removeHull(Hull hull) {
        hullPieces.remove(hull);
    }

    //GETTERS & SETTERS
    /**
     * Method returns the size (amount of Hulls) of the Ship.
     *
     * @return size of the Ship (amount of Hulls)
     */
    public int getSize() {
        return hullPieces.size();
    }

    /**
     * Method returns the Hulls of the Ship.
     *
     * @return hulls of the Ship
     */
    public ArrayList<Hull> getHulls() {
        return this.hullPieces;
    }

    /**
     * Method returns the Direction value of the ship.
     *
     * @return Direction of the Ship
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the Direction of the Ship.
     *
     * @param direction Direction of the Ship
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //OVERRIDES
    /**
     * 
     * @return locations of every hull of the ship
     */
    @Override
    public String toString() {
        String text = "";
        for (Hull hull : hullPieces) {
            text = text + hull.toString();
        }
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    /**
     * If checking against another Ship, returns true if ANY of the Hull pieces
     * match.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ship other = (Ship) obj;

        if (this.getSize() == 1 && other.getSize() == 1) {
            if (this.getHulls().get(0) == other.getHulls().get(0)) {
                return true;
            }
        }

        for (Hull hull : this.hullPieces) {
            for (Hull otherHull : other.hullPieces) {
                if (hull.equals(otherHull)) {
                    return true;
                }
            }
        }

        return false;
    }

}
