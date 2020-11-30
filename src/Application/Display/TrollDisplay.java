package Application.Display;



import Application.GameLoop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TrollDisplay {
    public BufferedImage tempDisplayImage;
    private  ArrayList<BufferedImage> movementRight;
    private ArrayList<BufferedImage> movementLeft;
    private ArrayList<BufferedImage> leftAttack;
    private ArrayList<BufferedImage> rightAttack;
    private int display = 0;
    private int spriteDisplayFrequency;
    private int speedGear = 6;
    private final String projectFolder = new File("").getAbsolutePath();

    public TrollDisplay() {
        this.movementRight = rightMovementMaker();
        this.movementLeft = leftMovementMaker();
        this.leftAttack = leftAttackMaker();
        this.rightAttack = rightAttackMaker();
    }
    public BufferedImage getTempDisplayImage(){
        return tempDisplayImage;
    }
    public ArrayList<BufferedImage> leftMovementMaker() {
        ArrayList<BufferedImage> leftMovementList = new ArrayList<>();

        try {
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL1.png"))));
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL2.png"))));
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL3.png"))));
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL4.png"))));
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL5.png"))));
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL4.png"))));
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL3.png"))));
            leftMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWL2.png"))));



        } catch (IOException e) {
            e.printStackTrace();
        }
        return leftMovementList;
    }

    public ArrayList<BufferedImage> rightMovementMaker() {
        ArrayList<BufferedImage> rightMovementList = new ArrayList<>();
        try {
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR1.png"))));
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR2.png"))));
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR3.png"))));
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR4.png"))));
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR5.png"))));
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR4.png"))));
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR3.png"))));
            rightMovementList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TWR2.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rightMovementList;
    }

    public ArrayList<BufferedImage> leftAttackMaker(){
        ArrayList<BufferedImage> leftAttackList = new ArrayList<>();
        try{
            leftAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAL1.png"))));
            leftAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAL2.png"))));
            leftAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAL3.png"))));
            leftAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAL4.png"))));
            leftAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAL5.png"))));

        }    catch (IOException e) {
        e.printStackTrace();
    }
        return leftAttackList;
    }

    public ArrayList<BufferedImage> rightAttackMaker(){
        ArrayList<BufferedImage> rightAttackList = new ArrayList<>();
        try{
            rightAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAR1.png"))));
            rightAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAR2.png"))));
            rightAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAR3.png"))));
            rightAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAR4.png"))));
            rightAttackList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/TAR5.png"))));

        }    catch (IOException e) {
            e.printStackTrace();
        }
        return rightAttackList;
    }

    public void trollMoveRight() {
        if (spriteDisplayFrequency % speedGear == 0) {
            tempDisplayImage = movementRight.get(display);
            display++;
            if (display == 7) {
                display = 0;
            }
        }
        spriteDisplayFrequency++;
    }

    public void trollMoveLeft() {

        if (spriteDisplayFrequency % speedGear == 0) {
            tempDisplayImage = movementLeft.get(display);
            display++;
            if (display == 7) {
                display = 0;
            }
        }
        spriteDisplayFrequency++;
    }

    public void attackAnimationLeft(long attackTime) {
        if (attackTime != 0) {
            if (GameLoop.roundCounter == attackTime) {
                tempDisplayImage = rightAttack.get(0);
            } else if (GameLoop.roundCounter == attackTime + 10) {
                tempDisplayImage = rightAttack.get(1);
            } else if (GameLoop.roundCounter == attackTime + 20) {
                tempDisplayImage = rightAttack.get(2);
            } else if (GameLoop.roundCounter == attackTime + 30) {
                tempDisplayImage = rightAttack.get(3);
            } else if (GameLoop.roundCounter == attackTime + 40) {
                tempDisplayImage = rightAttack.get(4);
            }
        }
    }

    public void attackAnimationRight(long attackTime) {
        if (attackTime != 0) {
            if (GameLoop.roundCounter == attackTime) {
                tempDisplayImage = leftAttack.get(0);
            } else if (GameLoop.roundCounter == attackTime + 10) {
                tempDisplayImage = leftAttack.get(1);
            } else if (GameLoop.roundCounter == attackTime + 20) {
                tempDisplayImage = leftAttack.get(2);
            } else if (GameLoop.roundCounter == attackTime + 30) {
                tempDisplayImage = leftAttack.get(3);
            } else if (GameLoop.roundCounter == attackTime + 40) {
                tempDisplayImage = leftAttack.get(4);
            }
        }
    }
}
