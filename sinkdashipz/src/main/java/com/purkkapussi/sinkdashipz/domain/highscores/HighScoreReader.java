/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

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
            highscores = (ArrayList<HighScore>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        try {
            fin.close();
            ois.close();
        } catch (Exception e) {

        }
        return highscores;
    }

    private void openFile() {
        try {
            fin = new FileInputStream("highscores.ser");
            ois = new ObjectInputStream(fin);
        } catch (Exception e) {

        }
    }

}
