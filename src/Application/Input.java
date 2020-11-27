package Application;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {

    MainGameLoop panel;

    public Input(MainGameLoop panel) {
        this.panel = panel;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        panel.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        panel.keyReleased(e);
    }

}
