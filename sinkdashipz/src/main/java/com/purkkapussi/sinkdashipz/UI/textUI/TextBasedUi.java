/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.textUI;

import com.purkkapussi.sinkdashipz.tools.Location;
import com.purkkapussi.sinkdashipz.users.AI;
import com.purkkapussi.sinkdashipz.users.Actor;
import java.util.Scanner;

/**
 *
 * @author ile
 */
public class TextBasedUi {
    
    private Scanner scanner = new Scanner(System.in);
    
    public TextBasedUi(){
        
    }
    
    public Location shoot(){
        String scan = "";
        System.out.println("Please enter desired X-coordinate: ");
        scan = scanner.nextLine();
        int x = Integer.parseInt(scan);
        
        System.out.println("Please enter desired Y-coordinate: ");
        scan = scanner.nextLine();
        int y = Integer.parseInt(scan);
        
        
        return new Location(x,y);
    }

    public void showScore(int playerships, int aiships) {
        System.out.println("Current score:");
        System.out.println("AI SHIPS: "+aiships);
        System.out.println("PLAYER SHIPS: "+playerships);
        System.out.println("");
    }
    
    public void hit(){
        System.out.println("AMAZING HIT!");
        //add randomized congratulations
    }
    public void aiHit(){
        System.out.println("Ouch, AI just hit you..");
        //add randomized messages
    }
    public void youWon(){
        System.out.println("Congratulations, you won!");
    }
    public void youLost(){
        System.out.println("You lost, too bad.");
    }
    public void youMissed(){
        System.out.println("You missed! That was close! ..probably. Or not.");
    }
    public void aiMissed(Location location){
        System.out.println("AI missed you on "+location+", those fool computers");
    }
    public void printFleet(Actor actor){
        System.out.println(actor.getName()+"'s ships as follows: \n");
        System.out.println(actor);
    }
}
