package Application;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        GameLoop panel = new GameLoop();
        panel.setLocation(0, 0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.BLACK);
        panel.setVisible(true);
        panel.timer.start();
        this.add(panel);
        addKeyListener(new Input(panel));

    }


}
