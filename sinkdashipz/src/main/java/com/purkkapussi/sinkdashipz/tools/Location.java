package com.purkkapussi.sinkdashipz.tools;


public class Location {
    
    private int x;
    private int y;
    
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Location moveEast(){
        this.x = this.x + 1;
        return this;
    }
    public Location moveSouth(){
        this.y = this.y-1;
        return this;
    }
    
    public String toString(){
        
        return "["+this.x+","+this.y+"]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
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
        final Location other = (Location) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    
    
}
