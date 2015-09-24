/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;


import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import com.purkkapussi.sinkdashipz.tools.Location;
import java.util.ArrayList;

/**
 * Class provides AI specific functionality for the Actor class. The class also
 * handles the determination of AI's next hit.
 *
 * @author ile
 */
public class AI extends Actor {

    private final ArrayList<Ship> ships;
    private Difficulty difficulty;
    private boolean lastHitSuccess;
    private Location lastHitLoc;

    /**
     * Constructs a new AI actor with the given Difficulty level. Also sets a
     * name for the AI based on the Difficulty level.
     *
     * @param difficulty AI Difficulty level
     */
    public AI(Difficulty difficulty) {
        ships = new ArrayList<>();
        this.difficulty = difficulty;
        super.setName(difficulty + " AI");
    }

    /**
     * Constructs a new AI actor with the easiest difficulty setting
     */
    public AI() {
        ships = new ArrayList<>();
        this.difficulty = Difficulty.BRAINLESS;
        super.setName(difficulty + " AI");
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        super.setName(difficulty + " AI");
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    //AI SHOOTING METHODS
    /**
     * Method determines the next Location the AI will shoot at. The location
     * depends on the Difficulty level. The method is not yet fully implemented
     * and will always return a random Location on the given game board.
     *
     *
     * @param gameBoardSize size of the game board to shoot at
     * @param actor Target actor
     * @return
     * 
     */
    public Location shoot(int gameBoardSize, Actor actor){
        if (this.difficulty == Difficulty.BRAINLESS || !lastHitSuccess) {
            return new Location(gameBoardSize);
        }
        if (this.difficulty == Difficulty.EASY) {
            return new Location(gameBoardSize);
        }
        if (this.difficulty == Difficulty.CAPABLE) {
            return new Location(gameBoardSize);
        } else {
            return new Location(gameBoardSize);
        }

    }

}
/**
    /**
     * Method not yet implemented
     *
     * @param actor
     * @return
   
    private Location easyShoot(Actor actor) {
        return new Location(1, 1);
    }

    /**
     * Method not yet implemented
     *
     * @param actor
     * @return
     
    private Location capableShoot(Actor actor) {
        return new Location(1, 1);
    }

    /**
     * Method not yet implemented
     *
     * @param actor
     * @return
    
    private Location jesusShoot(Actor actor) {
        return actor.getShips().get(0).getHulls().get(0).getLocation();
    }
*/
