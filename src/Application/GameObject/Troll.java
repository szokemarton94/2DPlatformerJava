package Application.GameObject;

import Application.Display.TrollDisplay;
import Application.GameLoop;



import java.awt.*;
import java.util.List;


public class Troll extends Creature {
    private boolean isChasePlayer;
    private final TrollDisplay td;
    private boolean movingLeft;
    private boolean movingRight;
    private int pointA;
    private int pointB;
    private boolean attackStance;
    private long attackTime;
    private boolean direction;


    public Troll(int x, int y, int spX1, int spX2) {
        this.td = new TrollDisplay();
        this.isChasePlayer = false;
        super.x = x;
        super.y = y;
        super.height = 60;
        super.width = 40;
        super.isActive = true;
        healthPoint = 10;
        pointA = spX1;
        pointB = spX2;
        super.hitBox = new Rectangle(x, y, width, height);

    }

    public void draw(Graphics2D graphics2D) {

        if (isActive) {
            graphics2D.drawImage(td.getTempDisplayImage(), x - 10, y - 44, 82, 104, null);
            if (!attackStance) {
                if (movingLeft && !movingRight) {
                    td.trollMoveLeft();
                }
                if (movingRight && !movingLeft) {
                    td.trollMoveRight();
                }
            } else if (!direction) {
                td.attackAnimationRight(attackTime);

            } else {
                td.attackAnimationLeft(attackTime);

            }
        }
    }

    public void interaction(Player player, List<InteractiveObject> obstacleList) {
        if (isActive) {
            playerStatusCheck(player);
            if (isChasePlayer) {
                chasePlayer(player);
            } else {
                patrol();
            }
            ySpeed += 0.3;
            horizontalCollision(obstacleList);
            verticalCollision(obstacleList);
            x = x + (int) xSpeed;
            y = y + (int) ySpeed;
            hitBox.x = x;
            hitBox.y = y;
            statusCheck();
        }
    }

    private void playerStatusCheck(Player player) {
        isChasePlayer = (player.getX() >= this.x - 300) && (player.getX() <= this.x + 300);
    }

    private void chasePlayer(Player player) {

        if (player.getX() > this.x + 80 && !attackStance) {
            xSpeed = 2;
            movingLeft = false;
            movingRight = true;
            direction = true;
        } else if (player.getX() < this.x - 40 && !attackStance) {
            xSpeed = -2;
            movingLeft = true;
            movingRight = false;
            direction = false;
        } else if(!direction && this.x < player.getX() + 20 || this.x > player.getX() - 40)
            attack(player);
        }



    private void attack(Player player) {
        ySpeed = -2.5;
        if (this.hitBox.intersects(player.hitBox)) {
            attackTime = GameLoop.roundCounter;
            attackStance = true;
            xSpeed = 0;
            player.getAttacked(this);
        }
    }

    private void patrol() {
        if (this.x > pointA && this.x < pointB && !direction) {
            xSpeed = -1;
            movingLeft = true;
            movingRight = false;
        }
        if (this.x <= pointA) {
            direction = true;
            movingLeft = false;
            movingRight = true;
            xSpeed = 1;
        }
        if (this.x > pointA && this.x < pointB && direction) {
            xSpeed = 1;
            movingLeft = false;
            movingRight = true;
        }
        if (this.x >= pointB) {
            direction = false;
            movingLeft = true;
            movingRight = false;
            xSpeed = -1;
        }
    }

    @Override
    public void statusCheck() {
        if (GameLoop.roundCounter == attackTime + 40) {
            attackStance = false;
        }
        if (attackStance) {
            xSpeed = 0;
        }
        if (healthPoint <= 0) {
            isActive = false;
        }
    }

    @Override
    public void getAttacked(Creature creature) {
        xSpeed = 0;
        if (creature.getX() > this.x) {
            this.x -= 50;
        } else {
            this.x += 50;
        }
        healthPoint--;
    }

    /**
     * Check horizontal collision
     **/
    private void horizontalCollision(List<InteractiveObject> obstacleList) {
        this.hitBox.x += xSpeed;
        for (InteractiveObject obstacle : obstacleList) {
            if (this.hitBox.intersects(obstacle.hitBox)) {
                this.hitBox.x -= xSpeed;
                while (!obstacle.hitBox.intersects(hitBox)) {
                    this.hitBox.x += Math.signum(xSpeed);
                    x += xSpeed;
                }
                hitBox.x -= Math.signum(xSpeed);
                xSpeed = 0;
                x = hitBox.x;
                jumpPhysics(obstacleList);
            }
        }
    }

    /**
     * Check vertical collision
     **/
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
                y = hitBox.y;
            }
        }
    }

    /**
     * Jump
     *
     * @paramobstacleList*/
    private void jumpPhysics(List<InteractiveObject> obstacleList) {
         hitBox.y++;
        for (InteractiveObject obstacle : obstacleList) {
            if (obstacle.hitBox.intersects(this.hitBox)) {
                ySpeed = -7.5;
            }
        }
        hitBox.y--;
    }


}


