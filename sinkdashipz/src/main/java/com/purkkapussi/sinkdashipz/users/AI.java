/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.users;

import com.purkkapussi.sinkdashipz.domain.Ship;
import com.purkkapussi.sinkdashipz.tools.Difficulty;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.util.ArrayList;
import java.util.Random;

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
     * depends on the Difficulty level. The higher the difficulty level, the
     * more chance the AI has of hitting players ships.
     *
     *
     * @param gameBoardSize size of the game board to shoot at
     * @param actor Target actor
     * @return
     *
     */
    public Location shoot(int gameBoardSize, Actor actor) {

        if (!actor.getShips().isEmpty()) {
            if (this.difficulty == Difficulty.BRAINLESS) {
                return new Location(gameBoardSize);
            }
            if (this.difficulty == Difficulty.EASY) {
                return easyShoot(gameBoardSize, actor);
            }
            if (this.difficulty == Difficulty.CAPABLE) {
                return capableShoot(gameBoardSize, actor);
            }
            if (this.difficulty == Difficulty.LITERALLYJESUS) {
                return jesusShoot(gameBoardSize, actor);
            }
        } 
            return new Location(gameBoardSize);
        

    }

    private Location easyShoot(int gameBoardSize, Actor actor) {
        Random random = new Random();
        int aimChance = random.nextInt(100);
        if (aimChance < 25) {
            return actor.getShips().get(0).getHulls().get(0);
        } else {
            return new Location(gameBoardSize);
        }
    }

    private Location capableShoot(int gameBoardSize, Actor actor) {
        Random random = new Random();
        int aimChance = random.nextInt(100);
        if (aimChance < 40) {
            return actor.getShips().get(0).getHulls().get(0);
        } else {
            return new Location(gameBoardSize);
        }
    }

    private Location jesusShoot(int gameBoardSize, Actor actor) {
        if (!actor.getShips().isEmpty()) {
            return actor.getShips().get(0).getHulls().get(0);
        } else {
            return new Location(gameBoardSize);
        }
    }

}
