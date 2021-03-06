/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.mainui;

import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the main UI shooting button grid buttons.
 * 
 */
public class MainUIListener implements ActionListener {

    private Location loc;
    private GraphicalUI gui;

    public MainUIListener(GraphicalUI gui, Location loc) {
        this.loc = loc;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        gui.shoot(loc);

    }

}
