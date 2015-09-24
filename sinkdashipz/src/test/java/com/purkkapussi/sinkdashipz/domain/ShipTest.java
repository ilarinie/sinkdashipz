/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.GameBoard;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class ShipTest {

    Hull hull1 = new Hull(1, 1);
    Hull hull2 = new Hull(2, 1);
    Hull hull3 = new Hull(3, 1);
    Hull hull4 = new Hull(4, 1);

    GameBoard gameBoard = new GameBoard(10);

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
        Ship testShip = new Ship(new Hull(1, 1));
        System.out.println(testShip);
        Ship testShip2 = new Ship(new Hull(1, 1));
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
        Ship outShip = new Ship(new Hull(-1, 2));
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
        ArrayList<Hull> hulls = new ArrayList<>();
        for (int i=0; i < 10; i++){
            hulls.add(new Hull(i,1));
        }
        ship.addHullList(hulls);
        assertEquals(10, ship.getSize());
    }
    @Test
    public void addBigHullList(){
        ArrayList<Hull> hulls = new ArrayList<>();
        for (int i=0; i < 1000; i++){
            hulls.add(new Hull(i,1));
        }
        ship.addHullList(hulls);
        assertEquals(1000, ship.getSize());
    }
    
}
