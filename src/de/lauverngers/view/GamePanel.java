package de.lauverngers.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Alexander Wesker on 17.09.2014.
 */
public class GamePanel extends JPanel {

    private JFrame creationFrame;

    public final Font CUSTOM_FONT = new Font("Comic Sans MS", Font.BOLD, 18);

    protected void init() {
        creationFrame = new JFrame("Zerschepperung");
        creationFrame.setResizable(false);
        creationFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        creationFrame.setSize(getMaximumScreenSize());
        creationFrame.setBackground(Color.BLACK);
        creationFrame.setLocationRelativeTo(null);
        creationFrame.add(initLabel());
    }

    private JLabel initLabel(){
        JLabel label = new JLabel("Zerschepperung");
        label.setBackground(Color.BLACK);
        label.setFont(CUSTOM_FONT);
        return label;
    }

    protected void addComponentToPanel(Component component){
        creationFrame.add(component);
    }

    public void showPanel(){
        creationFrame.setVisible(true);
    }


    private Dimension getMaximumScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
