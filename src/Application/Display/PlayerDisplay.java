package Application.Display;



import Application.MainGameLoop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDisplay {


    public BufferedImage tempDisplayImage;
    private int spriteDisplayFrequency;                                         // start from 0 moving up
    private int speedGear = 3;                                                  // checking spriteDisplayFrequency, make sprite display different
    private int currentLeft;                                                    // integer - count the current index in "leftMovement" imageList
    private int currentRight;                                                   // integer - count the current index in "rightMovement" imageList
    private int attackRight;
    private int attackLeft;
    BufferedImage leftStop;                                             // image shown when character stops heading left TODO -find better solution
    BufferedImage rightStop;                                            // image shown when character stops heading right TODO -find better solution
    List<BufferedImage> leftMovement;                                  // listOfImages - moving left
    List<BufferedImage> rightMovement;                                  // listOfImages - moving right
    List<BufferedImage> rightJumpMovement;                             // listOfImages - jumping left (currently 1)
    List<BufferedImage> leftJumpMovement;                              // listOfImages - jumping left (currently 1)
    List<BufferedImage> leftAttackMovement;
    List<BufferedImage> rightAttackMovement;
    private final int displayWidth = 120;  //81
    private final int displayHeight = 98; //65

    private final String projectFolder = new File("").getAbsolutePath();

    public PlayerDisplay() {
        this.tempDisplayImage = rightStop();
        this.leftStop = leftStop();
        this.rightStop = rightStop();
        this.leftMovement = leftMovementMaker();
        this.rightMovement = rightMovementMaker();
        this.rightJumpMovement = rightJumpMovementMaker();
        this.leftJumpMovement = leftJumpMovementMaker();
        this.leftAttackMovement = leftAttackMovementMaker();
        this.rightAttackMovement = rightAttackMovementMaker();
    }

    public BufferedImage getTempDisplayImage() {
        return tempDisplayImage;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    private List<BufferedImage> getLeftMovement() {
        return leftMovement;
    }

    private List<BufferedImage> getRightMovement() {
        return rightMovement;
    }

    private List<BufferedImage> getRightJumpMovement() {
        return rightJumpMovement;
    }

    private List<BufferedImage> getLeftJumpMovement() {
        return leftJumpMovement;
    }

    private List<BufferedImage> getLeftAttackMovement() {
        return leftAttackMovement;
    }

    private List<BufferedImage> getRightAttackMovement() {
        return rightAttackMovement;
    }

    private List<BufferedImage> leftAttackMovementMaker() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt1L.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt2L.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt3L.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt4L.png"))));

        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return returnList;
    }

    private List<BufferedImage> leftMovementMaker() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL1.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL2.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL3.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL4.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL5.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL6.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL7.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PL8.png"))));

        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return returnList;

    }

    private List<BufferedImage> rightAttackMovementMaker() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt1R.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt2R.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt3R.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PAtt4R.png"))));


        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return returnList;

    }

    private List<BufferedImage> rightJumpMovementMaker() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/JR.png"))));

        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return returnList;
    }

    private List<BufferedImage> leftJumpMovementMaker() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/JL.png"))));

        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return returnList;
    }

    private List<BufferedImage> rightMovementMaker() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR1.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR2.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR3.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR4.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR5.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR6.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR7.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PR8.png"))));
        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return returnList;
    }

    private List<BufferedImage> leftInjuredMovement() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PHL1.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PHL2.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PHL3.png"))));
        } catch (IOException e) {
            System.out.println("leftInjuredAnim PNGs not found");
        }
        return returnList;
    }

    public List<BufferedImage> rightInjuredMovement() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PHR1.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PHR2.png"))));
            returnList.add(ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/PHR3.png"))));
        } catch (IOException e) {
            System.out.println(" rightInjuredAnim PNGs not found");
        }
        return returnList;
    }

    private BufferedImage leftStop() {
        try {
            return ImageIO.read(new File(projectFolder.concat("/src/Image_Resources//SL.png")));
        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return null;
    }

    private BufferedImage rightStop() {
        try {
            return ImageIO.read(new File(projectFolder.concat("/src/Image_Resources/SR.png")));
        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return null;
    }

    public void startMoveLeft() {
        if (spriteDisplayFrequency % speedGear == 0) {
            tempDisplayImage = getLeftMovement().get(currentLeft);
            if (currentLeft == 7) {
                currentLeft = 0;
            } else {
                currentLeft++;
            }
        }
        spriteDisplayFrequency++;
    }

    public void startMoveRight() {
        if (spriteDisplayFrequency % speedGear == 0) {
            tempDisplayImage = getRightMovement().get(currentRight);
            if (currentRight == 7) {
                currentRight = 0;
            } else {
                currentRight++;
            }
        }
        spriteDisplayFrequency++;
    }

    public void startFightLeft() {
        if (spriteDisplayFrequency % 5 == 0) {
            tempDisplayImage = getLeftAttackMovement().get(attackLeft);
            if (attackLeft == 3) {
                attackLeft = 0;
            } else {
                attackLeft++;
            }
        }
        spriteDisplayFrequency++;
    }

    public void startFightRight() {
        if (spriteDisplayFrequency % 5 == 0) {
            tempDisplayImage = getRightAttackMovement().get(attackRight);
            if (attackRight == 3) {
                attackRight = 0;
            } else {
                attackRight++;
            }
        }
        spriteDisplayFrequency++;
    }

    public void startJumpRight() {
        tempDisplayImage = getRightJumpMovement().get(0);
    }

    public void startJumpLeft() {
        tempDisplayImage = getLeftJumpMovement().get(0);
    }

    public void stopRight() {
        tempDisplayImage = rightStop;
    }

    public void stopLeft() {
        tempDisplayImage = leftStop;
    }



    public void attackedAnimationL(long attackedRound) {
        if (attackedRound != 0)
            if (MainGameLoop.roundCounter == attackedRound) {
                tempDisplayImage = leftInjuredMovement().get(0);
            } else if (MainGameLoop.roundCounter == attackedRound + 8) {
                tempDisplayImage = leftInjuredMovement().get(1);
            } else if (MainGameLoop.roundCounter == attackedRound + 16) {
                tempDisplayImage = leftInjuredMovement().get(2);
            } else if (MainGameLoop.roundCounter == attackedRound + 24) {
                stopRight();
            }
    }

    public void attackedAnimationR(long attackedRound) {
        if (attackedRound != 0)
            if (MainGameLoop.roundCounter == attackedRound) {
                tempDisplayImage = rightInjuredMovement().get(0);
            } else if (MainGameLoop.roundCounter == attackedRound + 8) {
                tempDisplayImage = rightInjuredMovement().get(1);
            } else if (MainGameLoop.roundCounter == attackedRound + 16) {
                tempDisplayImage = rightInjuredMovement().get(2);
            } else if (MainGameLoop.roundCounter == attackedRound + 24) {
                stopRight();
            }
    }
}
