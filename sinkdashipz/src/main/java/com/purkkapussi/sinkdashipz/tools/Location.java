package com.purkkapussi.sinkdashipz.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 * Class provides a standard object to represent coordinates on the game board.
 *
 * @author ile
 */
public class Location implements Comparable {

    private int x;
    private int y;

    /**
     * Constructs a new Location of the parameters
     *
     * @param x X-value of the new Location
     * @param y Y-value of the new Location
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a random new location on the given game board.
     *
     * @param gameBoard game board to create random Location on
     */
    public Location(GameBoard gameBoard) {
        Random rand = new Random();
        this.x = rand.nextInt(gameBoard.getWidth());
        this.y = rand.nextInt(gameBoard.getWidth());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Method receives a HashSet of Locations, moves each location once towards
     * the given direction
     *
     * @param locations locations to be moved
     * @param direction direction to move at
     * @return moved locations in a HashSet
     */
    public HashSet<Location> moveLocSet(HashSet<Location> locations, Direction direction) {

        HashSet<Location> movedLocations = new HashSet<>();

        if (direction == Direction.EAST) {
            for (Location loc : locations) {
                loc.moveEast();
                movedLocations.add(loc);
            }
        }
        if (direction == Direction.WEST) {
            for (Location loc : locations) {
                loc.moveWest();
                movedLocations.add(loc);
            }
        }
        if (direction == Direction.SOUTH) {
            for (Location loc : locations) {
                loc.moveSouth();
                movedLocations.add(loc);
            }
        }
        if (direction == Direction.NORTH) {
            for (Location loc : locations) {
                loc.moveNorth();
                movedLocations.add(loc);
            }
        }

        return movedLocations;
    }

    /**
     * Method moves the Location "east". Adds one to the x-coordinate.
     *
     * @return new Location after the repositioning.
     */
    public Location moveEast() {
        this.x = this.x + 1;
        return this;
    }

    /**
     * Method moves the Location "south". Removes one of the y-coordinate.
     *
     * @return new Location after the repositioning.
     */
    public Location moveSouth() {
        this.y = this.y - 1;
        return this;
    }

    /**
     * Method moves the Location "north". Adds one to the y-coordinate.
     *
     * @return new Location after the repositioning.
     */
    public Location moveNorth() {

        this.y = this.y + 1;
        return this;
    }

    /**
     * Method moves the Location "west". Adds one to the x-coordinate.
     *
     * @return new Location after the repositioning.
     */
    public Location moveWest() {

        this.x = this.x - 1;
        return this;
    }

    /**
     * Prints the Location in human readable form ( [x,y] ).
     *
     * @return Location in human readable form.
     */
    public String toString() {

        return "[" + this.x + "," + this.y + "]";
    }

    public String setToString(HashSet<Location> locations) {
        ArrayList<Location> sortedList = new ArrayList(locations);
        Collections.sort(sortedList);
        Location firstLoc = sortedList.get(0);
        return "";

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        final Location other = (Location) o;
        if (this.y > other.getY()) {
            return 1;
        }
        if (this.equals(o)) {
            return 0;
        }
        if (this.y < other.getY()) {
            return -1;
        }
        if (this.y == other.getY()) {
            if (this.x > other.getX()) {
                return 1;
            }

            return -1;
        }
        return 0;
    }

}
