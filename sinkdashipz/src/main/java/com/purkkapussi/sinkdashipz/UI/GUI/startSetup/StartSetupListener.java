/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.startSetup;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author ile
 */
public class StartSetupListener implements ActionListener{
    
    private GUI gui;
    private JButton easy;
    private JButton brainless;
    private JButton startGame;
    
    public StartSetupListener(GUI gui){
        this.gui = gui;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == brainless){
            gui.setDifficulty(Difficulty.BRAINLESS);
        }
        if (e.getSource()== easy){
            gui.setDifficulty(Difficulty.EASY);
        }
        if (e.getSource()==startGame){
            gui.startGame();
        }
    }

    void getComponents(JButton brainless, JButton easy, JButton startGame) {
        this.brainless = brainless;
        this.easy = easy;
        this.startGame = startGame;
    }


    
}
