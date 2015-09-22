/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.initialsetup;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class InitialSetup extends JPanel {

    private ClickListener clickListener;

    public InitialSetup(GUI gui) {
        this.clickListener = new ClickListener(gui);
        setLayout(new GridLayout(5,1));
        
    }
    public void createInitialSetup(){
        
        JButton newgame = new JButton("New Game");
        newgame.setBounds(50, 70, 90, 25);
        newgame.addActionListener(clickListener);
        this.add(newgame);

        JButton highscores = new JButton("High Scores");
        highscores.setBounds(50, 140, 90, 25);
        highscores.addActionListener(clickListener);
        highscores.setEnabled(false);
        this.add(highscores);
        
        JButton exit = new JButton("Exit");
        exit.setBounds(50, 210, 90, 25);
        exit.addActionListener(clickListener);
        this.add(exit);
        
        clickListener.getComponents(newgame, highscores, exit);

         JLabel mainPic = new JLabel("kuva t\u00E4h\u00E4n");
        mainPic.setBounds(220, 75, 50, 15);
        this.add(mainPic);

        JLabel titleLabel = new JLabel("Sinkdashipz v 0.1");
        titleLabel.setBounds(50, 30, 90, 14);
        this.add(titleLabel);
    }
}
