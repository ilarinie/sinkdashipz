/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.startSetup;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ile
 */
public class StartSetup extends JPanel {

    private StartSetupListener listener;

    public StartSetup(GUI gui) {
        this.setPreferredSize(new Dimension(1200, 800));
        this.listener = new StartSetupListener(gui);
        this.setLayout(new BorderLayout());
    }
    public void createStartSetup() {
        
        JPanel holder = new JPanel();
        holder.setPreferredSize(new Dimension(500,500));
        holder.setLayout(new GridLayout(4,1));

        JTextField playerName = new JTextField("player name");
        playerName.setPreferredSize(new Dimension(40,100));

        JButton brainless = new JButton("Brainless");
        brainless.addActionListener(listener);
        JButton easy = new JButton("Easy");
        easy.addActionListener(listener);
        
        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(listener);
        
        listener.getComponents(brainless, easy, startGame);
        
        holder.add(playerName);
        holder.add(brainless);
        holder.add(easy);
        
        this.add(holder,BorderLayout.CENTER);

    }

}
