/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

/**
 *  Class provides the outlines for the game board.
 * 
 * @author ile
 */
public class GameBoard {

    private int width;

    public GameBoard(int width) {
        this.width = width;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
