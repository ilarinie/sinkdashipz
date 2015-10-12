/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.gamemenu;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * In-game shooting and info-panel that holds the shooting button and in-game
 * info.
 *
 * @author ile
 */
public class GameMenu extends JPanel {

    public GameMenuListener listener;
    protected JButton shoot = new JButton("Shoot");
    protected JLabel aiShips = new JLabel();
    protected JLabel ownShips = new JLabel();
    protected JLabel coordinates = new JLabel();
    protected JLabel playerScore = new JLabel();

    public GameMenu(GUI gui) {
       
        listener = new GameMenuListener(gui);
        this.setBackground(Color.BLACK);
        aiShips.setForeground(Color.WHITE);
        ownShips.setForeground(Color.WHITE);
        coordinates.setForeground(Color.WHITE);
        playerScore.setForeground(Color.WHITE);
        shoot.setOpaque(true);
        shoot.setBackground(Color.DARK_GRAY);
        shoot.setForeground(Color.red);
        shoot.setToolTipText("Use this button to shoot, when a target location has been selected.");
    }

    public void createGameMenu(GUI gui) {
        this.setLayout(new GridLayout(2, 4));
        shoot.addActionListener(listener);
        shoot.setEnabled(gui.locationSelected());
        this.add(shoot);
        this.add(aiShips);
        this.add(ownShips);
        this.add(coordinates);
        this.add(playerScore);
        updateGameMenu(gui);
    }

    public void updateGameMenu(GUI gui) {
        if (gui.locationSelected()) {
            shoot.setBackground(Color.RED);
            shoot.setForeground(Color.BLACK);
            shoot.setEnabled(true);
        } else {
            shoot.setEnabled(false);
            shoot.setForeground(Color.GRAY);
            shoot.setBackground(Color.DARK_GRAY);
        }

        aiShips.setText(gui.getGame().getAI().getName() + "'s ships: " + gui.getGame().getAI().fleetSize());
        ownShips.setText(gui.getGame().getPlayer().getName() + "'s ships: " + gui.getGame().getPlayer().fleetSize());
        coordinates.setText("Selected coordinates: " + gui.getGame().getPlayerTargetLoc());
        playerScore.setText("Current score: " + gui.getGame().getPlayer().getScore());
    }

}
