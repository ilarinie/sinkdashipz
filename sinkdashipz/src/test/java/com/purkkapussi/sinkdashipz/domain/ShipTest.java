/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

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
    public void sameShipsCollide(){
        Ship testShip = new Ship(new Hull(1,1));
        System.out.println(testShip);
        Ship testShip2 = new Ship(new Hull(1,1));
        System.out.println(testShip2);
        assertEquals(true, testShip.equals(testShip2));
    }
    
    @Test
    public void shipSizeIsCorrect(){
        ship.addHull(hull1);
        assertEquals(1,ship.getSize());
    }
    
    @Test
    public void shipSizeIsCorrectTwo(){
        ship.addHull(hull1);
        ship.addHull(hull2);
        ship.addHull(hull3);
        
        assertEquals(3,ship.getSize());
    }
}
