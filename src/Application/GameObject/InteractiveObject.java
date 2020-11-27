package Application.GameObject;

import java.util.List;
import java.awt.*;

/**
 * PhysicalObject
 * -------------------------------
 * features:
 * - all of GameObject
 * -
 * - hitbox - interActableObj
 */
public abstract class InteractiveObject extends GameObject {
    boolean isActive;
    Rectangle hitBox;

    /**
     * Constructor
     */
    public InteractiveObject() {
        this.hitBox = new Rectangle(super.x, super.y, super.width, super.height);
        this.isActive = false;
    }

    public abstract void interaction(Player player, List<InteractiveObject> obstacleList);
}
