/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.welcomescreen;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ile
 */
public class WelcomeScreen extends JPanel {
    
    public WelcomeScreen(){
        this.setPreferredSize(new Dimension(810, 400));
        this.setLayout(new GridLayout(1,1));
        URL imgURL = this.getClass().getResource("/img/welcomescreen.png");
        JLabel image = new JLabel(new ImageIcon(imgURL));
        this.add(image);
    }
}
