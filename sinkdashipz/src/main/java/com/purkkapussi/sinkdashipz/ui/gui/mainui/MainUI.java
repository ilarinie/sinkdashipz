package com.purkkapussi.sinkdashipz.ui.gui.mainui;

import com.purkkapussi.sinkdashipz.ui.gui.GraphicalUI;
import com.purkkapussi.sinkdashipz.domain.Location;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

    private final ButtonChanger changer;

    protected JPanel mainHolder;
    protected JPanel aimHolder;
    protected JPanel playerShipHolder;

    private final int buttonWidth = 40;
    private final int buttonHeight = 40;
    private final int fontSize = 7;
    private final int gameBoardSize = 10;

    public MainUI(GraphicalUI gui) {
        changer = new ButtonChanger(gui);
        playerShipHolder = new JPanel(new GridLayout(gameBoardSize + 1, gameBoardSize + 1));
        aimHolder = new JPanel(new GridLayout(gameBoardSize + 1, gameBoardSize + 1));
        mainHolder = new JPanel(new GridLayout(1, 2));
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(900, 500));
        createMainUI(gui);
    }

    /**
     * method creates a new MainUI on the given graphical user interface
     *
     * @param gui GUI to be used
     */
    private void createMainUI(GraphicalUI gui) {
        updateMainUI(gui);
        mainHolder.add(aimHolder);
        mainHolder.add(playerShipHolder);
        this.add(mainHolder);
    }

    /**
     * Method to update the UI. Creates two grids with coordinate legends. Uses
     * changeButton to determine icon for the button.
     *
     * @param gui main graphical user interface
     */
    public void updateMainUI(GraphicalUI gui) {
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
                        changer.changeButton(gui, aimButton, loc, 1);
                        changer.changeButton(gui, labelButton, loc, 0);
                        aimButton.addActionListener(aimListener);
                        aimHolder.add(aimButton);
                        playerShipHolder.add(labelButton);
                    }
                }
            }
        }
    }

    private JLabel createCoordinateLabel(int number) {
        JLabel coord = new JLabel("" + number, SwingConstants.CENTER);
        coord.setFont(new Font("Arial", Font.PLAIN, fontSize));
        coord.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        coord.setBackground(Color.BLACK);
        coord.setForeground(Color.GREEN);
        coord.setOpaque(true);
        return coord;
    }

    /**
     * Method returns a new JButton to be used in the aim grid.
     *
     * @return new JButton
     */
    private JButton createButton() {
        JButton aimButton = new JButton();
        aimButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        aimButton.setBackground(Color.DARK_GRAY);
        aimButton.setToolTipText("Use these buttons to shoot!");
        return aimButton;
    }

    private JLabel createEmptyLabel() {
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        emptyLabel.setOpaque(true);
        emptyLabel.setBackground(Color.BLACK);
        return emptyLabel;
    }

}
