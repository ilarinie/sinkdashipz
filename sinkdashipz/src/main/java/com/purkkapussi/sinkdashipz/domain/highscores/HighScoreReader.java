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
 * Class used to reading the HighScore -file
 *
 */
public class HighScoreReader {

    private final String filename;
    
    /**
     * Main constructor
     * @param filename file name of the high score file.
     */
    public HighScoreReader(String filename) {
        this.filename = filename;
    }

    private FileInputStream fin;
    private ObjectInputStream ois;

    private void openFile() {
        try {
            File scores = new File(filename);
            if (!scores.exists()) {
                scores.createNewFile();
            }
            fin = new FileInputStream("highscores.ser");
            ois = new ObjectInputStream(fin);
        } catch (Exception e) {
            System.out.println();
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
            System.out.println();
        }
        try {
            fin.close();
            ois.close();
        } catch (Exception e) {
            System.out.println();
        }
        return highscores;
    }

}
