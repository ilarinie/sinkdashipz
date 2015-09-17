/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ile
 */
public class HullTest {
    
    @Test
    public void sameLocationHullsMatch(){
        Hull hull1 = new Hull(1,1);
        Hull hull2 = new Hull(1,1);
        
        assertEquals(true, hull1.equals(hull2));
    }
    
}
