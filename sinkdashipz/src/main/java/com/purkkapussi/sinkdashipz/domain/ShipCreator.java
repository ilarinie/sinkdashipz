/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.domain.Direction;
import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.Location;
import com.purkkapussi.sinkdashipz.users.Actor;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class provides methods to create new ships and fleets for Actors.
 *
 * @author ile
 */
public class ShipCreator {

    private Random rand = new Random();

    /**
     * Method creates a random ship with the given size on the given game board.
     *
     * @param size size of the new Ship
     * @param gameBoard game board to create the Ship on
     *
     * @return new random Ship
     */
    public Ship createRandomShip(int size, GameBoard gameBoard) {

        Ship ship = new Ship();

        Direction direction = Direction.SOUTH;
        if (rand.nextInt(2) == 1) {
            direction = Direction.EAST;
        }
        Location start = createStartPoint(gameBoard);

        Hull hull = new Hull(start);

        while (size > 0) {
            if (direction == direction.SOUTH) {
                start = start.moveSouth();
                shipBuilder(ship, new Hull(start.getX(), start.getY()));
            } else {
                start = start.moveEast();
                shipBuilder(ship, new Hull(start.getX(), start.getY()));
            }

            size--;
        }

        return ship;
    }

    /**
     * Method adds a given Hull to the given Ship
     *
     * @param ship Ship to add the Hull to
     * @param hull Hull to be added
     *
     */
    public void shipBuilder(Ship ship, Hull hull) {
        ship.addHull(hull);
    }

    /**
     * Method creates a randomized "starting point" for a new Ship.
     *
     *
     * @param gameBoard game board to create the Location on
     *
     * @return starting location for the new Ship
     */
    public Location createStartPoint(GameBoard gameBoard) {

        int startX = rand.nextInt(gameBoard.getWidth() - 2);
        /* while (direction == Direction.EAST && ((startX + size) >= gameBoard.getWidth())) {
         startX = rand.nextInt(gameBoard.getWidth() - 2);
         }
         */
        int startY = rand.nextInt(gameBoard.getWidth() - 2);
        /*  while (direction == Direction.SOUTH && ((startY - size) < 0)) {
         startY = rand.nextInt(gameBoard.getLength() - 2);
         }*/
        return new Location(startX, startY);
    }

    /**
     * Method tries to add a ship to an actor. The method throws an
     * IllegalArgumentException if the ship is either out of bounds from the
     * game board, or if the actor has overlapping ships.
     *
     *
     * @param actor actor to add the ship to.
     * @param ship ship to add.
     * @param gameBoard game board to be used.
     * 
     * @throws IllegalArgumentException thrown if ships overlap, or are out of
     * bounds
     */
    public void addShipToActor(Actor actor, Ship ship, GameBoard gameBoard) throws IllegalArgumentException {

        if (ship.overlaps(gameBoard)) {
            throw new IllegalArgumentException("Ship out of bounds");
        }

        ArrayList<Ship> ships = actor.getShips();
        if (ships.isEmpty()) {
            actor.addShip(ship);
        } else {

            for (Ship shipster : ships) {
                if (ship.equals(shipster)) {
                    throw new IllegalArgumentException("Ships overlapping!");
                }

            }
            actor.addShip(ship);
        }

    }
    
    
    /**
     * Method creates a random fleet for the Actor. 
     * 
     * 
     * @param actor actor to create the fleet for
     * @param fleetSize desired fleet size
     * @param gameBoard game board to be used
     * 
     * @throws IllegalArgumentException 
     * 
     */
    public void createRandomFleet(Actor actor, int fleetSize, GameBoard gameBoard) throws IllegalArgumentException {

        if (fleetSize >= gameBoard.getWidth() || fleetSize >= gameBoard.getWidth()) {
            throw new IllegalArgumentException("Board too small for fleetsize");
        }

        for (int i = 0; i < fleetSize; i++) {
            Ship ship = createRandomShip(nextShipSize(actor, 2), gameBoard);
            try {
                addShipToActor(actor, ship, gameBoard);
            } catch (IllegalArgumentException e) {
                i--;
            }
        }

    }

    /**
     * Method determines the next ship to be created for an actor in random ship creation.
     * 
     * @param actor actor in question
     * @param increment determines the increment of the ship size increase.
     * @return 
     */
    public int nextShipSize(Actor actor, int increment) {
        int size;
        int add = 0;

        if (actor.getShips().isEmpty()) {
            return 2;
        }
        size = actor.biggestShipSize();

        if (actor.getShips().size() % 2 == 0) {
            return size + increment;
        }

        return size;
    }

}
