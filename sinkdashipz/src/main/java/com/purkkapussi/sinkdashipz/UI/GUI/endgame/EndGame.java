/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.endgame;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGame extends JPanel {

    protected JLabel gameEndedText = new JLabel();
    protected JLabel winnerText = new JLabel();
    protected JLabel playerScoreText = new JLabel();
    protected JLabel playerScore = new JLabel();

    public EndGame(GUI gui) {
        this.setBackground(Color.BLACK);
        gameEndedText.setForeground(Color.WHITE);
        winnerText.setForeground(Color.WHITE);
        playerScoreText.setForeground(Color.WHITE);
        playerScore.setForeground(Color.WHITE);
    }

    public void createEndGame(GUI gui) {
        this.setLayout(new GridLayout(2, 4));
        this.add(gameEndedText);
        this.add(winnerText);
        this.add(playerScoreText);
        this.add(playerScore);
        updateEndGame(gui);
    }

    public void updateEndGame(GUI gui) {
        gameEndedText.setText("The Game has ended.");
        winnerText.setText(gui.getWinner() + " won.");
        playerScoreText.setText("Your score was: " + gui.getPlayerScore());
        playerScore.setText("Your score puts you on the  " + gui.getPlayerRank() + ". spot in the highscores");
    }
}
