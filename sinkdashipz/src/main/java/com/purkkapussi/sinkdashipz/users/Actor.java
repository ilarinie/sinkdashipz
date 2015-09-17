/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.GameBoard;
import com.purkkapussi.sinkdashipz.domain.Hull;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.util.ArrayList;

/**
 * yläluokka pelaajille (sekä AI, että pelaaja), hoitaa pelaajan laivat.
 */
public class Actor {

    private ArrayList<Ship> ships;

    public Actor() {
        this.ships = new ArrayList<>();
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public String toString() {
        String text = "";
        for (Ship ship : this.ships) {
            text = text + ship.toString() + "\n";
        }
        return text;
    }
    
    public int fleetSize(){
        return ships.size();
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }
    public void createRandomFleet(GameBoard gameboard){
        
    }
    
    /*
     Metodi saa parametrinä lokaation, johon ammutaan. Metodi tarkistaa pelaajan laivat mahdollisten osumien 
     varalta ja poistaa laivan, jos sen rungon kaikkiin osiin on osuttu.
     */

    public boolean hit(Location location) {
        Ship ship = new Ship(new Hull(location.getX(), location.getY()));  //Luo uuden laivan ampumalokaatioon vertailua varten

        boolean re = false;

        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).equals(ship)) {    // Ship luokan oliot ovat samat, jos niissä on yksikin runkopalikka samassa sijainnissa

                ships.get(i).removeHull(ship.getHulls().get(0));

                if (ships.get(i).getHulls().isEmpty()) {
                    ships.remove(i);
                }

                re = true;
            }
        }
        return re;
    }
}
