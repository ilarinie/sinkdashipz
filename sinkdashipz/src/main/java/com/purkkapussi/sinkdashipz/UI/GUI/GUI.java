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
import java.net.URL;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Main GUI that holds all the other UI components.
 *
 * @author ile
 */
public class GUI implements Runnable {

    private Game game;

    private JFrame frame;
    private MainMenu initialSetup;
    private MainUI mainUI;
    private GameMenu gameMenu;
    private EndGame endGame;
    private WelcomeScreen welcomescreen;
    private boolean gamerunning;

    public GUI() {

    }

    @Override
    public void run() {
        frame = new JFrame("Sinkdashipz V 0.1");
        frame.setPreferredSize(new Dimension(1060, 550));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(new BorderLayout());

        URL imgSmartURL = this.getClass().getResource("/img/icon.png");
        frame.setIconImage(new ImageIcon(imgSmartURL).getImage());

        frame.setResizable(false);
        createInitialScreen();

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Method creates the components shown initially when the application is
     * started. These components are MainMenu and WelcomeScreen.
     */
    public void createInitialScreen() {
        initialSetup = new MainMenu(this);
        initialSetup.createInitialSetup();
        welcomescreen = new WelcomeScreen();
        frame.getContentPane().add(welcomescreen, BorderLayout.CENTER);
        frame.getContentPane().add(initialSetup, BorderLayout.WEST);
    }

    /**
     * Method creates the main game UI components which are the MainUI which
     * holds the grids to show ship locations and targeting and the game
     * menu/info-panel.
     */
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

    /**
     * New game method. If a game is currently running, the method will ask
     * confirmation to start a new game. Method removes last games UI from the
     * screen if applicable. After, the method will start a new Game and asks
     * for players name and difficulty level. Name can be blank, but if cancel
     * is pressed or a dialog closed, the method will exit without starting a
     * new game.
     */
    public void newGame() {

        String[] difficulties = {"BRAINLESS", "EASY", "CAPABLE", "LITERALLYJESUS"};

        if (gamerunning && !this.game.getEndgame()) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game? Current game progress excluding highscores will be lost.", "Are you sure?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION || reply == -1) {
                return;
            }
        }

        frame.getContentPane().remove(welcomescreen);

        String name = JOptionPane.showInputDialog(null, "Enter your desired nickname", "Who do you want to be?", JOptionPane.INFORMATION_MESSAGE);
        if (name == null) {
            return;
        }

        int input = JOptionPane.showOptionDialog(null,
                "Please Choose the Difficulty Level", "Choose Difficulty", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[1]);
        if (input == -1) {
            return;
        }
        if (endGame != null) {
            frame.getContentPane().remove(endGame);
            frame.getContentPane().remove(mainUI);
            frame.getContentPane().remove(gameMenu);
        }
        this.game = new Game(10, this);
        game.startGame();
        this.game.getPlayer().setName(name);
        this.game.getAI().setDifficulty(Difficulty.values()[input]);

        startGame();
    }

    /**
     * Method pops up a window showing the top 10 high scores.
     */
    public void showHighScore() {
        if (this.game.tenBestHighScores().equals("")) {
            JOptionPane.showMessageDialog(null, "No highscores to show yet.", "HighScores", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, this.game.tenBestHighScores(), "HighScores", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Method closes the process after confirmation from the user.
     */
    public void exit() {

        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Quit?", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION || reply == -1) {
            return;
        }

        System.exit(0);
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

    /**
     * Returns the game currently running.
     *
     * @return game being played currently
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Method updates the main game UI and the info panel, switches to end game
     * screen if the game has ended.
     */
    private void update() {
        gameMenu.updateGameMenu(this);
        mainUI.updateMainUI(this);
        if (game.getEndgame()) {
            endGame();
        }
    }

    /**
     * Method to check if player has selected a target location. Used to enable
     * and disable the shooting button accordingly.
     *
     * @return true if player has a target selected
     */
    public boolean locationSelected() {
        return null != game.getPlayerTargetLoc();
    }

    /**
     * Method tells the game to shoot at the target coordinates and updates the
     * UI after.
     */
    public void playerShoot() {
        game.playerShoot();
        update();
    }

    private void endGame() {
        frame.remove(gameMenu);
        mainUI.updateAimButtonsEndGame(this);
        endGame = new EndGame(this);
        endGame.createEndGame(this);
        frame.getContentPane().add(endGame, BorderLayout.SOUTH);
    }

    //INGAME MESSAGES
    /**
     * Method shows a message informing the player of a scored hit
     */
    public void showPlayerHitMessage() {
        JOptionPane.showMessageDialog(null, "Nice hit! Shoot again.", "Great success!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method displays a message informing player that an AI ship has been sunk.
     */
    public void showPlayerSinkAIShipMessage() {
        JOptionPane.showMessageDialog(null, "Noice! You sank AI's battleship! AI has " + this.game.getAI().fleetSize() + " ships left.", "Haha!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method displays a message when AI sinks a player's ship
     */
    public void showAISinkPlayerShipMessage() {
        JOptionPane.showMessageDialog(null, this.game.getAI().getName() + " sank your battleship!", ":(", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method pops up a dialog showing the game manual.
     */
    public void showManual() {
        JLabel lbl = new JLabel(new ImageIcon(this.getClass().getResource("/img/manual.png")));
        JOptionPane.showMessageDialog(null, lbl, "Manual",
                JOptionPane.PLAIN_MESSAGE, null);
    }
}
