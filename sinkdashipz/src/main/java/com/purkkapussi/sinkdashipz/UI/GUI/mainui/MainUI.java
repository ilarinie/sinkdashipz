/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.mainui;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainUI extends JPanel {

    private MainUIListener listener;
    private int gameBoardSize;

    public MainUI(GUI gui) {
        this.listener = new MainUIListener(gui);
        gameBoardSize = gui.getGameBoardSideLenght();

    }
    
    public void createMainUI(GUI gui){
        
        JPanel mainHolder = new JPanel(new GridLayout(1,2));
        
        JPanel aimHolder = new JPanel(new GridLayout(gameBoardSize, gameBoardSize));
        JPanel playerShipHolder = new JPanel(new GridLayout(gameBoardSize, gameBoardSize));
        
        for (int i = 0; i < gameBoardSize; i++){
            for (int j = 0; j < gameBoardSize; j++){
                //pelaajan ampumisnappulat
                JButton aimButton = new JButton();
                Location loc = new Location(i,j);
                AimListener aimListener = new AimListener(gui,loc);
                aimButton.addActionListener(aimListener);
                aimHolder.add(aimButton);
                //pelaajan omat laivat
                JLabel playerShip = new JLabel("testing");
                playerShipHolder.add(playerShip);
                
            }
            
            
        }
        mainHolder.add(aimHolder);
        mainHolder.add(playerShipHolder);
        
        
        
        
        
        
        this.add(mainHolder);
        
        
        
        
    }
    

}
