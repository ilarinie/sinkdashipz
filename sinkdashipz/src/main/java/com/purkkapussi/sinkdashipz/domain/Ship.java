package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.Location;
import java.util.ArrayList;

public class Ship {

    private ArrayList<Hull> hullPieces;
    

    public Ship() {
        hullPieces = new ArrayList<>();
    }
    
    public Ship(Hull hull){
        hullPieces = new ArrayList<>();
        hullPieces.add(hull);
    }

    public void addHull(Hull hull) {
        hullPieces.add(hull);

    }
    
    public void removeHull(Hull hull){
        hullPieces.remove(hull);
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
        
        if (this.getSize()==1 && other.getSize() == 1){
            if (this.getHulls().get(0) == other.getHulls().get(0))
                return true;
        }
        
        
        
        for (Hull hull : this.hullPieces){
            for (Hull otherHull : other.hullPieces){
                if (hull.equals(otherHull))
                    return true;
            }
        }
        
        return false;
    }
    
    
    

}
