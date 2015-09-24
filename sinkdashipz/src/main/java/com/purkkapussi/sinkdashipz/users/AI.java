/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Game;
import com.purkkapussi.sinkdashipz.tools.GameBoard;
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

    private ArrayList<Ship> laivat;
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
        laivat = new ArrayList<>();
        this.difficulty = difficulty;
        super.setName(difficulty + " - AI");

    }

    /**
     * Constructs a new AI actor with the easiest difficulty setting
     */
    public AI() {
        laivat = new ArrayList<>();
        super.setName("AI - BRAINLESS");
        this.difficulty = Difficulty.BRAINLESS;
    }

    /**
     * Method determines the next Location the AI will shoot at. The location
     * depends on the Difficulty level. The method is not yet fully implemented
     * and will always return a random Location on the given game board. If the
     * actor given as a parameter has no ships, the method will throw
     * IllegalArgumentException.
     *
     *
     * @param gameBoard GameBoard to target at
     * @param actor Target actor
     * @return
     * @throws IllegalArgumentException
     */
    public Location shoot(GameBoard gameBoard, Actor actor) throws IllegalArgumentException {
        if (actor.getShips().isEmpty()) {
            throw new IllegalArgumentException("No ships to shoot at");
        }
        if (this.difficulty == Difficulty.BRAINLESS || !lastHitSuccess) {
            return new Location(gameBoard);
        }
        if (this.difficulty == Difficulty.EASY) {
            return new Location(gameBoard);
        }
        if (this.difficulty == Difficulty.CAPABLE) {
            return new Location(gameBoard);
        } else {
            return jesusShoot(actor);
        }

    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    

    /**
     * Method not yet implemented
     *
     * @param actor
     * @return
     */
    private Location easyShoot(Actor actor) {
        return new Location(1, 1);
    }

    /**
     * Method not yet implemented
     *
     * @param actor
     * @return
     */
    private Location capableShoot(Actor actor) {
        return new Location(1, 1);
    }

    /**
     * Method not yet implemented
     *
     * @param actor
     * @return
     */
    private Location jesusShoot(Actor actor) {
        return actor.getShips().get(0).getHulls().get(0).getLocation();
    }
    
    

}
