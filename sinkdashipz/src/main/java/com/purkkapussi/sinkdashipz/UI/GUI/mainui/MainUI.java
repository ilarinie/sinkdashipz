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

    private final int buttonWidth = 60;
    private final int buttonHeight = 40;
    private final int fontSize = 7;

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
        updateAimButtons(gui);
        updatePlayerShipLabels(gui);
        mainHolder.add(aimHolder);
        mainHolder.add(playerShipHolder);
        this.add(mainHolder);
    }

    public void updateMainUI(GUI gui) {
        aimHolder.removeAll();
        playerShipHolder.removeAll();
        updateAimButtons(gui);
        updatePlayerShipLabels(gui);
    }

    public void updateAimButtons(GUI gui) {
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                //pelaajan ampumisnappulat
                JButton aimButton = createAimButton();
                Location loc = new Location(i, j);
                AimListener aimListener = new AimListener(gui, loc);
                if (loc.equals(gui.targetLocation())) {
                    markAimLocation(aimButton);
                }
                if (gui.getPlayerHits().contains(new Location(i, j))) {
                    changeButtonToMissed(aimButton);
                    if (gui.initialAIShipLocs().contains(new Location(i, j))) {
                        changeButtonToHit(aimButton);
                    }
                }
                aimButton.addActionListener(aimListener);
                aimHolder.add(aimButton);
            }
        }
    }

    public void updatePlayerShipLabels(GUI gui) {
        for (int i = 0; i < gameBoardSize; i++) {
            for (int j = 0; j < gameBoardSize; j++) {
                JLabel playerShipLabel = createPlayerShipLabel();

                if (gui.initialPlayerShipLocs().contains(new Location(i, j))) {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        setPlayerShipLabelToHit(playerShipLabel);
                    } else {
                        setPlayerShipLabelToShip(playerShipLabel);
                    }
                } else {
                    if (gui.getAIHits().contains(new Location(i, j))) {
                        setPlayerShipLabelToAIMiss(playerShipLabel);
                    } else {
                        setPlayerShipLabelToSea(playerShipLabel);
                    }
                }
                playerShipHolder.add(playerShipLabel);
            }

        }

    }

    //PLAYERSHIPLABEL CREATION AND MODIFICATION:
    public JLabel createPlayerShipLabel() {
        JLabel playerShipLabel = new JLabel();
        playerShipLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
        playerShipLabel.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        return playerShipLabel;
    }

    public void setPlayerShipLabelToHit(JLabel playerShipLabel) {
        playerShipLabel.setText("BOOM");
        playerShipLabel.setBackground(Color.ORANGE);
        playerShipLabel.setOpaque(true);
    }

    public void setPlayerShipLabelToShip(JLabel playerShipLabel) {
        playerShipLabel.setText("SHIP");
        playerShipLabel.setBackground(Color.BLACK);
        playerShipLabel.setOpaque(true);
    }

    public void setPlayerShipLabelToAIMiss(JLabel playerShipLabel) {
        playerShipLabel.setText("AI MISS");
        playerShipLabel.setForeground(Color.BLACK);
        playerShipLabel.setBackground(Color.red);
        playerShipLabel.setOpaque(true);
    }

    public void setPlayerShipLabelToSea(JLabel playerShipLabel) {
        playerShipLabel.setText("SEA");
        playerShipLabel.setForeground(Color.WHITE);
        playerShipLabel.setBackground(Color.BLUE);
        playerShipLabel.setOpaque(true);
    }

    // AIMBUTTON CREATION AND MODIFICATION:
    public JButton createAimButton() {
        JButton aimButton = new JButton();
        aimButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        aimButton.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return aimButton;
    }

    public void markAimLocation(JButton aimButton) {
        aimButton.setText("X");
    }

    public void changeButtonToMissed(JButton aimButton) {
        aimButton.setEnabled(false);
        aimButton.setText("MISS");
    }

    public void changeButtonToHit(JButton aimButton) {
        aimButton.setBackground(Color.ORANGE);
        aimButton.setText("BOOM");
        aimButton.setOpaque(true);
    }

}
