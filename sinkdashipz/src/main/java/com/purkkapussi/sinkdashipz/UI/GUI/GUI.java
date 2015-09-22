/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI;

import com.purkkapussi.sinkdashipz.UI.GUI.initialsetup.InitialSetup;
import com.purkkapussi.sinkdashipz.UI.GUI.mainui.MainUI;
import com.purkkapussi.sinkdashipz.domain.Game;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI implements Runnable {

    private Game game;
    private JFrame frame;
    private InitialSetup initialSetup;
    private MainUI mainUI;

    public GUI(Game game) {
        this.game = game;
        
    }

    @Override
    public void run() {
        frame = new JFrame("Sinkdashipz V 0.1");
        frame.setPreferredSize(new Dimension(1000, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(new BorderLayout());

        createInitialSetup();

        frame.pack();
        frame.setVisible(true);

    }

    public void createInitialSetup() {
        initialSetup = new InitialSetup(this);
        initialSetup.createInitialSetup();
        frame.getContentPane().add(initialSetup, BorderLayout.WEST);
    }

    public void startGame() {
        //frame.getContentPane().remove(0);
        mainUI = new MainUI(this);
        mainUI.createMainUI(this);
        frame.getContentPane().add(mainUI, BorderLayout.CENTER);
        
        frame.pack();
        
    }

    public void showHighScore() {
        System.exit(0);
    }

    public void exit() {
        System.exit(0);
    }
    
    public int getGameBoardSideLenght(){
        return game.getGameBoardSize();
    }
    public void playerShootLoc(Location loc){
        this.game.setPlayerShootLoc(loc);
    }
    
    

}
