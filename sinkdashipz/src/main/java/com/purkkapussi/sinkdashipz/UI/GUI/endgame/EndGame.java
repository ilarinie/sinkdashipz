/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.endgame;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ile
 */
public class EndGame extends JPanel{
    
    public EndGame(GUI gui){
        this.setPreferredSize(new Dimension(1200,800));
        this.add(new JLabel("Game ended"));
    }
    
}
