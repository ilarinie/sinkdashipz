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
import java.awt.Font;
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
    
    private int buttonWidth = 60;
    private int buttonHeight = 40;
    private int fontSize = 7;

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
                aimButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                if (loc.equals(gui.targetLocation())){
                    aimButton.setText("X");
                }
                aimHolder.add(aimButton);
                //pelaajan omat laivat
                JLabel playerShip = new JLabel();
                playerShip.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                playerShip.setFont(new Font("Arial", Font.PLAIN, fontSize));
                if (gui.isTherePlayerShip(i, j)) {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("BOOM");
                        playerShip.setForeground(Color.ORANGE);
                    } else {
                        playerShip.setText("SHIP");
                    }
                } else {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("MISS");
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
                aimButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                aimButton.setFont(new Font("Arial", Font.PLAIN, fontSize));
                Location loc = new Location(i, j);
                AimListener aimListener = new AimListener(gui, loc);
                  if (loc.equals(gui.targetLocation())){
                    aimButton.setText("X");
                }

                if (gui.getPlayerHits().contains(new Location(i, j))) {
                    aimButton.setEnabled(false);
                    aimButton.setText("MISS");
                    if (gui.initialAIShipLocs().contains(new Location(i, j))) {
                        aimButton.setBackground(Color.ORANGE);
                        aimButton.setText("BOOM");
                        aimButton.setOpaque(true);
                    }
                }
                aimButton.addActionListener(aimListener);
                aimHolder.add(aimButton);
                
                
                //pelaajan omat laivat
                JLabel playerShip = new JLabel();
                playerShip.setFont(new Font("Arial", Font.PLAIN, 9));
                playerShip.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                if (gui.initialPlayerShipLocs().contains(new Location(i,j))) {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("BOOM");
                        playerShip.setBackground(Color.ORANGE);
                        playerShip.setOpaque(true);
                    } else {
                        playerShip.setText("SHIP");
                        playerShip.setBackground(Color.BLACK);
                        playerShip.setOpaque(true);
                    }
                } else {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        playerShip.setText("AI MISS");
                        playerShip.setForeground(Color.BLACK);
                        playerShip.setBackground(Color.red);
                        playerShip.setOpaque(true);

                    } else {
                        playerShip.setText("SEA");
                        playerShip.setForeground(Color.WHITE);
                        playerShip.setBackground(Color.BLUE);
                        playerShip.setOpaque(true);
                    }
                }
                playerShipHolder.add(playerShip);

            }
        }

    }
}
