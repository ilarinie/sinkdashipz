/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Ship;
import java.util.ArrayList;

/**
 *
 * @author ile
 */
public class Actor {
    
    private ArrayList<Ship> ships;
    
    public Actor() {
    this.ships = new ArrayList<>();
}
    
   public ArrayList<Ship> getShips(){
       return this.ships;
   }
   
   public void addShip(Ship ship){
       ships.add(ship);
   }
   
   public String toString(){
       String text = "";
       for (Ship ship : this.ships){
           text = text+ship.toString()+"\n";
       }
       return text;
   }
}
