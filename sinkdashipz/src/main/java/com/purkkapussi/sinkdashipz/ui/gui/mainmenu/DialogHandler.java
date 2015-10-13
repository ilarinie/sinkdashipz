/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.mainmenu;

import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Class provides dialogs that are accessible trough the main menu.
 *
 * @author ile
 */
public class DialogHandler {

    private final GraphicalUI gui;

    public DialogHandler(GraphicalUI gui) {
        this.gui = gui;
    }

    /**
     * Method pops up a dialog showing the high scores.
     */
    public void showHighScores() {
        if (this.gui.getGame() == null) {
            JOptionPane.showMessageDialog(null, "No highscores to show yet.", "HighScores", JOptionPane.INFORMATION_MESSAGE);
        } else if (this.gui.getGame().tenBestHighScores().equals("")) {
            JOptionPane.showMessageDialog(null, "No highscores to show yet.", "HighScores", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, this.gui.getGame().tenBestHighScores(), "HighScores", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Method pops up a dialog showing the game manual.
     */
    public void showManual() {
        JLabel lbl = new JLabel(new ImageIcon(this.getClass().getResource("/img/manual.png")));
        JOptionPane.showMessageDialog(null, lbl, "Manual",
                JOptionPane.PLAIN_MESSAGE, null);
    }

    /**
     * Method asks for user confirmation before closing the application.
     */
    public void exit() {
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Quit?", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION || reply == -1) {
            return;
        }
        System.exit(0);
    }
}
