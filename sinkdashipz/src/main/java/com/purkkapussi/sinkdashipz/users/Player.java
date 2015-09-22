/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Ship;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class provides human player specific functionality for the Actor Class.
 */
public class Player extends Actor{
    
    private final ArrayList<Ship> ships;
    
    /**
     * Constructs a new Player object with specified name
     * 
     * @param   nick    nickname for the human player
     */
    public Player(String nick){
        super.setName(nick);
        this.ships = new ArrayList<>();
    }
    
    /**
     * Constructs a new Player with a default name "unnamed player"
     */
    public Player() {
        super.setName("unnamed player");
        this.ships = new ArrayList<>();
    }
    

    
    
    
}
