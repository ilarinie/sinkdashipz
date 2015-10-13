/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.mainui;

import com.purkkapussi.sinkdashipz.domain.Location;
import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class provides functionality to change the icons of the buttons on MainUI.
 *
 * @author ile
 */
public class ButtonChanger {

    private final GraphicalUI gui;

    public ButtonChanger(GraphicalUI gui) {
        this.gui = gui;
    }

    /**
     * Method updates the icon on the given button depending on the location and
     * whether the game has ended or not.
     *
     * @param gui Graphical UI in use
     * @param button button to be changed
     * @param loc location of the button on the game board.
     * @param panelId id of the panel in question
     * aimHolder=1,playerShipHolder=2.
     * @return
     */
    public JButton changeButton(GraphicalUI gui, JButton button, Location loc, int panelId) {
        if (panelId == 1) {
            if (gui.getGame().getPlayer().getShotLocs().contains(loc)) {
                changeButtonToMissed(button);
                if (gui.getGame().getAI().initialShipLocations().contains(loc)) {
                    changeButtonToHit(button);
                }
            }
            if (gui.getGame().getEndgame()) {
                if (gui.getGame().getAI().initialShipLocations().contains(loc) && !gui.getGame().getPlayer().getShotLocs().contains(loc)) {
                    changeButtonToShip(button);
                }
                if (!gui.getGame().getPlayer().getShotLocs().contains(loc) && !gui.getGame().getAI().initialShipLocations().contains(loc)) {
                    changeButtonToSea(button);
                }
            }
        } else {
            if (gui.getGame().getPlayer().initialShipLocations().contains(loc)) {
                if (gui.getGame().getAI().getShotLocs().contains(loc)) {
                    changeButtonToHit(button);
                } else {
                    changeButtonToShip(button);
                }
            } else {
                if (gui.getGame().getAI().getShotLocs().contains(loc)) {
                    changeButtonToMissed(button);
                } else {
                    changeButtonToSea(button);
                }
            }
        }
        return button;

    }

    /**
     * Method marks the selected button as missed shot.
     *
     * @param aimButton button to change
     */
    private void changeButtonToMissed(JButton aimButton) {
        aimButton.setEnabled(false);
        URL imgURL = this.getClass().getResource("/img/seamiss.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
    }

    private void changeButtonToSea(JButton aimButton) {
        aimButton.setEnabled(false);
        URL imgURL = this.getClass().getResource("/img/seapic2.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
    }

    /**
     * Method marks the selected button as successful shot
     *
     * @param aimButton button to change
     */
    private void changeButtonToHit(JButton aimButton) {
        aimButton.setEnabled(false);
        URL imgURL = this.getClass().getResource("/img/playershiphitpic.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
    }

    private void changeButtonToShip(JButton aimButton) {
        aimButton.setEnabled(false);
        URL imgURL = this.getClass().getResource("/img/playershippic.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
    }

}
