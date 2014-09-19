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

    public GameStartPanel() {
        super.init();
        addComponentToPanel(initGameStartButton());
        super.showPanel();
    }

    private JButton initGameStartButton(){
        gameStartButton = new JButton("Los gehts");
        gameMouseListener = new GameStartMouseListener();
        gameStartButton.addMouseListener(gameMouseListener);
        return gameStartButton;
    }

    private Dimension getMaximumScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public class GameStartMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            Game game = new Game(new ChallengeService());
            try {
                game.init();
            }
            catch (IOException ioe) {
                //toDo: exception handling
            }
//            initSettingsPanel
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
}
