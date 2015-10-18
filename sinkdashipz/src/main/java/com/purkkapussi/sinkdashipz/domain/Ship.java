package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.Direction;
import java.util.ArrayList;

/**
 * The Ship class stores the Location of the ship's hulls and the direction of
 * the ship on the game board. The class also provides functionality to check
 * whether the ship is fully on the game board.
 *
 * @author ile
 */
public class Ship {

    private final ArrayList<Location> hullPieces;
    private Direction direction;

    /**
     * Creates a new Ship with no pieces.
     */
    public Ship() {
        this.hullPieces = new ArrayList<>();
    }

    /**
     * Creates a new Ship with the given Location as a starting piece and the
     * Direction SOUTH. Used for in-game testing purposes.
     *
     * @param hull the Hull of the new ship
     */
    public Ship(Location hull) {
        hullPieces = new ArrayList<>();
        hullPieces.add(hull);
        this.direction = Direction.SOUTH;
    }

    /**
     * Method checks if the Ship is out of bounds of the GameBoard given as
     * parameter.
     *
     * @param gameBoardSize GameBoard to be used
     *
     * @return true if ship is out of bounds, false if not
     */
    public boolean outOfBounds(int gameBoardSize) {
        if (hullPieces.isEmpty()) {
            return false;
        }
        for (Location hull : hullPieces) {
            if (hull.getX() < 0 || hull.getX() > gameBoardSize - 1 || hull.getY() < 0 || hull.getY() > gameBoardSize - 1) {
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
    public void addHull(Location hull) {
        hullPieces.add(hull);
    }

    /**
     * Method adds the given ArrayList of Hulls on the Ship. Used for in-game
     * testing purposes.
     *
     * @param hulls Hull to add to the Ship
     */
    public void addHullList(ArrayList<Location> hulls) {
        this.hullPieces.addAll(hulls);
    }

    /**
     * Method removes the given Hull from the Ship.
     *
     * @param hull hull to remove
     */
    public void removeHull(Location hull) {
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
    public ArrayList<Location> getLocs() {
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
     * @return locations of every piece of the ship in human readable form
     */
    @Override
    public String toString() {
        String text = "";
        for (Location hull : hullPieces) {
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
     * @param obj Object to check against
     * @return true if ships overlap, false otherwise
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
            if (this.getLocs().get(0) == other.getLocs().get(0)) {
                return true;
            }
        }
        for (Location hull : this.hullPieces) {
            for (Location otherHull : other.hullPieces) {
                if (hull.equals(otherHull)) {
                    return true;
                }
            }
        }
        return false;
    }
}
