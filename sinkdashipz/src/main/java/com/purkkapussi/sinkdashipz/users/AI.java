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
    private final ArrayList<Location> shotLocs = new ArrayList<>();

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

    public ArrayList<Location> getShotLocs() {
        return shotLocs;
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
     * @return Location based on the game board and difficulty
     *
     */
    public Location shoot(int gameBoardSize, Actor actor) {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (!actor.getShips().isEmpty() && this.difficulty != null) {
            switch (this.difficulty) {
                case BRAINLESS:
                    Location newLoc = new Location(gameBoardSize);
                    shotLocs.add(newLoc);
                    return newLoc;
                case EASY:
                    return shotTargeter(gameBoardSize, actor, chance, 25);
                case CAPABLE:
                    return shotTargeter(gameBoardSize, actor, chance, 40);
                case LITERALLYJESUS:
                    return jesusShoot(gameBoardSize, actor);
            }
        }
        return new Location(gameBoardSize);
    }

    /**
     * Returns a location of a piece of actors ship, if the randomized number is
     * below the hit chance.
     *
     * @param gameBoardSize size of the game board
     * @param actor actor to shoot at
     * @param randomized random number between 1-100
     * @param hitChance hit chance percent
     *
     * @return Location to shoot at
     *
     */
    public Location shotTargeter(int gameBoardSize, Actor actor, int randomized, int hitChance) {
        if (randomized < hitChance) {
            shotLocs.add(actor.getShips().get(0).getLocs().get(0));
            return actor.getShips().get(0).getLocs().get(0);
        } else {
            Location loc = new Location(gameBoardSize);
            shotLocs.add(loc);
            return loc;
        }
    }
    
    /**
     * Shooting method of the "Jesus" difficulty. Hits every time.
     * 
     * @param gameBoardSize size of the game board
     * @param actor actor to shoot at
     * 
     * @return Location to shoot at
     */
    public Location jesusShoot(int gameBoardSize, Actor actor) {
        shotLocs.add(actor.getShips().get(0).getLocs().get(0));
        return actor.getShips().get(0).getLocs().get(0);
    }
}
