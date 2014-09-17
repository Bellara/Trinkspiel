package de.lauverngers.view;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Alexander Wesker on 17.09.2014.
 */
public class GameCreationPanel extends JPanel {

    private JFrame creationFrame;

    public GameCreationPanel() {
        init();
    }

    private void init() {
        creationFrame = new JFrame("Zerschepperung");
        creationFrame.setResizable(false);
        creationFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        creationFrame.setSize(800, 550);
        creationFrame.setLocationRelativeTo(null);
        creationFrame.setVisible(true);

        creationFrame.add(this);
    }

    public static void main(String[] args) {
        new GameCreationPanel();
    }
}
