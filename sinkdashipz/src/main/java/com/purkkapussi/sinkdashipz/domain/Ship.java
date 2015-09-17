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
    public ArrayList<Hull> getHulls(){
        return this.hullPieces;
    }
    
    
    public String toString(){
        String text = "";
        for (Hull hull : hullPieces){
            text = text+hull.toString();
        }
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ship other = (Ship) obj;
        for (Hull hull : this.hullPieces){
            for (Hull otherHull : other.hullPieces){
                if (hull.getLocation() == otherHull.getLocation())
                    return true;
            }
        }
        
        return false;
    }
    
    
    

}
