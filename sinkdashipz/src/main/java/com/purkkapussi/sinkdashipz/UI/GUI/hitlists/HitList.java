/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purkkapussi.sinkdashipz.UI.GUI.hitlists;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.awt.GridLayout;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ile
 */
public class HitList extends JPanel {

    protected JTextArea aiHits = new JTextArea("AI shot locations:\n");
    protected JTextArea playerHits = new JTextArea("Player shot locations:\n");

    public HitList(GUI gui) {
        this.setLayout(new GridLayout(2, 1));
    }

    public void createHitLists(GUI gui) {
        HashSet<Location> aiHitList = gui.getAIHits();
        HashSet<Location> playerHitList = gui.getPlayerHits();

        if (!aiHitList.isEmpty() || !playerHitList.isEmpty()) {
            for (Location loc : aiHitList) {
                aiHits.append(loc + "\n");
            }
            for (Location loc : playerHitList) {
                playerHits.append(loc + "\n");
            }
        }
        this.add(aiHits);
        this.add(playerHits);

    }

    public void updateHitList(GUI gui) {
        HashSet<Location> aiHitList = gui.getAIHits();
        HashSet<Location> playerHitList = gui.getPlayerHits();
        aiHits.setText("AI hit locations:\n");
        playerHits.setText("Player hit locations:\n");
        if (!aiHitList.isEmpty() || !playerHitList.isEmpty()) {
            for (Location loc : aiHitList) {
                if (gui.getAIShipLocs().contains(loc))
                    aiHits.append(loc + " (HIT!) \n");
                else{
                    aiHits.append(loc + " (MISS!) \n");
                }
                
            }
            for (Location loc : playerHitList) {
                
                if (gui.initialAIShipLocs().contains(loc))
                    playerHits.append(loc +" (HIT!) \n");
                else
                    playerHits.append(loc + " (MISS!) \n");
            }
        }

    }

}
