/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Class provides the functionality to write high scores to a file.
 *
 * @author ile
 */
public class HighScoreWriter {

    private final String filename;
    private FileOutputStream fout;
    private ObjectOutputStream oos;

    /**
     * Main constructor
     *
     * @param filename filename of the high score file.
     */
    public HighScoreWriter(String filename) {
        this.filename = filename;
    }

    /**
     * Method writes the given ArrayList of high scores into a file.
     *
     * @param highscores high scores to write
     */
    public void writeHighScores(ArrayList<HighScore> highscores) {
        try {
            openFile();
        } catch (Exception e) {
            System.out.println("Error opening highscore-file");
            return;
        }

        try {
            oos.writeObject(highscores);
        } catch (Exception e) {
            System.out.println("Error writing in the highscore-file");
        }
        try {
            closeFile();
        } catch (Exception e) {
            System.out.println("Error closing the highscore-file.");
        }

    }

    private void openFile() throws FileNotFoundException {

        try {
            fout = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fout);
        } catch (Exception e) {
            System.out.println("Error opening highscore-file");
        }
    }

    private void closeFile() {
        try {
            fout.close();
            oos.close();
        } catch (Exception e) {
            System.out.println("Error closing the highscore-file");
        }
    }

}
