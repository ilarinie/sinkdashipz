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
    protected JLabel coordinates = new JLabel("Selected coordinates:");
    protected JLabel playerScore = new JLabel("Current score:");
    
    
    public GameMenu(GUI gui){
        listener = new GameMenuListener(gui);
    }
    public void createGameMenu(GUI gui){
        this.setLayout(new GridLayout(2,4));
        shoot.addActionListener(listener);
        shoot.setEnabled(gui.isLocationSelected());
        this.add(shoot);
        
        
        this.add(aiShips);
        
        this.add(ownShips);
        
        this.add(coordinates);
        
        this.add(playerScore);
        
        
    }
    public void updateGameMenu(GUI gui){
        shoot.setEnabled(gui.isLocationSelected());
        aiShips.setText(gui.aiName() +"'s ships: "+gui.getAIFleetSize());
        ownShips.setText(gui.playerName()+"'s ships: "+gui.getPlayerFleetSize());
        coordinates.setText("Selected coordinates: "+gui.targetLocation());
        playerScore.setText("Current score: "+gui.playerScore());
    }
    
}
