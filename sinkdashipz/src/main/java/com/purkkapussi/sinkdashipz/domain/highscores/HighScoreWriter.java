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
 *
 * @author ile
 */
public class HighScoreWriter {

    private FileOutputStream fout;
    private ObjectOutputStream oos;

    public HighScoreWriter() {

    }

    public void openFile() throws FileNotFoundException {

        try {
            fout = new FileOutputStream("highscores.ser");
            oos = new ObjectOutputStream(fout);
        } catch (Exception e) {

        }
    }

    public void writeHighScores(ArrayList<HighScore> highscores) {
        try {
            openFile();
        } catch (Exception e) {
            return;
        }

        try {
            oos.writeObject(highscores);
        } catch (Exception e) {

        }

    }

}
