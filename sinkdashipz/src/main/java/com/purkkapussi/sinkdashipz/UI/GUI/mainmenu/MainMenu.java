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

public class MainMenu extends JPanel {

    private final MainMenuListener clickListener;

    public MainMenu(GUI gui) {
        this.clickListener = new MainMenuListener(gui);
        setLayout(new GridLayout(5, 1));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.GREEN);
        this.setPreferredSize(new Dimension(100, 500));

    }

    public void createInitialSetup() {

        JButton newgame = new JButton("New Game");
        newgame.setBackground(Color.DARK_GRAY);
        newgame.setForeground(Color.WHITE);
        newgame.addActionListener(clickListener);
        this.add(newgame);

        JButton highscores = new JButton("High Scores");
        highscores.setBackground(Color.DARK_GRAY);
        highscores.setForeground(Color.WHITE);
        highscores.addActionListener(clickListener);
        this.add(highscores);

        JButton exit = new JButton("Exit");
        exit.setBackground(Color.DARK_GRAY);
        exit.setForeground(Color.WHITE);
        exit.addActionListener(clickListener);
        this.add(exit);

        clickListener.getComponents(newgame, highscores, exit);

        URL imgSmartURL = this.getClass().getResource("/img/mainpic.png");
        JLabel mainPic = new JLabel(new ImageIcon(imgSmartURL), JLabel.CENTER);
        this.add(mainPic);

        JLabel titleLabel = new JLabel("Sinkdashipz v 0.1");
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel);
    }
}
