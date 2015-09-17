package com.purkkapussi.sinkdashipz.tools;

import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.ShipCreator;
import com.purkkapussi.sinkdashipz.users.Actor;
import org.junit.Test;
import org.junit.Rule;

import static org.junit.Assert.*;


public class ShipCreatorTest{

public ShipCreator luoja = new ShipCreator();
public Actor tester = new Actor();
public GameBoard gameBoard = new GameBoard(10,10);

@Test
public void createShipSizeTest(){
    Ship laiva = luoja.createRandomShip(5,gameBoard);
    
    assertEquals(5, laiva.getSize());
}

@Test
public void createBiggerShip(){
    Ship laiva = luoja.createRandomShip(9,gameBoard);
    assertEquals(9, laiva.getSize());
}

@Test
public void createShipForActor(){
    Ship laiva = luoja.createRandomShip(9,gameBoard);
    
    luoja.addShipToActor(tester, laiva);
    
    assertEquals(1, tester.getShips().size());
    
}

@Test
public void createDuplicateShipForActor(){
    Ship laiva = luoja.createRandomShip(5,gameBoard);
    
    luoja.addShipToActor(tester, laiva);
    try {
    luoja.addShipToActor(tester, laiva);
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
        luoja.createRandomFleet(tester, 5, gameBoard);
        assertEquals(5, tester.getShips().size());
    }
    catch (IllegalArgumentException e){
        
    }
    
}


}