package com.purkkapussi.sinkdashipz.domain;

import java.util.Random;

/**
 * Class provides a standard object to represent coordinates on the game board.
 *
 * @author ile
 */
public class Location {

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
     * Constructs a random new location depending on the game board size given
     * as parameter
     *
     * @param size game board size
     *
     */
    public Location(int size) {
        Random rand = new Random();
        this.x = rand.nextInt(size);
        this.y = rand.nextInt(size);
    }

    //GETTERS AND SETTERS
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
    //LOCATION FUNCTIONALITY

    /**
     * Method checks if this Location is out of bounds on the given game board
     * size
     *
     * @param size size of the game board
     *
     * @return true if location is not on the game board
     */
    public Boolean locOutOfBounds(int size) {
        if (this.x < 0) {
            return true;
        }
        if (this.x >= size) {
            return true;
        }
        if (this.y < 0) {
            return true;
        }
        if (this.y >= size) {
            return true;
        }
        return false;
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
    @Override
    public String toString() {

        return "[" + this.x + "," + this.y + "]";
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

}
