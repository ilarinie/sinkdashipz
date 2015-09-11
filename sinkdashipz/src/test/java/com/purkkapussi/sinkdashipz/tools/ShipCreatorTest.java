package com.purkkapussi.sinkdashipz.tools;

import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.ShipCreator;
import org.junit.Test;
import org.junit.Rule;

import static org.junit.Assert.*;


public class ShipCreatorTest{

public ShipCreator luoja = new ShipCreator();    

@Test
public void createShipSizeTest(){
    Ship laiva = luoja.createRandomShip(5);
    
    assertEquals(5, laiva.getSize());
}

@Test
public void createBiggerShip(){
    Ship laiva = luoja.createRandomShip(9);
    assertEquals(9, laiva.getSize());
}



}