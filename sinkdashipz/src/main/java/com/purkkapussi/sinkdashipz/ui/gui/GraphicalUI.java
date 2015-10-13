package com.purkkapussi.sinkdashipz.ui.gui;

import com.purkkapussi.sinkdashipz.ui.gui.infopanel.InfoPanel;
import com.purkkapussi.sinkdashipz.ui.gui.mainmenu.MainMenu;
import com.purkkapussi.sinkdashipz.ui.gui.mainui.MainUI;
import com.purkkapussi.sinkdashipz.ui.gui.sounds.SoundPlayer;
import com.purkkapussi.sinkdashipz.ui.gui.welcomescreen.WelcomeScreen;
import com.purkkapussi.sinkdashipz.domain.Game;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * Main GUI that holds all the other UI components.
 *
 * @author ile
 */
public class GraphicalUI implements Runnable {

    private Game game;
    private final SoundPlayer sounds = new SoundPlayer();
    private JFrame frame;
    private MainMenu mainmenu;
    private MainUI mainUI;
    private InfoPanel infoPanel;
    private WelcomeScreen welcomescreen;
    private boolean gamerunning;

    @Override
    public void run() {
        frame = new JFrame("Sinkdashipz V 0.1");
        frame.setPreferredSize(new Dimension(1060, 550));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        URL imgSmartURL = this.getClass().getResource("/img/icon.png");
        frame.setIconImage(new ImageIcon(imgSmartURL).getImage());
        frame.setResizable(false);
        this.game = new Game(10, this);
        createInitialScreen();
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method creates the components shown initially when the application is
     * started. These components are MainMenu and WelcomeScreen.
     */
    public void createInitialScreen() {
        mainmenu = new MainMenu(this);
        welcomescreen = new WelcomeScreen();
        frame.getContentPane().add(welcomescreen, BorderLayout.CENTER);
        frame.getContentPane().add(mainmenu, BorderLayout.WEST);
    }

    /**
     * Method creates the main game UI components which are the MainUI which
     * holds the grids to show ship locations and targeting and the game
     * menu/info-panel.
     */
    public void startGame() {
        mainUI = new MainUI(this);
        frame.getContentPane().add(mainUI, BorderLayout.CENTER);
        infoPanel = new InfoPanel(this);
        frame.getContentPane().add(infoPanel, BorderLayout.SOUTH);
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
        if (mainUI != null) {
            frame.getContentPane().remove(mainUI);
            frame.getContentPane().remove(infoPanel);
        }
        this.game = new Game(10, this);
        game.startGame();
        this.game.getPlayer().setName(name);
        this.game.getAI().setDifficulty(Difficulty.values()[input]);
        startGame();
    }
    /**
     * Method sets the players target location to the given location and updates
     * the view.
     *
     * @param loc Players target location
     */
    public void shoot(Location loc) {
        if (loc != null) {
            game.playerShoot(loc);
        }
        update();
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
     * Method updates the main game UI and the info panel.
     */
    private void update() {
        infoPanel.updateGameMenu(this);
        mainUI.updateMainUI(this);
        if (game.getEndgame()) {
            if (!this.game.getWinner().equals("AI")) {
                sounds.playVictorySound();
            } else {
                sounds.playLosingSound();
            }
        }
    }

    /**
     * Method shows a message informing the player of a scored hit
     */
    public void showPlayerHitMessage() {
        sounds.playHitSound();
        JOptionPane.showMessageDialog(null, "Nice hit! Shoot again.", "Great success!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void playMissSound() {
        sounds.playMissSound();
    }

    /**
     * Method displays a message informing player that an AI ship has been sunk.
     */
    public void showPlayerSinkAIShipMessage() {
        sounds.playDestroySound();
        JOptionPane.showMessageDialog(null, "Noice! You sank AI's battleship! AI has " + this.game.getAI().fleetSize() + " ships left.", "Haha!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method displays a message when AI sinks a player's ship
     */
    public void showAISinkPlayerShipMessage() {
        sounds.playAIDestroySound();
        JOptionPane.showMessageDialog(null, this.game.getAI().getName() + " sank your battleship!", ":(", JOptionPane.INFORMATION_MESSAGE);
    }

}
