/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author ile
 */
public class HighScoreReader {

    private FileInputStream fin;
    private ObjectInputStream ois;

    private void openFile() {
        try {
            File scores = new File("highscores.ser");
            if (!scores.exists()) {
                scores.createNewFile();
            }
            fin = new FileInputStream("highscores.ser");
            ois = new ObjectInputStream(fin);
        } catch (Exception e) {
            System.out.println("Error opening highscore-file.");
        }
    }

    /**
     * Method tries to read the high scores from the file "highscores.ser" and
     * closes the file after.
     *
     * @return ArrayList of HighScores
     */
    public ArrayList<HighScore> readHighScores() {
        ArrayList<HighScore> highscores = new ArrayList<>();

        openFile();
        try {
            if (ois != null) {
                highscores = (ArrayList<HighScore>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading highscores.");
        }
        try {
            fin.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error closing highscore-file.");
        }
        return highscores;
    }

}
