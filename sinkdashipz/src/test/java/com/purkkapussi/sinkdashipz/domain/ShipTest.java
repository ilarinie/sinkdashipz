/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;


import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class ShipTest {

    Location hull1 = new Location(1, 1);
    Location hull2 = new Location(2, 1);
    Location hull3 = new Location(3, 1);
    Location hull4 = new Location(4, 1);

    int gameBoard = 10;

    Ship ship = new Ship();
    Ship ship2 = new Ship();

    @Test
    public void shipsWithSameHullsCollide() {
        ship.addHull(hull1);
        ship.addHull(hull2);
        ship2.addHull(hull2);
        ship2.addHull(hull3);
        assertEquals(true, ship.equals(ship2));
    }

    @Test
    public void shipsWithDifferentHullsDontCollide() {
        ship.addHull(hull1);
        ship.addHull(hull2);

        ship2.addHull(hull3);
        ship2.addHull(hull4);

        assertEquals(false, ship.equals(ship2));
    }

    @Test
    public void sameShipsCollide() {
        Ship testShip = new Ship(new Location(1, 1));
        System.out.println(testShip);
        Ship testShip2 = new Ship(new Location(1, 1));
        System.out.println(testShip2);
        assertEquals(true, testShip.equals(testShip2));
    }

    @Test
    public void shipSizeIsCorrect() {
        ship.addHull(hull1);
        assertEquals(1, ship.getSize());
    }

    @Test
    public void shipSizeIsCorrectTwo() {
        ship.addHull(hull1);
        ship.addHull(hull2);
        ship.addHull(hull3);

        assertEquals(3, ship.getSize());
    }

    @Test
    public void shipOutOfBoundsTrue() {
        Ship outShip = new Ship(new Location(-1, 2));
        assertEquals(true, outShip.outOfBounds(gameBoard));
    }

    @Test
    public void shipOutOfBoundsFalse() {
        ship.addHull(hull1);
        assertEquals(false, ship.outOfBounds(gameBoard));
    }
    
    @Test
    public void addHullTest(){
        ship.addHull(hull1);
        assertEquals(1, ship.getSize());
    }
    @Test
    public void addTwoHullsTest(){
        ship.addHull(hull1);
        ship.addHull(hull2);
        assertEquals(2,ship.getSize());
    }
    @Test
    public void addHullList(){
        ArrayList<Location> hulls = new ArrayList<>();
        for (int i=0; i < 10; i++){
            hulls.add(new Location(i,1));
        }
        ship.addHullList(hulls);
        assertEquals(10, ship.getSize());
    }
    @Test
    public void addBigHullList(){
        ArrayList<Location> hulls = new ArrayList<>();
        for (int i=0; i < 1000; i++){
            hulls.add(new Location(i,1));
        }
        ship.addHullList(hulls);
        assertEquals(1000, ship.getSize());
    }
    @Test
    public void toStringTest(){
        ship.addHull(hull1);
        assertEquals("[1,1]",ship.toString());
    }
    
}
