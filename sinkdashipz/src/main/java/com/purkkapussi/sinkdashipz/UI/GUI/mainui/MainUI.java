package com.purkkapussi.sinkdashipz.UI.GUI.mainui;

import com.purkkapussi.sinkdashipz.UI.GUI.GUI;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel that holds a button grid used by the player to target and shoot and a
 * label grid that shows the players own ships and AI's hits and misses.
 *
 * @author ile
 */
public class MainUI extends JPanel {

    protected JPanel mainHolder;
    protected JPanel aimHolder;
    protected JPanel playerShipHolder;

    private final int buttonWidth = 40;
    private final int buttonHeight = 40;
    private final int fontSize = 7;
    private final int gameBoardSize = 10;

    public MainUI(GUI gui) {

        playerShipHolder = new JPanel(new GridLayout(gameBoardSize + 1, gameBoardSize + 1));
        aimHolder = new JPanel(new GridLayout(gameBoardSize + 1, gameBoardSize + 1));
        mainHolder = new JPanel(new GridLayout(1, 2));
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(900, 500));
    }

    /**
     * method creates a new MainUI on the given graphical user interface
     *
     * @param gui GUI to be used
     */
    public void createMainUI(GUI gui) {
        updateMainUI(gui);
        mainHolder.add(aimHolder);
        mainHolder.add(playerShipHolder);
        this.add(mainHolder);
    }


    /**
     * Method to update the UI. Creates two grids with coordinate legends. Uses
     * changeButton to determine icon for the button.
     *
     * @see #changeButton()
     *
     * @param gui main graphical user interface
     */
    public void updateMainUI(GUI gui) {
        aimHolder.removeAll();
        playerShipHolder.removeAll();
        int counter = 0;
        for (int i = 0; i < gameBoardSize + 1; i++) {
            if (i == 0) {
                for (int k = 0; k < gameBoardSize + 1; k++) {
                    if (k == 0) {
                        aimHolder.add(createEmptyLabel());
                        playerShipHolder.add(createEmptyLabel());
                    } else {
                        JLabel coord = createCoordinateLabel(k - 1);
                        JLabel coord2 = createCoordinateLabel(k - 1);
                        aimHolder.add(coord);
                        playerShipHolder.add(coord2);
                    }
                }
            } else {
                for (int j = 0; j < gameBoardSize + 1; j++) {
                    if (j == 0) {
                        JLabel coord = createCoordinateLabel(counter);
                        JLabel coord2 = createCoordinateLabel(counter);
                        aimHolder.add(coord);
                        playerShipHolder.add(coord2);
                        counter++;
                    } else {
                        JButton aimButton = createButton();
                        JButton labelButton = createButton();
                        Location loc = new Location(i - 1, j - 1);
                        MainUIListener aimListener = new MainUIListener(gui, loc);
                        changeButton(gui, aimButton, loc, aimHolder);
                        changeButton(gui, labelButton, loc, playerShipHolder);
                        aimButton.addActionListener(aimListener);
                        aimHolder.add(aimButton);
                        playerShipHolder.add(labelButton);
                    }
                }
            }
        }
    }

    //END UPDATE METHODS
    //COORDINATE LABELS
    private JLabel createCoordinateLabel(int number) {
        JLabel coord = new JLabel("" + number, SwingConstants.CENTER);
        coord.setFont(new Font("Arial", Font.PLAIN, fontSize));
        coord.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        coord.setBackground(Color.BLACK);
        coord.setForeground(Color.GREEN);
        coord.setOpaque(true);
        return coord;
    }

    // UTTON CREATION AND MODIFICATION:
    /**
     * Method returns a new JButton to be used in the aim grid.
     *
     * @return new JButton
     */
    private JButton createButton() {
        JButton aimButton = new JButton();
        aimButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        aimButton.setFont(new Font("Arial", Font.PLAIN, fontSize));
        aimButton.setOpaque(true);
        aimButton.setBackground(Color.DARK_GRAY);
        aimButton.setForeground(Color.red);
        aimButton.setToolTipText("Use these buttons to mark a target for your shot.");
        return aimButton;
    }

    /**
     * Method marks the selected button with and X to indicate current target.
     *
     * @param aimButton button to change
     */
    private void markAimLocation(JButton aimButton) {
        aimButton.setText("X");
    }

    /**
     * Method marks the selected button as missed shot.
     *
     * @param aimButton button to change
     */
    private void changeButtonToMissed(JButton aimButton) {
        aimButton.setEnabled(false);

        URL imgURL = this.getClass().getResource("/img/seamiss.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));

    }

    private void changeButtonToSea(JButton aimButton) {
        aimButton.setEnabled(false);

        URL imgURL = this.getClass().getResource("/img/seapic2.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));

    }

    /**
     * Method marks the selected button as successful shot
     *
     * @param aimButton button to change
     */
    private void changeButtonToHit(JButton aimButton) {
        aimButton.setEnabled(false);
        URL imgURL = this.getClass().getResource("/img/playershiphitpic.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
        aimButton.setOpaque(true);
    }

    private void changeButtonToShip(JButton aimButton) {
        aimButton.setEnabled(false);
        URL imgURL = this.getClass().getResource("/img/playershippic.png");
        aimButton.setIcon(new ImageIcon(imgURL));
        aimButton.setDisabledIcon(new ImageIcon(imgURL));
    }

    private JLabel createEmptyLabel() {
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        emptyLabel.setOpaque(true);
        emptyLabel.setBackground(Color.BLACK);
        return emptyLabel;
    }
    /**
     * Method changes the icon on the given button to represent what is in the buttons location.
     * 
     * 
     * @param gui current GUI
     * @param button button to be changed
     * @param loc location of the button
     * @param panel panel the button belongs to
     * @return 
     */
    private JButton changeButton(GUI gui, JButton button, Location loc, JPanel panel) {
        
        if (panel == aimHolder && !gui.getGame().getEndgame()) {
            if (loc.equals(gui.getGame().getPlayerTargetLoc())) {
                markAimLocation(button);
            }
            if (gui.getGame().getPlayerShotLocs().contains(loc)) {
                changeButtonToMissed(button);
                if (gui.getGame().getInitialAIShipLocs().contains(loc)) {
                    changeButtonToHit(button);
                }
            }
        } else if (panel == playerShipHolder) {
            if (gui.getGame().getInitialPlayerShipLocs().contains(loc)) {
                if (gui.getAIHits().contains(loc)) {
                    changeButtonToHit(button);
                } else {
                    changeButtonToShip(button);
                }
            } else {
                if (gui.getAIHits().contains(loc)) {
                    changeButtonToMissed(button);
                } else {
                    changeButtonToSea(button);
                }
            }
        } else {
            if (gui.getGame().getPlayerShotLocs().contains(loc)) {
                changeButtonToMissed(button);
                if (gui.getGame().getInitialAIShipLocs().contains(loc)) {
                    changeButtonToHit(button);
                }
            } else if (gui.getGame().getInitialAIShipLocs().contains(loc)) {
                changeButtonToShip(button);
            } else {
                changeButtonToSea(button);
            }
        }
        return button;

    }
}
