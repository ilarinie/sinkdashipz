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
 *
 * @author ile
 */
public class Player extends Actor{
    
    private final ArrayList<Ship> ships;
    
    
    public Player(String nick){
        super.setName(nick);
        this.ships = new ArrayList<>();
    }

    public Player() {
        super.setName("unnamed player");
        this.ships = new ArrayList<>();
    }
    

    
    
    
}
