/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.tools;

import com.purkkapussi.sinkdashipz.domain.Direction;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import java.util.Random;


public class ShipCreator {

    private Random rand = new Random();
    
    /*
    Metodi luo satunnaisen laivan annetulla koolla satunnaiseen sijaintiin
    */
    public Ship createRandomShip(int size) {

        Ship ship = new Ship();

        Direction direction = Direction.SOUTH;
        if (rand.nextInt(2) == 1) {
            direction = Direction.EAST;
        }
        Location start = createStartPoint(size, direction);

        while (size > 0) {
            ship.addHull(new Hull(start));
            if (direction == direction.SOUTH) {
                start.moveSouth();
            } else {
                start.moveEast();
            }

            size--;
        }

        return ship;
    }
    /*
    Metodi luo satunnaisen aloituspisteen joka sopii yhteen laivan koon kanssa.
    */
    public Location createStartPoint(int size, Direction direction) {

        int startX = rand.nextInt(9);
        while (direction == Direction.EAST && (startX + size) >= 10) {
            startX = rand.nextInt(9);
        }
        int startY = rand.nextInt(9);
        while (direction == Direction.NORTH && (startY + size) >= 10) {
            startY = rand.nextInt(9);
        }
        return new Location(startX, startY);
    }

}
