package com.purkkapussi.sinkdashipz.main;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.domain.Game;
import com.purkkapussi.sinkdashipz.tools.GameBoard;


public class Main {
    
    public static void main(String[] args){
        
        GameBoard gameboard = new GameBoard(10);
        Game game = new Game(gameboard);
        GUI gui = new GUI(game);
        gui.run();
        
    }
}
