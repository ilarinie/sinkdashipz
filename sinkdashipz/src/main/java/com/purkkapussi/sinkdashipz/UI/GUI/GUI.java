/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI;

import com.purkkapussi.sinkdashipz.UI.GUI.endgame.EndGame;
import com.purkkapussi.sinkdashipz.UI.GUI.gamemenu.GameMenu;
import com.purkkapussi.sinkdashipz.UI.GUI.mainmenu.MainMenu;
import com.purkkapussi.sinkdashipz.UI.GUI.mainui.MainUI;
import com.purkkapussi.sinkdashipz.UI.GUI.welcomescreen.WelcomeScreen;
import com.purkkapussi.sinkdashipz.domain.Game;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class GUI implements Runnable {

    private Game game;

    private JFrame frame;
    private MainMenu initialSetup;
    private MainUI mainUI;
    private GameMenu gameMenu;
    private EndGame endGame;
    private WelcomeScreen welcomescreen;
    private boolean gamerunning;

    public GUI(Game game) {
        this.game = game;


    }

    @Override
    public void run() {
        frame = new JFrame("Sinkdashipz V 0.1");
        frame.setPreferredSize(new Dimension(1060, 550));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(new BorderLayout());

        createInitialSetup();
        createWelcomeScreen();

        frame.pack();
        frame.setVisible(true);

    }

    public void createInitialSetup() {
        initialSetup = new MainMenu(this);
        initialSetup.createInitialSetup();
        frame.getContentPane().add(initialSetup, BorderLayout.WEST);
    }

    public void createWelcomeScreen() {
        welcomescreen = new WelcomeScreen();
        frame.getContentPane().add(welcomescreen, BorderLayout.CENTER);
    }

    public void startGame() {

        mainUI = new MainUI(this);
        mainUI.createMainUI(this);
        frame.getContentPane().add(mainUI, BorderLayout.CENTER);

        gameMenu = new GameMenu(this);
        gameMenu.createGameMenu(this);
        frame.getContentPane().add(gameMenu, BorderLayout.SOUTH);

        gamerunning = true;
        frame.pack();

    }

    public void newGame() {


        if (endGame != null) {
            frame.getContentPane().remove(endGame);
            game.resetGame();
        }

        if (mainUI != null) {
            frame.getContentPane().remove(mainUI);
            frame.getContentPane().remove(gameMenu);
        }

        if (gamerunning && !this.game.getEndgame()) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game? Current game progress excluding highscores will be lost.", "Are you sure?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.NO_OPTION) {
                return;
            }
        }
        try {
            frame.getContentPane().remove(welcomescreen);
        } catch (Exception e) {

        }
        


        this.game = new Game(10,this);
        game.startGame();

        String name = JOptionPane.showInputDialog(null, "Enter your name");
        this.game.setPlayerName(name);
        String[] difficulties = {"BRAINLESS", "EASY", "CAPABLE", "LITERALLYJESUS"};
        int input;
        input = JOptionPane.showOptionDialog(null,
                "Please Choose the Difficulty Level", "Choose Difficulty", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[1]);

        this.game.setDifficulty(input);

        startGame();
    }

    public void showHighScore() {
        if (this.game.tenBestHighScores().equals("")) {
            JOptionPane.showMessageDialog(null, "No highscores to show yet.", "HighScores", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, this.game.tenBestHighScores(), "HighScores", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void exit() {
        System.exit(0);
    }

    public int getGameBoardSideLenght() {
        return game.getGameBoardSize();
    }

    /**
     * Method sets the players target location to the given location and updates
     * the view.
     *
     * @param loc Players target location
     */
    public void playerTargetLoc(Location loc) {
        this.game.setPlayerTargetLoc(loc);
        update();
    }

    /**
     * Method used by HitList and MainUI
     *
     * @return targets the AI has hit
     */
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
        //hitList.updateHitList(this);
        mainUI.updateMainUI(this);
        if (game.getEndgame()) {
            endGame();
        }

    }

    public boolean isLocationSelected() {
        if (null != game.getPlayerTargetLoc()) {
            return true;
        } else {
            return false;
        }
    }

    public Location targetLocation() {
        return game.getPlayerTargetLoc();
    }

    public void shoot() {

        game.playerShoot();
        update();
    }

    public void endGame() {
        frame.remove(gameMenu);
        mainUI.updateAimButtonsEndGame(this);
        endGame = new EndGame(this);
        endGame.createEndGame(this);
        frame.getContentPane().add(endGame, BorderLayout.SOUTH);

    }

    public void setDifficulty(Difficulty difficulty) {
        this.game.getAI().setDifficulty(difficulty);
    }

    public void setPlayerName(String name) {
        this.game.getPlayer().setName(name);
    }

    public int playerScore() {
        return this.game.getPlayer().getScore();
    }

    public String playerName() {
        return this.game.getPlayer().getName();
    }

    public String aiName() {
        return this.game.getAI().getName();
    }

    public String getWinner() {
        return this.game.getWinner();
    }

    public int getPlayerRank() {
        return this.game.getPlayerRank();
    }
    
    public void playerHitMessage(){
        JOptionPane.showMessageDialog(null, "Nice hit! Shoot again.","Great success!",JOptionPane.INFORMATION_MESSAGE);
    }

    public void playerMissMessage() {
        JOptionPane.showMessageDialog(null, "You missed! Better luck next time.","What a failure",JOptionPane.INFORMATION_MESSAGE);
    }
    public void aiHitMessage(){
        JOptionPane.showMessageDialog(null, this.game.getAI().getName()+ " hit your battleship!","Ouch!",JOptionPane.INFORMATION_MESSAGE);
    }
    public void aiMissMessage(){
        JOptionPane.showMessageDialog(null,  this.game.getAI().getName()+ " missed, stupid computers, amiright??.","Haha!",JOptionPane.INFORMATION_MESSAGE);
    }

    public void playerSinkAiShip() {
        JOptionPane.showMessageDialog(null,  "Noice! You sank AI's battleship! AI has "+this.game.getAI().fleetSize()+" ships left.","Haha!",JOptionPane.INFORMATION_MESSAGE);
    }

    public void aiSinkPlayerShip() {
        JOptionPane.showMessageDialog(null,  this.game.getAI().getName()+ " sank your battleship!",":(",JOptionPane.INFORMATION_MESSAGE);
    }
}
