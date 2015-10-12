/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.mainmenu;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
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

    public MainMenu(GUI gui) {
        this.listener = new MainMenuListener(gui);
        setLayout(new GridLayout(5, 1));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.GREEN);
        this.setPreferredSize(new Dimension(150, 500));

    }

    public void createInitialSetup() {
        JButton newgame = new JButton("New Game");
        newgame.setBackground(Color.DARK_GRAY);
        newgame.setForeground(Color.WHITE);
        newgame.addActionListener(listener);
        this.add(newgame);

        JButton highscores = new JButton("HighScores");
        highscores.setBackground(Color.DARK_GRAY);
        highscores.setForeground(Color.WHITE);
        highscores.addActionListener(listener);
        this.add(highscores);

        JButton manual = new JButton("Manual");
        manual.setBackground(Color.DARK_GRAY);
        manual.setForeground(Color.WHITE);
        manual.addActionListener(listener);
        this.add(manual);

        JButton exit = new JButton("Exit");
        exit.setBackground(Color.DARK_GRAY);
        exit.setForeground(Color.WHITE);
        exit.addActionListener(listener);
        this.add(exit);

        listener.getComponents(newgame, highscores, manual, exit);

        URL imgSmartURL = this.getClass().getResource("/img/mainpic.png");
        JLabel mainPic = new JLabel(new ImageIcon(imgSmartURL), JLabel.CENTER);
        this.add(mainPic);

    }
}
