/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.Direction;
import com.purkkapussi.sinkdashipz.users.Actor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Class provides methods to create new ships and fleets for Actors.
 *
 * @author ile
 */
public class ShipCreator {

    private final Random rand = new Random();

    /**
     * Method creates a random ship with the given size on the given game board.
     *
     * @param size size of the new Ship
     * @param gameBoard game board to create the Ship on
     *
     * @return new random Ship
     */
    public Ship createRandomShip(int size, int gameBoard) {
        Ship ship = new Ship();
        Direction direction = Direction.SOUTH;
        if (rand.nextInt(2) == 1) {
            direction = Direction.EAST;
        }
        ship.setDirection(direction);
        Location start = createStartPoint(gameBoard);

        if (direction == Direction.EAST) {
            for (int i = 0; i < size; i++) {
                ship.addHull(new Location((start.getX() + i), start.getY()));
            }
        }
        if (direction == Direction.SOUTH) {
            for (int i = 0; i < size; i++) {
                ship.addHull(new Location(start.getX(), start.getY() - i));
            }
        }
        return ship;
    }

    /**
     * Method creates a new ship with the given parameters.
     *
     * @param size size of the ship
     * @param direction direction of the ship on the game board
     * @param startloc starting location of the ship
     * @return new Ship
     */
    public Ship createShip(int size, Direction direction, Location startloc) {
        Ship newShip = new Ship();
        newShip.setDirection(direction);
        if (direction == Direction.EAST) {
            for (int i = 0; i < size; i++) {
                newShip.addHull(new Location(startloc.getX() + i, startloc.getY()));
            }
        }
        if (direction == Direction.SOUTH) {
            for (int i = 0; i < size; i++) {
                newShip.addHull(new Location(startloc.getX(), startloc.getY() - i));
            }
        }
        return newShip;
    }

    private HashSet<Location> createHullSet(int size, Direction direction, Location startloc) {
        HashSet<Location> hulls = new HashSet<>();
        if (direction == Direction.EAST) {
            for (int i = 0; i < size; i++) {
                hulls.add(new Location(startloc.getX() + i, startloc.getY()));
            }
        }
        if (direction == Direction.SOUTH) {
            for (int i = 0; i < size; i++) {
                hulls.add(new Location(startloc.getX(), startloc.getY() - i));
            }
        }
        return hulls;
    }

    /**
     * Method creates a random fleet for the Actor.
     *
     * @param actor actor to create the fleet for
     * @param gameBoard game board to be used
     *
     * @throws IllegalArgumentException if a ship cant be added to the actor.
     * Tries again until the fleet size of 5 is reached.
     *
     */
    public void createRandomFleet(Actor actor, int gameBoard) throws IllegalArgumentException {
        for (int i = 0; i < 5; i++) {
            Ship ship = createRandomShip(actor.nextShipSize(), gameBoard);
            try {
                addShipToActor(actor, ship, gameBoard);
            } catch (IllegalArgumentException e) {
                i--;
            }
        }
    }

    /**
     * Method creates a randomized "starting point" for a new Ship.
     *
     * @param gameBoard game board to create the Location on
     *
     * @return starting location for the new Ship
     */
    public Location createStartPoint(int gameBoard) {
        int startX = rand.nextInt(gameBoard - 2);
        int startY = rand.nextInt(gameBoard - 2);
        return new Location(startX, startY);
    }

    /**
     * Method tries to add a ship to an actor. The method throws an
     * IllegalArgumentException if the ship is either out of bounds from the
     * game board, if the actor has overlapping ships or if the ship would have
     * adjacent neighbor ships.
     *
     * @param actor actor to add the ship to.
     * @param ship ship to add.
     * @param gameBoard game board to be used.
     *
     * @throws IllegalArgumentException thrown if ships overlap, or are out of
     * bounds
     */
    public void addShipToActor(Actor actor, Ship ship, int gameBoard) throws IllegalArgumentException {
        if (ship.outOfBounds(gameBoard)) {
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
            if (checkForNeighboringShips(actor, ship)) {
                throw new IllegalArgumentException("Neighboring ship detected");
            }
            actor.addShip(ship);
        }
    }

    /**
     * Method checks the given ship against the Actors ships to determine if the
     * ship has any adjacent ships (therefore being in an illegal position). The
     * method creates a new ship that has all the Hulls of the tested ship and
     * also a Hull in every location surrounding the tested ship. It then checks
     * if the new ship overlaps with any of the actors previous ships
     *
     * @param actor actor who's ships to check against
     * @param ship ship to get neighbors
     * @return true if ship has immediate neighbors
     *
     */
    public boolean checkForNeighboringShips(Actor actor, Ship ship) {
        if (actor.getShips().isEmpty()) {
            return false;
        }
        Ship tester = new Ship();
        Location startLoc = new Location(ship.getLocs().get(0).getX(), ship.getLocs().get(0).getY());
        startLoc.moveNorth();
        startLoc.moveWest();
        //The code below will create a new ship that has all the Hulls of the tested ship and also a Hull in every location surrounding the tested ship
        if (ship.getDirection() == Direction.EAST) {
            ArrayList<Location> hulls = new ArrayList<>();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            startLoc.moveSouth();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            startLoc.moveSouth();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            tester.addHullList(hulls);
        }
        if (ship.getDirection() == Direction.SOUTH) {
            ArrayList<Location> hulls = new ArrayList<>();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            startLoc.moveEast();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            startLoc.moveEast();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            tester.addHullList(hulls);
        }
        return actor.getShips().contains(tester);
    }
}
