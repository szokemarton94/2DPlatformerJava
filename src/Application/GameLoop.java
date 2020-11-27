package Application;





import Application.Level.FirstLevel;
import Application.Level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GameLoop extends JPanel implements ActionListener {
    private final String projectFolder = new File("").getAbsolutePath();
    public static long roundCounter;
    public Timer timer;
    private final List<Level> levels;             // ArrayList containing LEVELS
    private Level currentLevel;             // Level currently  DRAWING in MainLoop
    private int levelCounter;
    private int xSpeed;
    private boolean isDirectionChanged;
    private boolean lastRoundDirection;
    //Music
    String filepath = projectFolder.concat("\\src\\Music\\Main.wav");
//    MusicObject musicObject = new MusicObject();
    //Constructor-------------------------------------------------------------------------------------------------------

    /**
     * Main Loop for the Game
     **/
    GameLoop() {
        this.levelCounter = 0;
        this.levels = levelListCreator();
        this.currentLevel = levels.get(levelCounter);
        //The Loop
        timer = new Timer(17, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                timer.start();
                if (currentLevel.isFinished()) {                  // if conditions matched -> current level turns to next level
                    startNextLevel();
                    roundCounter = 0;
                } else {
                    currentLevel.set();
                    changeLocationOfJPanel();
                    repaint();
                }
                roundCounter++;
            }
        });
    }

    /**
     * Change x and y location of JPanel - functioning as a"CAMERA"
     **/
    private void changeLocationOfJPanel() {

        if (currentLevel.getPlayer().getX() <= currentLevel.getBackGround().getX() + 465 && !currentLevel.getPlayer().isDirection()) {
            this.setLocation(0, -currentLevel.getPlayer().getY() + 350);
            //insuff
        } else if (currentLevel.getPlayer().getX() >= 2500 && currentLevel.getPlayer().isDirection()) {
            this.setLocation(-2480, -currentLevel.getPlayer().getY() + 350);
        } else {

            //ha megváltozik az előző körben az irány
            if (currentLevel.getPlayer().isDirection() != lastRoundDirection) {
                isDirectionChanged = true;
            }

            //ha irányváltás történik elindul a kamera
            if (isDirectionChanged) {

                inCaseOFDirectionChange();

                if (!currentLevel.getPlayer().isDirection() && this.getLocation().getX()
                        >= -currentLevel.getPlayer().getX() + 400) {
                    isDirectionChanged = false;
                    xSpeed = 0;
                }
                if (currentLevel.getPlayer().isDirection() && this.getLocation().getX()
                        <= -currentLevel.getPlayer().getX() + 100) {
                    isDirectionChanged = false;
                    xSpeed = 0;
                }

                this.setLocation((int) this.getLocation().getX() + xSpeed, -currentLevel.getPlayer().getY() + 350);


            } else {
                //ha irányváltás nincs a kamera fixen követi a karaktert
                int adjustX = 100;
                if (!currentLevel.getPlayer().isDirection()) {    // -->
                    adjustX = 400;
                }
                this.setLocation(-currentLevel.getPlayer().getX() + adjustX, -currentLevel.getPlayer().getY() + 350);

            }
        }
        lastRoundDirection = currentLevel.getPlayer().isDirection();
        this.setSize(getWidth() + currentLevel.getPlayer().getX() * 2, getHeight() + currentLevel.getPlayer().getY() * 2);

    }
    //Class own methods-------------------------------------------------------------------------------------------------

    /**
     * Draw the Objects of current Level
     **/
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D gtd = (Graphics2D) graphics;
        currentLevel.draw(gtd);
        graphics.setFont(new Font("arial", Font.BOLD, 14));
//        graphics.drawString("LIFE: " + currentLevel.getPlayer().getHealthPoint(), currentLevel.getPlayer().getX(), currentLevel.getPlayer().getY()-10);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    /**
     * Keyevent handeling
     **/

    public void keyPressed(KeyEvent e) {
        if (currentLevel.getPlayer().isControlAble()) {
            switch (e.getKeyChar()) {
                case ('a'):
                    currentLevel.getPlayer().setKeyLeft(true);
                    break;
                case ('w'):
                    currentLevel.getPlayer().setKeyUp(true);
                    break;
                case ('d'):
                    currentLevel.getPlayer().setKeyRight(true);
                    break;
                case ('s'):
                    currentLevel.getPlayer().setKeyDown(true);
                    break;
                case ('k'):
                    currentLevel.getPlayer().setKeyAttack(true);
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (currentLevel.getPlayer().isControlAble()) {
            switch (e.getKeyChar()) {
                case ('a'):
                    currentLevel.getPlayer().setKeyLeft(false);
                    currentLevel.getPlayer().getPlayerDisplay().stopLeft();
                    break;

                case ('w'):
                    currentLevel.getPlayer().setKeyUp(false);
                    break;

                case ('d'):
                    currentLevel.getPlayer().setKeyRight(false);
                    currentLevel.getPlayer().getPlayerDisplay().stopRight();
                    break;

                case ('s'):
                    currentLevel.getPlayer().setKeyDown(false);
                    break;

                case ('k'):
                    currentLevel.getPlayer().setKeyAttack(false);
                    if (currentLevel.getPlayer().isDirection()) {
                        currentLevel.getPlayer().getPlayerDisplay().stopRight();
                    } else {
                        currentLevel.getPlayer().getPlayerDisplay().stopLeft();
                    }
                    break;
            }
        }
    }

    /**
     * Private class for creating the Levels, and level list
     **/
    private List<Level> levelListCreator() {
        List<Level> returnList = new ArrayList<>();
        returnList.add(new FirstLevel());
//        returnList.add(new SecondLevel());
        return returnList;
    }

    private void inCaseOFDirectionChange() {
        int cameraSpeed = 10;
        if (!currentLevel.getPlayer().isDirection()) {          //--> right
            if (xSpeed < cameraSpeed)
                xSpeed++;
            else
                xSpeed = cameraSpeed;
        } else if (currentLevel.getPlayer().isDirection()) {    //--> left
            if (xSpeed > -cameraSpeed)
                xSpeed--;
            else
                xSpeed = -cameraSpeed;
        }
        //TODO
    }

    private void startNextLevel() {
        levelCounter++;
        currentLevel = levels.get(levelCounter);
        this.setLocation(0, 0);
        //start music of level
//        musicObject.playMusic(filepath);
    }
}
