/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain.highscores;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author ile
 */
public class HighScoreReader {

    private FileInputStream fin;
    private ObjectInputStream ois;

    public void openFile() {
        try {
            fin = new FileInputStream("highscores.ser");
            ois = new ObjectInputStream(fin);
        } catch (Exception e) {

        }
    }

    public ArrayList<HighScore> readHighScores() {
        ArrayList<HighScore> highscores = new ArrayList<>();
        try {
            openFile();

        } catch (Exception e) {

        }

        try {
            highscores = (ArrayList<HighScore>) ois.readObject();
        } catch (Exception e) {

        }
        return highscores;
    }

}
