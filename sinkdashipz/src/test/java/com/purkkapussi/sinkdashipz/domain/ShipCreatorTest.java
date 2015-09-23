package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.domain.ShipCreator;
import com.purkkapussi.sinkdashipz.tools.Location;
import com.purkkapussi.sinkdashipz.users.Actor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;

import static org.junit.Assert.*;
import org.junit.Before;

public class ShipCreatorTest {

    public ShipCreator creator;
    public Actor tester;
    public GameBoard gameBoard;
    public Ship testShip;

    @Before
    public void createObjects() {
        creator = new ShipCreator();
        tester = new Actor();
        gameBoard = new GameBoard(10);
        testShip = new Ship(new Hull(1, 1));
        testShip.setDirection(Direction.EAST);
        for (int i = 2; i < 6; i++) {
            testShip.addHull(new Hull(i, 1));
        }

    }

    @Test
    public void createShipSizeTest() {
        Ship laiva = creator.createRandomShip(5, gameBoard);

        assertEquals(5, laiva.getSize());
    }

    @Test
    public void createBiggerShip() {
        Ship laiva = creator.createRandomShip(9, gameBoard);
        assertEquals(9, laiva.getSize());
    }

    @Test
    public void createShipForActor() {
        Ship laiva = creator.createRandomShip(8, gameBoard);

        try {
            creator.addShipToActor(tester, laiva, gameBoard);
            assertEquals(1, tester.fleetSize());
        } catch (IllegalArgumentException e) {
            assertEquals(0, tester.fleetSize());
        }

    }

    @Test
    public void createDuplicateShipForActor() {
        Ship ship = new Ship(new Hull(1, 1));

        creator.addShipToActor(tester, ship, gameBoard);
        try {
            creator.addShipToActor(tester, ship, gameBoard);
            fail("Expected overlapping exception.");
        } catch (IllegalArgumentException e) {

            String error = "Ships overlapping!";
            assertEquals(error, e.getMessage());
        }

    }

    @Test
    public void createRandomFleetOfFive() {
        for (int i = 0; i < 100; i++) {
            try {
                creator.createRandomFleet(tester, 5, gameBoard);
                System.out.println(tester);
                assertEquals(5, tester.fleetSize());
            } catch (IllegalArgumentException e) {

            }
            this.createObjects();
        }

    }

    @Test
    public void createRandomFleetOfSix() {
        for (int i = 0; i < 5; i++) {
            try {
                creator.createRandomFleet(tester, 6, gameBoard);
                System.out.println(tester);
                assertEquals(6, tester.fleetSize());
            } catch (IllegalArgumentException e) {

            }
            this.createObjects();
        }

    }

    @Test
    public void creatTooBigRandomFleet() {

        try {
            creator.createRandomFleet(tester, 12, gameBoard);
            fail("Should have thrown exception");
        } catch (IllegalArgumentException e) {
            String error = "Board too small for fleetsize";
            assertEquals(error, e.getMessage());
        }

    }

    @Test
    public void checkForNeighborShips() {
        Ship ship1 = new Ship(new Hull(1, 1));
        tester.addShip(ship1);
        Ship ship2 = new Ship(new Hull(2, 1));
        ship2.setDirection(Direction.EAST);

        assertEquals(true, creator.checkForNeighboringShips(tester, ship2));
    }

    @Test
    public void checkForNeighBorShipsWhenNoneArePresent() {
        Ship ship1 = new Ship(new Hull(1, 1));
        tester.addShip(ship1);
        Ship ship2 = new Ship(new Hull(5, 4));

        assertEquals(false, creator.checkForNeighboringShips(tester, ship2));
    }

    @Test
    public void checkForBiggerNeighbor() {
        Ship ship1 = creator.createShip(5, Direction.EAST, new Location(1, 1));
        System.out.println("ship1 : " + ship1);
        tester.addShip(ship1);
        Ship ship2 = creator.createShip(5, Direction.EAST, new Location(1, 0));
        System.out.println("ship2: " + ship2);
        assertEquals(true, creator.checkForNeighboringShips(tester, ship2));
    }

    @Test
    public void checkForBiggerNeighborThatIsNotThere() {
        Ship ship1 = creator.createShip(5, Direction.EAST, new Location(1, 1));
        tester.addShip(ship1);
        Ship ship2 = creator.createShip(5, Direction.SOUTH, new Location(10, 2));
        assertEquals(false, creator.checkForNeighboringShips(tester, ship2));
    }

    @Test
    public void checkForShipToTheNorth() {
        tester.addShip(testShip);
        Ship northShip = creator.createShip(testShip.getSize() + 2, Direction.EAST, new Location(0, 2));
        assertEquals(true, creator.checkForNeighboringShips(tester, northShip));
    }

    @Test
    public void checkForShipToTheSouth() {
        tester.addShip(testShip);
        Ship southShip = creator.createShip(testShip.getSize() + 2, Direction.EAST, new Location(0, 0));
        assertEquals(true, creator.checkForNeighboringShips(tester, southShip));
    }

    @Test
    public void checkForShipToTheEast() {
        tester.addShip(testShip);
        Ship eastShip = new Ship(new Hull(6, 1));
        assertEquals(true, creator.checkForNeighboringShips(tester, eastShip));
    }

    @Test
    public void checkForShipToTheWest() {
        tester.addShip(testShip);
        Ship westShip = new Ship(new Hull(0, 1));
        assertEquals(true, creator.checkForNeighboringShips(tester, westShip));
    }

    @Test
    public void checkAgainsShip() {
        tester.addShip(testShip);
        assertEquals(true, creator.checkForNeighboringShips(tester, new Ship(new Hull(4, 2))));
    }

    @Test
    public void testAllSpotsAroundShip() {

        Location startLoc = new Location(testShip.getHulls().get(0).getLocation().getX(), testShip.getHulls().get(0).getLocation().getY());
        int size = testShip.getSize();

        if (testShip.getDirection() == Direction.SOUTH) {
            startLoc.moveNorth();
            startLoc.moveWest();

            for (int i = 0; i <= size + 1; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.println("Location: " + (startLoc.getX() + j) + "," + (startLoc.getY() - i) + " truth: " + creator.checkForNeighboringShips(tester, new Ship(new Hull((startLoc.getX() + j), (startLoc.getY() - i)))));
                    assertEquals(true, creator.checkForNeighboringShips(tester, new Ship(new Hull((startLoc.getX() + j), (startLoc.getY() - i)))));

                }
            }

        }
    }

}
