/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.mainui;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ile
 */
public class AimListener implements ActionListener{
    
    private Location loc;
    private GUI gui;
    
    public AimListener(GUI gui, Location loc){
        this.loc = loc;
        this.gui = gui;
    }
    
   @Override
   public void actionPerformed(ActionEvent e){
       gui.playerShootLoc(loc);
       
       
   }
    
    
    
    
    
}
