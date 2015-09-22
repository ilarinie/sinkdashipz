/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.mainmenu;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainMenuListener implements ActionListener {

    private GUI gui;
    private JButton newGame;
    private JButton highScores;
    private JButton exit;

    public MainMenuListener(GUI gui) {
        this.gui = gui;
    }

    public void getComponents(JButton newGame, JButton highScores, JButton exit) {
        this.newGame = newGame;
        this.highScores = highScores;
        this.exit = exit;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == newGame) {
            gui.newGame();
        }
        if (ae.getSource() == highScores) {
            gui.showHighScore();
        }
        if (ae.getSource() == exit) {
            gui.exit();
        }
    }
}
