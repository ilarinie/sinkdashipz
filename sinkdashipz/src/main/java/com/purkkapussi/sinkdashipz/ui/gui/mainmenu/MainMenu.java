/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.mainmenu;

import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Main menu panel that holds New Game, High Score, Manual and Exit buttons.
 *
 * @author ile
 */
public class MainMenu extends JPanel {
    
    private final MainMenuListener listener;
    private DialogHandler handler;
    
    public MainMenu(GraphicalUI gui) {
        handler = new DialogHandler(gui);
        this.listener = new MainMenuListener(gui, handler);
        setLayout(new GridLayout(5, 1));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.GREEN);
        this.setPreferredSize(new Dimension(150, 500));
        createMainMenu();
    }
    
    private void createMainMenu() {
        JButton newgame = new JButton("New Game");
        buttonSetup(newgame);
        this.add(newgame);
        
        JButton highscores = new JButton("HighScores");
        buttonSetup(highscores);
        this.add(highscores);
        
        JButton manual = new JButton("Manual");
        buttonSetup(manual);
        this.add(manual);
        
        JButton exit = new JButton("Exit");
        buttonSetup(exit);
        this.add(exit);
        
        listener.getComponents(newgame, highscores, manual, exit);
        
        URL imgSmartURL = this.getClass().getResource("/img/mainpic.png");
        JLabel mainPic = new JLabel(new ImageIcon(imgSmartURL), JLabel.CENTER);
        this.add(mainPic);
        
    }
    
    private void buttonSetup(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.addActionListener(listener);
        button.setFocusPainted(false);
    }
}
