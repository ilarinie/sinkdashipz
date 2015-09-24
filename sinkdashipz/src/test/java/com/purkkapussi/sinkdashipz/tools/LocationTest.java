package com.purkkapussi.sinkdashipz.tools;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class LocationTest {

    public Location location1 = new Location(1, 1);

    @Test
    public void locationEqualsSameLocation() {

        Location location2 = new Location(1, 1);

        assertEquals(location1, location2);

    }

    @Test
    public void differentLocationsNotEqual() {

        Location location2 = new Location(2, 1);

        assertThat(location1, not(equalTo(location2)));
    }

    @Test
    public void newDifLocationDontMatch() {

        assertThat(new Location(2, 2), not(equalTo(new Location(2, 3))));
    }

    @Test
    public void newLocationsMatch() {

        assertEquals(new Location(2, 2), new Location(2, 2));
    }

    @Test
    public void randomLocationOnGameBoard() {
        
        Location randLoc = new Location(10);
        assertEquals(true, (randLoc.getX() < 10 && randLoc.getY() < 10));

    }

    @Test
    public void invalidLocOutOfBounds() {
        Location negLoc = new Location(-4, 2);
        assertEquals(true, negLoc.locOutOfBounds(10));
    }

    @Test
    public void tooHighLocOutOfBounds() {
        Location negLoc = new Location(4, 17);
        assertEquals(true, negLoc.locOutOfBounds(10));
    }
    
    @Test
    public void normalLocsDontOutOfBound(){
        Location normLoc = new Location (4,6);
        Location normLoc2 = new Location (6,7);
        
        assertEquals(false, location1.locOutOfBounds(10));
        assertEquals(false, normLoc.locOutOfBounds(10));
        assertEquals(false, normLoc2.locOutOfBounds(10));
    }
    @Test
    public void movingLocationTest(){
        location1.moveWest();
        assertEquals(new Location(0,1), location1);
        location1.moveEast();
        assertEquals(new Location(1,1), location1);
        location1.moveSouth();
        assertEquals(new Location(1,0), location1);
        location1.moveNorth();
        assertEquals(new Location(1,1), location1);
    }
    
}
