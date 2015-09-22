/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.tools;

/**
 * AI Difficulty levels in enum
 * @author ile
 */
public enum Difficulty {
    BRAINLESS(1),EASY(2),CAPABLE(3),LITERALLYJESUS(4);
    
    private final int level;
    
    Difficulty(int level){
        this.level =level;
    }
    

}
