/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.gamemenu;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ile
 */
public class GameMenuListener implements ActionListener{
    
    private GUI gui;
    public GameMenuListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.shoot();
    }
    
}
