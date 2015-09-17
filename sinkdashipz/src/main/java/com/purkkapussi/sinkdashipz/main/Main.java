package com.purkkapussi.sinkdashipz.main;

import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.ShipCreator;
import com.purkkapussi.sinkdashipz.users.Actor;
import java.util.ArrayList;


public class Main {
    
    public static void main(String[] args){
        
        ShipCreator luoja = new ShipCreator();
        Actor testaaja = new Actor();
        GameBoard gameboard = new GameBoard(10,10);
        
        luoja.createRandomFleet(testaaja, 5,gameboard);
        
        System.out.println(testaaja);
        
    }
    
}
