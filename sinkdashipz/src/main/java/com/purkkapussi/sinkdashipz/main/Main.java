package com.purkkapussi.sinkdashipz.main;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.domain.Game;

public class Main {

    public static void main(String[] args) {

        Game game = new Game(10);
        GUI gui = new GUI(game);
        gui.run();

    }
}
