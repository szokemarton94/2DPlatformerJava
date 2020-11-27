package Application.GameObject;
import Application.Display.PlayerDisplay;
import Application.MainGameLoop;

import java.awt.*;
import java.util.List;

public class Player extends Creature {
    private final PlayerDisplay playerDisplay;
    private boolean direction; //true => Right  false => Left
    private boolean onTheGround;
    private boolean isControlAble;
    private boolean isAttackedByRight;
    private boolean isAttackedByLeft;
    private long attackedRound;
    private boolean keyUp;
    private boolean keyRight;
    private boolean keyLeft;
    private boolean keyDown;
    private boolean keyAttack;


    //Constructor-------------------------------------------------------------------------------------------------------
    public Player(int x, int y) {
        this.isControlAble = true;
        this.healthPoint = 13;
        this.playerDisplay = new PlayerDisplay();
        this.onTheGround = true;
        super.x = x;
        super.y = y;
        super.width = 54;   //36   --> hit
        super.height = 87;   //58
        super.hitBox = new Rectangle(x, y, width, height);
        super.isActive = true;

    }

    public boolean isControlAble() {
        return isControlAble;
    }

    public PlayerDisplay getPlayerDisplay() {
        return playerDisplay;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public int getHealthPoint() {
        return super.healthPoint;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setKeyUp(boolean keyUp) {
        this.keyUp = keyUp;
    }

    public void setKeyRight(boolean keyRight) {
        this.keyRight = keyRight;
    }

    public void setKeyLeft(boolean keyLeft) {
        this.keyLeft = keyLeft;
    }

    public void setKeyDown(boolean keyDown) {
        this.keyDown = keyDown;
    }

    public void setKeyAttack(boolean keyAttack) {
        this.keyAttack = keyAttack;
    }



    @Override
    public void draw(Graphics2D gtd) {
        if (isActive) {
            gtd.drawImage(playerDisplay.getTempDisplayImage(), x - 30, y - 10, playerDisplay.getDisplayWidth(), playerDisplay.getDisplayHeight(), null);
            if (isAttackedByRight) {
                playerDisplay.attackedAnimationL(attackedRound);
            }
            if (isAttackedByLeft) {
                playerDisplay.attackedAnimationR(attackedRound);
            }
        }
    }

    public void set(List<InteractiveObject> obstacleList, List<Creature> creatureList) {
//        System.out.println(x);
//        System.out.println(y);
        if (isControlAble) {
            keyInputHandler();
            attack(creatureList);
        }
        applyPhysics(obstacleList);
        statusCheck();
    }

    private void keyInputHandler() {
        standingAttackCheck();
        movement();


    }

    private void movement() {
        /**if A and D |pressed|
         or |released| at the same time
         slow down the character**/
        if (keyLeft && keyRight || !keyLeft && !keyRight) {
            xSpeed *= 0.8;
        } else if (keyLeft) {
            eventsCorrespondingKeyLeft();
        } else {
            eventsCorrespondingKeyRight();
        }
    }

    private void eventsCorrespondingKeyRight() {
        /**only D key is |pressed| --> goes RIGHT**/
        direction = true; //=> true = Left
        xSpeed++;
        if (keyAttack) {
            playerDisplay.startFightRight();
        }
        if (onTheGround) {
            playerDisplay.startMoveRight();
        } else {
            playerDisplay.startJumpRight();
        }
        if (keyUp) {
            onTheGround = false;
            playerDisplay.startJumpRight();

        }
    }

    private void eventsCorrespondingKeyLeft() {
        direction = false; //=> false = Left
        xSpeed--;
        if (keyAttack) {
            playerDisplay.startFightLeft();
        } else if (onTheGround) {
            playerDisplay.startMoveLeft();
        } else {
            playerDisplay.startJumpLeft();
        }

        if (keyUp) {
            onTheGround = false;
            playerDisplay.startJumpLeft();
        }
    }


    public void getAttacked(Creature creature) {
        attackedRound = MainGameLoop.roundCounter;
        if (creature.x > this.x) {
            isAttackedByLeft = true;
            xSpeed = 0;
            x -= 100;
        } else if (creature.x < this.x) {
            isAttackedByRight = true;
            xSpeed = 0;
            x += 100;
        }
        healthPoint--;
    }

    @Override
    public void statusCheck() {
        //System.out.println(healthPoint);
        if (attackedRound != 0)
            if (MainGameLoop.roundCounter < (attackedRound + 50) && MainGameLoop.roundCounter >= attackedRound) {
                isControlAble = false;
                xSpeed = 0;
                keyRight = false;
                keyLeft = false;
                keyUp = false;
                keyAttack = false;
            }
        if (MainGameLoop.roundCounter == attackedRound + 50) {
            isControlAble = true;
            if (direction) {
                playerDisplay.stopRight();
            } else {
                playerDisplay.stopLeft();
            }
            isAttackedByRight = false;
            isAttackedByLeft = false;
        }
        if (this.getHealthPoint() == 0)
            isActive = false;

    }

    private void attack(List<Creature> creatureList) {
        if (keyAttack && direction) {
            hitBox.x += 50;
            for (Creature creature : creatureList) {
                if (this.hitBox.intersects(creature.hitBox)) {
                    creature.getAttacked(this);
                }
            }
            hitBox.x -= 50;
        } else if (keyAttack) {
            hitBox.x -= 50;
            for (Creature creature : creatureList) {
                if (this.hitBox.intersects(creature.hitBox)) {
                    creature.getAttacked(this);
                }
            }
            hitBox.x += 50;
        }
    }


    private void standingAttackCheck() {
        if (keyAttack) {
            if (direction || keyRight) {
                playerDisplay.startFightRight();
            } else {
                playerDisplay.startFightLeft();
            }
        }
    }


    /**
     * Apply Physics On Player
     *
     * @param obstacleList
     */
    private void applyPhysics(List<InteractiveObject> obstacleList) {
        setMaxXSpeed();
        frictionHandling();
        jumpPhysics(obstacleList);
        ySpeed += 0.3; //Gravity
        horizontalCollision(obstacleList);
        verticalCollision(obstacleList);

        x += xSpeed;
        y += ySpeed;
        hitBox.x = x;
        hitBox.y = y;
    }
/**------------------------------------------------------------------------------------------------------------------**/
/**Physics private methods**/
/**------------------------------------------------------------------------------------------------------------------**/
    /**
     * Check horizontal collision
     *
     * @param obstacleList
     */
    private void horizontalCollision(List<InteractiveObject> obstacleList) {
        this.hitBox.x += xSpeed;
        for (InteractiveObject obstacle : obstacleList) {
            if (this.hitBox.intersects(obstacle.hitBox)) {
                this.hitBox.x -= xSpeed;
                while (!obstacle.hitBox.intersects(hitBox)) {
                    this.hitBox.x += Math.signum(xSpeed);
                    x += xSpeed;
                }
                /**To climb staircase**/
                if (obstacle.hitBox.getHeight() > 30) {
                    hitBox.x -= Math.signum(xSpeed);
                    xSpeed = 0;
                } else if (obstacle.hitBox.getHeight() <= 30 && keyLeft || keyRight) {
                    xSpeed = 10;
                }
                x = hitBox.x;
            }
        }
    }

    /**
     * Check verticalal collision
     */
    private void verticalCollision(List<InteractiveObject> obstacleList) {

        this.hitBox.y += ySpeed;
        for (InteractiveObject obstacle : obstacleList) {
            if (this.hitBox.intersects(obstacle.hitBox)) {
                this.hitBox.y -= ySpeed;
                while (!obstacle.hitBox.intersects(hitBox)) {
                    this.hitBox.y += Math.signum(ySpeed);
                }
                this.hitBox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                onTheGround = true;
                y = hitBox.y;
            }
        }
    }

    /**
     * Add  max xSpeed
     **/
    private void setMaxXSpeed() {
        int maxHrzSpeed = 10;
        if (xSpeed > maxHrzSpeed) {
            xSpeed = maxHrzSpeed;
        } else if (xSpeed < -maxHrzSpeed) {
            xSpeed = -maxHrzSpeed;
        }
    }

    /**
     * Provide Player hitbox friction (=súrlódás)
     **/
    private void frictionHandling() {
        if (xSpeed > 0 && xSpeed < 0.2) {
            xSpeed = 0;
        } else if (xSpeed < 0 && xSpeed > -0.2) {
            xSpeed = 0;
        }
    }

    /**
     * Jump mechanism
     */
    private void jumpPhysics(List<InteractiveObject> obstacleList) {
        if (keyUp) {
            hitBox.y++;
            for (InteractiveObject obstacle : obstacleList) {
                if (obstacle.hitBox.intersects(this.hitBox)) {
                    ySpeed = -7.5;
                }
            }
            hitBox.y--;

        }
    }

    @Override
    public void interaction(Player player, List<InteractiveObject> obstacleList) {
    }
}
