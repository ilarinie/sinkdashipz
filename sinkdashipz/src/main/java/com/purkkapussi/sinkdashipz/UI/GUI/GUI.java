/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI;

import com.purkkapussi.sinkdashipz.UI.GUI.endgame.EndGame;
import com.purkkapussi.sinkdashipz.UI.GUI.gamemenu.GameMenu;
import com.purkkapussi.sinkdashipz.UI.GUI.hitlists.HitList;
import com.purkkapussi.sinkdashipz.UI.GUI.initialsetup.InitialSetup;
import com.purkkapussi.sinkdashipz.UI.GUI.mainui.MainUI;
import com.purkkapussi.sinkdashipz.domain.Game;
import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI implements Runnable {

    private Game game;
    private JFrame frame;
    private InitialSetup initialSetup;
    private MainUI mainUI;
    private GameMenu gameMenu;
    private HitList hitList;
    private EndGame endGame;

    public GUI(Game game) {
        this.game = game;

    }

    @Override
    public void run() {
        frame = new JFrame("Sinkdashipz V 0.1");
        frame.setPreferredSize(new Dimension(1600, 600));
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
        if (endGame!=null){
            frame.getContentPane().remove(endGame);
            game.resetGame();
        }
        
        mainUI = new MainUI(this);
        mainUI.createMainUI(this);
        frame.getContentPane().add(mainUI, BorderLayout.CENTER);

        gameMenu = new GameMenu(this);
        gameMenu.createGameMenu(this);
        frame.getContentPane().add(gameMenu, BorderLayout.SOUTH);

        hitList = new HitList(this);
        hitList.createHitLists(this);
        frame.getContentPane().add(hitList, BorderLayout.EAST);

        frame.pack();

    }

    public void showHighScore() {
        System.exit(0);
    }

    public void exit() {
        System.exit(0);
    }

    public int getGameBoardSideLenght() {
        return game.getGameBoardSize();
    }

    public void playerShootLoc(Location loc) {

        this.game.setPlayerShootLoc(loc);
        update();
    }

    public boolean isTherePlayerShip(int x, int y) {
        return game.isTherePlayerShip(x, y);
    }

    public HashSet<Location> getAIHits() {
        return game.getAiShootLocs();
    }

    public HashSet<Location> getPlayerHits() {
        return game.getPlayerShootLocs();
    }

    public HashSet<Location> getAIShipLocs() {
        return game.getAI().shipLocs();
    }

    public int getAIFleetSize() {
        return game.getAI().fleetSize();
    }

    public int getPlayerFleetSize() {
        return game.getPlayer().fleetSize();
    }

    public HashSet<Location> initialAIShipLocs() {
        return game.getInitialAIShipLocs();
    }

    public HashSet<Location> initialPlayerShipLocs() {
        return game.getInitialPlayerShipLocs();
    }

    public void update() {
        gameMenu.updateGameMenu(this);
        hitList.updateHitList(this);
        mainUI.updateMainUI(this);

    }

    public boolean isLocationSelected() {
        if (null != game.getPlayerShootLoc()) {
            return true;
        } else {
            return false;
        }
    }

    public Location targetLocation() {
        return game.getPlayerShootLoc();
    }

    public void shoot() {

        game.playerShoot();
        update();
    }

    public void endGame() {
        frame.remove(mainUI);
        endGame = new EndGame(this);
        frame.getContentPane().add(endGame, BorderLayout.CENTER);
        
    }

}
