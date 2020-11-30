package Application;

import Application.Level.FirstLevel;
import Application.Level.Level;
import Application.Level.SecondLevel;

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
    private int JPanelXSpeed;
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
     * Change x and y location of JPanel - functioning as a "CAMERA"
     **/
    private void changeLocationOfJPanel() {
        //TODO
        System.out.println(currentLevel.getPlayer().getX());

        //Player values of current turn
        boolean tempDirection = currentLevel.getPlayer().isDirection(); //false = -> | true = <-
        int tempPlayerX = currentLevel.getPlayer().getX();
        int tempPlayerY = currentLevel.getPlayer().getY();
        double tempPlayerXSpeed = currentLevel.getPlayer().getxSpeed();
        int JPanelX;

        // < -- Left
        if (tempPlayerX <= currentLevel.getBackGround().getX() + 465) {
            if (!tempDirection) {    // <--
                JPanelX = Math.min(0, -tempPlayerX + 400);
            } else {                // -->
                JPanelX = Math.min(0, -tempPlayerX + 100);
            }
            //Right End Of The Map -- >
        } else if (tempPlayerX >= currentLevel.getBackGround().getWidth() - 1000) {
            if (!tempDirection) {    //<--
                JPanelX = Math.max(-tempPlayerX + 400, -2100);
            } else {
                JPanelX = Math.max(-tempPlayerX + 100, -2100);
            }


        } else {
            if (tempPlayerXSpeed != 0) {
                int maxJPanelSpeed = 10;
                if (!tempDirection) {
                    System.out.println("LEFT");
                    if (JPanelXSpeed < maxJPanelSpeed)
                        JPanelXSpeed++;
                    else JPanelXSpeed = maxJPanelSpeed;
                } else {    //--> right
                    System.out.println("RIGHT");
                    if (JPanelXSpeed > -maxJPanelSpeed)
                        JPanelXSpeed--;
                    else JPanelXSpeed = -maxJPanelSpeed;
                }
                JPanelX = (int) this.getLocation().getX() + JPanelXSpeed;
            } else {
                if (!tempDirection) {    // -->
                    System.out.println("STATIC LEFT");
                    if ((int) this.getLocation().getX() + JPanelXSpeed> -tempPlayerX+600){
                        JPanelXSpeed--;
                    }else{
                        JPanelXSpeed=0;
                    }
                    JPanelX = (int) this.getLocation().getX() + JPanelXSpeed;

                } else {
                    System.out.println("STATIC RIGHT");
                    JPanelX = -tempPlayerX + 100;
                }
            }
        }
        this.setLocation(JPanelX, -tempPlayerY + 350);
        lastRoundDirection = tempDirection;
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
        returnList.add(new SecondLevel());
        returnList.add(new FirstLevel());
        returnList.add(new SecondLevel());
        returnList.add(new FirstLevel());
        returnList.add(new SecondLevel());
        return returnList;
    }

    private void inCaseOFDirectionChange() {

    }

    private void startNextLevel() {
        levelCounter++;
        currentLevel = levels.get(levelCounter);
        this.setLocation(0, 0);
        //start music of level
//        musicObject.playMusic(filepath);
    }
}
