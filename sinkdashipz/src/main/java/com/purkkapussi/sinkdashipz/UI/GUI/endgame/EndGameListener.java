/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.endgame;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ile
 */
class EndGameListener implements ActionListener{

    private final GUI gui;
    public EndGameListener(GUI gui){
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.newGame();
    }
    
}
