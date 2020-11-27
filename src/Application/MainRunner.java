package Application;

import javax.swing.*;
import java.awt.*;

public class MainRunner {
    public static void main(String[] args) {

        GameFrame gameFrame = new GameFrame();
        gameFrame.setSize(1024, 768);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame.setLocation((int) (screenSize.getWidth() / 2 - gameFrame.getSize().getWidth() / 2),
                (int) (screenSize.getHeight() / 2 - gameFrame.getSize().getHeight() / 2));
        gameFrame.setBackground(Color.BLACK);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setTitle("Platformer");
        gameFrame.setVisible(true);
    }
}
