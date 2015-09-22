/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.tools;

import com.purkkapussi.sinkdashipz.domain.Direction;
import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.users.Actor;
import java.util.ArrayList;
import java.util.Random;

public class ShipCreator {

    private Random rand = new Random();

    /*
     Metodi luo satunnaisen laivan annetulla koolla satunnaiseen sijaintiin
     */
    public Ship createRandomShip(int size, GameBoard gameBoard) {

        Ship ship = new Ship();
        
        Direction direction = Direction.SOUTH;
        if (rand.nextInt(2) == 1) {
            direction = Direction.EAST;
        }
        Location start = createStartPoint(size, direction, gameBoard);

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

    public void shipBuilder(Ship ship, Hull hull) {
        ship.addHull(hull);
    }
    /*
     Metodi luo satunnaisen aloituspisteen joka sopii yhteen laivan koon kanssa.
     */

    public Location createStartPoint(int size, Direction direction, GameBoard gameBoard) {

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

    public void addShipToActor(Actor actor, Ship ship, GameBoard gameBoard) throws IllegalArgumentException {
        
        if (ship.overlaps(gameBoard)){
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

    public void createRandomFleet(Actor actor, int fleetSize, GameBoard gameBoard) throws IllegalArgumentException {

        if (fleetSize >= gameBoard.getWidth() || fleetSize >= gameBoard.getWidth()) {
            throw new IllegalArgumentException("Board too small for fleetsize");
        }

        for (int i = 0; i < fleetSize; i++) {
            Ship ship = createRandomShip(nextShipSize(actor, 2), gameBoard);
            System.out.println("shipsize=" + nextShipSize(actor, 1));
            try {
                addShipToActor(actor, ship, gameBoard);
            } catch (IllegalArgumentException e) {
                i--;
            }
        }

    }

    public int nextShipSize(Actor actor, int increment) {
        int size;
        int add = 0;

        if (actor.getShips().isEmpty()) {
            return 2;
        }
        size = actor.biggestShipSize();
        
        if (actor.getShips().size() %2 == 0){
            return size+increment;
        }
        
        return size;
    }

}
