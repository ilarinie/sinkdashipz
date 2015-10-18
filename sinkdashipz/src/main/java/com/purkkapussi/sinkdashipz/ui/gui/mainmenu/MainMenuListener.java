/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.mainmenu;

import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Listener for the main menu buttons.
 * @author ile
 */
public class MainMenuListener implements ActionListener {

    private GraphicalUI gui;
    private DialogHandler handler;
    private JButton newGame;
    private JButton highScores;
    private JButton exit;
    private JButton manual;

    public MainMenuListener(GraphicalUI gui, DialogHandler handler) {
        this.gui = gui;
        this.handler = handler;
    }

    public void getComponents(JButton newGame, JButton highScores, JButton manual, JButton exit) {
        this.newGame = newGame;
        this.highScores = highScores;
        this.manual = manual;
        this.exit = exit;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == newGame) {
            gui.newGame();
        }
        if (ae.getSource() == highScores) {
            handler.showHighScores();
        }
        if (ae.getSource() == manual) {
            handler.showManual();
        }
        if (ae.getSource() == exit) {
            handler.exit();
        }
    }
}
