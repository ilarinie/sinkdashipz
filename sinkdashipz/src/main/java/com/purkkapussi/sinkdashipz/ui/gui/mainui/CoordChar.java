/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.ui.gui.mainui;

/**
 * Used to determine a letter corresponding to a coordinate by the main UI.
 *
 * @author ile
 */
public enum CoordChar {

    A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10);

    private final int coord;

    CoordChar(int coord) {
        this.coord = coord;
    }
}
