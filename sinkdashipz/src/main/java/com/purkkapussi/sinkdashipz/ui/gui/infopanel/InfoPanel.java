/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.infopanel;

import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * In-game shooting and info-panel that holds the shooting button and in-game
 * info.
 *
 * @author ile
 */
public class InfoPanel extends JPanel {

    protected JLabel ownShips = new JLabel("", SwingConstants.CENTER);
    protected JLabel coordinates = new JLabel("", SwingConstants.CENTER);
    protected JLabel playerScore = new JLabel("", SwingConstants.CENTER);
    private Font font = new Font("Arial", Font.BOLD, 20);

    
    public InfoPanel(GraphicalUI gui) {
        this.setPreferredSize(new Dimension(1060, 50));
        this.setBackground(Color.BLACK);
        ownShips.setForeground(Color.WHITE);
        ownShips.setFont(font);
        coordinates.setForeground(Color.WHITE);
        coordinates.setFont(font);
        playerScore.setForeground(Color.WHITE);
        playerScore.setFont(font);
        createInfoPanel(gui);
    }

    private void createInfoPanel(GraphicalUI gui) {
        this.setLayout(new GridLayout(1, 3));

        this.add(ownShips);
        this.add(coordinates);
        this.add(playerScore);
        updateGameMenu(gui);
    }

    /**
     * Method updates the info-panel texts
     * @param gui gui used
     */
    public void updateGameMenu(GraphicalUI gui) {
        if (!gui.getGame().getEndgame()) {
            coordinates.setText(gui.getGame().getAI().getName() + "'s ships: " + gui.getGame().getAI().fleetSize());
            ownShips.setText(gui.getGame().getPlayer().getName() + "'s ships: " + gui.getGame().getPlayer().fleetSize());
            playerScore.setText("Current score: " + gui.getGame().getPlayer().getScore());
        } else {
            ownShips.setText("Game ended, " + gui.getGame().getWinner() + " won.");
            coordinates.setText("Your score was: " + gui.getGame().getPlayer().getScore());
            playerScore.setText("Your are " + gui.getGame().getPlayerRank() + ". in the highscores");
        }

    }
}
