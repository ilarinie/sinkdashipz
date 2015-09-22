package com.purkkapussi.sinkdashipz.tools;

import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.ShipCreator;
import com.purkkapussi.sinkdashipz.users.Actor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;

import static org.junit.Assert.*;


public class ShipCreatorTest{

public ShipCreator creator = new ShipCreator();
public Actor tester = new Actor();
public GameBoard gameBoard = new GameBoard(10);

@Test
public void createShipSizeTest(){
    Ship laiva = creator.createRandomShip(5,gameBoard);
    
    assertEquals(5, laiva.getSize());
}

@Test
public void createBiggerShip(){
    Ship laiva = creator.createRandomShip(9,gameBoard);
    assertEquals(9, laiva.getSize());
}

@Test
public void createShipForActor(){
    Ship laiva = creator.createRandomShip(9,gameBoard);
    
    try {
    creator.addShipToActor(tester, laiva, gameBoard);
    assertEquals(1, tester.fleetSize());
    }
    catch (IllegalArgumentException e){
        assertEquals(0, tester.fleetSize());
    }
    
    
    
}

@Test
public void createDuplicateShipForActor(){
    Ship ship = new Ship(new Hull(1, 1));
    
    creator.addShipToActor(tester, ship, gameBoard);
    try {
    creator.addShipToActor(tester, ship, gameBoard);
    fail("Expected overlapping exception.");
    }
    catch (IllegalArgumentException e){
        
        String error = "Ships overlapping!";
        assertEquals(error, e.getMessage());
    }
    
}

@Test
public void createRandomFleet(){
    
    try {
        creator.createRandomFleet(tester, 5, gameBoard);
        System.out.println(tester);
        assertEquals(5, tester.fleetSize());
    }
    catch (IllegalArgumentException e){
        
    }
    
}
@Test
public void creatTooBigRandomFleet(){
    
    try {
        creator.createRandomFleet(tester, 12, gameBoard);
        fail("Should have thrown exception");
    }
    catch (IllegalArgumentException e){
        String error = "Board too small for fleetsize";
        assertEquals(error,e.getMessage());
    }
    
}


}