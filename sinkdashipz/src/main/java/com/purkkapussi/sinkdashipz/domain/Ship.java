package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.Location;
import java.util.ArrayList;

public class Ship {

    private ArrayList<Hull> hullPieces;
    

    public Ship() {
        hullPieces = new ArrayList<>();
    }

    public void addHull(Hull hull) {
        hullPieces.add(hull);

    }
    public int getSize(){
        return hullPieces.size();
    }

}
