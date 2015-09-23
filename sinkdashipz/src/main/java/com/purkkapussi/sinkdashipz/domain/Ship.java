package com.purkkapussi.sinkdashipz.domain;

import com.purkkapussi.sinkdashipz.tools.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Ship {

    private ArrayList<Hull> hullPieces;
    private Direction direction;
   

    public Ship() {
        this.hullPieces = new ArrayList<>();
        
    }

    public Ship(Hull hull) {
        hullPieces = new ArrayList<>();
        hullPieces.add(hull);
        this.direction = Direction.SOUTH;
    }

    public Ship(Hull hull, Direction direction) {
        hullPieces = new ArrayList<>();
        hullPieces.add(hull);
        this.direction = direction;
    }

    public void addHull(Hull hull) {
        hullPieces.add(hull);

    }
     
    public void addHullList(ArrayList<Hull> hulls){
        this.hullPieces.addAll(hulls);
        Collections.sort(this.hullPieces);
    }

    public void removeHull(Hull hull) {
        hullPieces.remove(hull);
    }

    public int getSize() {
        return hullPieces.size();
    }

    public ArrayList<Hull> getHulls() {
        return this.hullPieces;
    }

    public boolean overlaps(GameBoard gameBoard) {
        if (hullPieces.isEmpty()) {
            return false;
        }

        for (Hull hull : hullPieces) {
            if (hull.getLocation().getX() < 0 || hull.getLocation().getX() > gameBoard.getWidth() - 1 || hull.getLocation().getY() < 0 || hull.getLocation().getY() > gameBoard.getWidth() - 1) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String text = "";
        for (Hull hull : hullPieces) {
            text = text + hull.toString();
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

        if (this.getSize() == 1 && other.getSize() == 1) {
            if (this.getHulls().get(0) == other.getHulls().get(0)) {
                return true;
            }
        }

        for (Hull hull : this.hullPieces) {
            for (Hull otherHull : other.hullPieces) {
                if (hull.equals(otherHull)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }



}
