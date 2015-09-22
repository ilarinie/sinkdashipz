/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.gamemenu;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ile
 */
public class GameMenu extends JPanel{
    
    public GameMenuListener listener;
    protected JButton shoot = new JButton("shoot");
    protected JLabel aiShips = new JLabel("aiships");
    protected JLabel ownShips = new JLabel("own ships");
    protected JLabel other = new JLabel("Selected coordinates:");
    
    public GameMenu(GUI gui){
        listener = new GameMenuListener(gui);
    }
    public void createGameMenu(GUI gui){
        this.setLayout(new GridLayout(1,4));
        shoot.addActionListener(listener);
        shoot.setEnabled(gui.isLocationSelected());
        this.add(shoot);
        
        
        this.add(aiShips);
        
        this.add(ownShips);
        
        this.add(other);
        
        
    }
    public void updateGameMenu(GUI gui){
        shoot.setEnabled(gui.isLocationSelected());
        aiShips.setText("Ai ships: "+gui.getAIFleetSize());
        ownShips.setText("Player ships: "+gui.getPlayerFleetSize());
        other.setText("Selected coordinates: "+gui.targetLocation());
    }
    
}
