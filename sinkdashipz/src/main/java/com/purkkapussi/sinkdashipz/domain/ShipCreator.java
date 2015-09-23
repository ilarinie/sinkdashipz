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
import java.util.HashSet;
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
        ship.setDirection(direction);
        Location start = createStartPoint(gameBoard);

        Hull hull = new Hull(start);
        System.out.println("startloc " + start + " direction " + direction);

        if (direction == Direction.EAST) {
            for (int i = 0; i < size; i++) {
                shipBuilder(ship, new Hull((start.getX() + i), start.getY()));
                System.out.println(i + ". pala: " + (start.getX() + i) + ", " + start.getY());
            }
        }
        if (direction == Direction.SOUTH) {
            for (int i = 0; i < size; i++) {
                shipBuilder(ship, new Hull(start.getX(), start.getY() - i));
                System.out.println(i + ". pala: " + start.getX() + ", " + (start.getY() - i));
            }
        }
        System.out.println("laiva täs vaihees: " + ship);
        return ship;
    }

    public Ship createShip(int size, Direction direction, Location startloc) {
        Ship newShip = new Ship();
        newShip.setDirection(direction);
        if (direction == Direction.EAST) {
            for (int i = 0; i < size; i++) {
                shipBuilder(newShip, new Hull(startloc.getX() + i, startloc.getY()));
            }
        }
        if (direction == Direction.SOUTH) {
            for (int i = 0; i < size; i++) {
                shipBuilder(newShip, new Hull(startloc.getX(), startloc.getY() - i));
            }
        }
        return newShip;
    }

    public HashSet<Hull> createHullSet(int size, Direction direction, Location startloc) {
        HashSet<Hull> hulls = new HashSet<>();
        if (direction == Direction.EAST) {
            for (int i = 0; i < size; i++) {
                hulls.add(new Hull(startloc.getX() + i, startloc.getY()));
            }
        }
        if (direction == Direction.SOUTH) {
            for (int i = 0; i < size; i++) {
                hulls.add(new Hull(startloc.getX(), startloc.getY() - i));
            }
        }
        return hulls;
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
            if (checkForNeighboringShips(actor, ship)) {
                throw new IllegalArgumentException("Neighboring ship detected");
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
        

        if (fleetSize >= gameBoard.getWidth() || (gameBoard.getWidth()==10 && fleetSize > 6)) {
            throw new IllegalArgumentException("Board too small for fleetsize");
        }
        for (int i = 0; i < fleetSize; i++) {
            Ship ship = createRandomShip(nextShipSize(actor, 1, fleetSize), gameBoard);
            try {
                addShipToActor(actor, ship, gameBoard);
            } catch (IllegalArgumentException e) {
                i--;
            }
        }
    }

    /**
     * Method determines the next ship to be created for an actor in random ship
     * creation.
     *
     * @param actor actor in question
     * @param increment determines the increment of the ship size increase.
     * @return
     */
    public int nextShipSize(Actor actor, int increment, int fleetSize) {
        int size = fleetSize;
        int add = 0;

        if (actor.getShips().isEmpty()) {
            return size;
        }
        size = actor.smallestShipSize();
        if (size==2){
            return size;
        }

        // if (actor.getShips().size() % 2 == 0) {
        return size - increment;
       // }

    }

    public boolean checkForNeighboringShips(Actor actor, Ship ship) {

        if (actor.getShips().isEmpty()) {
            return false;
        }

        Ship tester = new Ship();
        Location startLoc = new Location(ship.getHulls().get(0).getLocation().getX(), ship.getHulls().get(0).getLocation().getY());
        startLoc.moveNorth();
        startLoc.moveWest();

        if (ship.getDirection() == Direction.EAST) {
            ArrayList<Hull> hulls = new ArrayList<>();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            startLoc.moveSouth();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            startLoc.moveSouth();
            hulls.addAll(createHullSet(ship.getSize() + 2, ship.getDirection(), startLoc));
            tester.addHullList(hulls);

        }
        if (ship.getDirection() == Direction.SOUTH) {
            ArrayList<Hull> hulls = new ArrayList<>();
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
