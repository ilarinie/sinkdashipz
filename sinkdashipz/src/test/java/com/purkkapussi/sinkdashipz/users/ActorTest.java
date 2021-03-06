/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.domain.Location;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ActorTest {

    Actor person = new Actor();

    Ship ship = new Ship(new Location(1, 1));
    Ship ship2 = new Ship(new Location(2, 1));
    Location location = new Location(1, 1);
    Location location2 = new Location(2, 3);

    @Test
    public void didItHit() {

        person.addShip(ship);
        assertEquals(true, person.hit(location));
    }

    @Test
    public void didntItHit() {
        person.addShip(ship);
        assertEquals(false, person.hit(location2));
    }

    @Test
    public void didItDestroy() {

        person.addShip(ship);
        int before = person.getShips().size();
        person.hit(location);

        assertEquals(before - 1, person.getShips().size());
    }

    @Test
    public void didItDestroy2() {

        person.addShip(ship);
        person.addShip(ship2);
        int before = person.getShips().size();
        person.hit(location);

        assertEquals(before - 1, person.getShips().size());
    }

    @Test
    public void toStringTest() {
        person.addShip(ship);
        assertEquals("[1,1]\n", person.toString());
    }

    @Test
    public void removeShipTest() {
        person.addShip(ship);
        person.removeShip(ship);
        assertEquals(0, person.fleetSize());
    }

    @Test
    public void initialLocTest() {
        for (int i = 0; i < 5; i++) {
            person.addShip(new Ship(new Location(1, 1)));
            if (i == 3) {
                assertEquals(null, person.initialShipLocations());
            }
        }
        assertEquals("[[1,1]]", person.initialShipLocations().toString());
        assertEquals("[[1,1]]", person.shipLocs().toString());
    }
    
    @Test
    public void nextShipSizeTest(){
        assertEquals(5, person.nextShipSize());
    }

}
