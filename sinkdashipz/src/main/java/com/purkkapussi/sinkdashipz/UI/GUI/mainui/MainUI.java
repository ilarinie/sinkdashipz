/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.mainui;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainUI extends JPanel {
    
    private MainUIListener listener;
    private int gameBoardSize;
    private BufferedImage shipImage = null;
    private BufferedImage seaImage = null;
    private URL shipUrl;
    private URL seaUrl;
    protected JPanel mainHolder;
    protected JPanel aimHolder;
    protected JPanel playerShipHolder;
    
    public MainUI(GUI gui) {
        this.listener = new MainUIListener(gui);
        gameBoardSize = gui.getGameBoardSideLenght();
        playerShipHolder = new JPanel(new GridLayout(gameBoardSize, gameBoardSize));
        aimHolder = new JPanel(new GridLayout(gameBoardSize, gameBoardSize));
        mainHolder = new JPanel(new GridLayout(1, 2));
        //System.out.println(seaUrl.getPath());
    }
    
    public void createImages() {
        
        shipUrl = getClass().getResource("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/ship.png");
        seaUrl = getClass().getResource("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/ship.png");
        
        try {
            shipImage = ImageIO.read(new File("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/ship.png"));
            
        } catch (IOException e) {
            
        }
        try {
            seaImage = ImageIO.read(new File("/com/purkkapussi/sinkdashipz/UI/GUI/mainui/sea.png"));
        } catch (IOException e) {
            
        }
        
    }
    
    public void createMainUI(GUI gui) {
        
        createImages();
        
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                //pelaajan ampumisnappulat
                JButton aimButton = new JButton();
                Location loc = new Location(i, j);
                AimListener aimListener = new AimListener(gui, loc);
                aimButton.addActionListener(aimListener);
                aimButton.setPreferredSize(new Dimension(40, 40));
                aimHolder.add(aimButton);
                //pelaajan omat laivat
                JLabel playerShip = new JLabel();
                playerShip.setPreferredSize(new Dimension(40,40));
                if (gui.isTherePlayerShip(i, j)) {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("BOOM");
                        playerShip.setForeground(Color.ORANGE);
                    } else {
                        playerShip.setText("SHIP");
                    }
                } else {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("AI MISS");
                        playerShip.setForeground(Color.RED);
                        
                    } else {
                        playerShip.setText("SEA");
                        playerShip.setForeground(Color.BLUE);
                    }
                }
                playerShipHolder.add(playerShip);
                
            }
            
        }
        mainHolder.add(aimHolder);
        mainHolder.add(playerShipHolder);
        
        this.add(mainHolder);
        
    }
    
    public void updateMainUI(GUI gui) {
        
        aimHolder.removeAll();
        playerShipHolder.removeAll();
        
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                //pelaajan ampumisnappulat
                JButton aimButton = new JButton();
                aimButton.setPreferredSize(new Dimension(40, 40));
               
                Location loc = new Location(i, j);
                AimListener aimListener = new AimListener(gui, loc);
                
                
                if (gui.getPlayerHits().contains(new Location(i, j))) {
                    aimButton.setEnabled(false);
                    if (gui.initialAIShipLocs().contains(new Location(i,j))){
                        System.out.println("joo");
                        aimButton.setBackground(Color.ORANGE);
                        aimButton.setOpaque(true);
                        
                    }
                }
                aimButton.addActionListener(aimListener);
                aimHolder.add(aimButton);
                //pelaajan omat laivat
                JLabel playerShip = new JLabel();
                playerShip.setPreferredSize(new Dimension(40,40));
                if (gui.isTherePlayerShip(i, j)) {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("BOOM");
                        playerShip.setForeground(Color.ORANGE);
                    } else {
                        playerShip.setText("SHIP");
                    }
                } else {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("AI MISS");
                        playerShip.setForeground(Color.RED);
                        
                    } else {
                        playerShip.setText("SEA");
                        playerShip.setForeground(Color.BLUE);
                    }
                }
                playerShipHolder.add(playerShip);
                
            }
        }
        
    }
}
