package de.lauverngers.view;

import de.lauverngers.challenge.ChallengeService;
import de.lauverngers.objects.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GameStartPanel extends GamePanel {

    private JButton gameStartButton;
    private MouseListener gameMouseListener;
    private Game game;

    public GameStartPanel() {
        super.init();
        addComponentToPanel(initGameStartButton());
        this.repaint();
        super.showPanel();
    }

    private JButton initGameStartButton() {
        gameStartButton = new JButton("ZERSCHEPPERN !!");
        gameStartButton.setSize(new Dimension(100, 30));
        gameStartButton.setFont(CUSTOM_FONT);
        gameStartButton.setForeground(Color.GREEN);
        gameStartButton.setBackground(Color.GRAY);
        gameMouseListener = new GameStartMouseListener();
        gameStartButton.addMouseListener(gameMouseListener);
        return gameStartButton;
    }

    private Dimension getMaximumScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public class GameStartMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                game = new Game();
            } catch (IOException ioe) {
                //toDo: exception handling
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public Game getGame() {
        return game;
    }
}
