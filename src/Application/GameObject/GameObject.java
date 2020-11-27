package Application.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ----------------------------------------------------------------------------------------------------------------------------
 * GameObject
 * ----------------------------------------------------------------------------------------------------------------------------
 * features:
 * <p>
 * - coordinates in 2D space
 * - scope in x and y axis
 * - visibility
 */
public abstract class GameObject {
    //TODO accessibility
    int x;
    int y;
    int width;
    int height;
    // The current image drawn by the: draw() method, in each "frame"
    BufferedImage tempDisplayImage;

    /**
     * Constructors
     */
    public GameObject() {
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //required: access from outside of the package
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public abstract void draw(Graphics2D gtd);
}
