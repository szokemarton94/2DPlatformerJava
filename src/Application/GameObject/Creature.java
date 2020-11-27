package Application.GameObject;

import java.util.List;

public abstract class Creature extends InteractiveObject {
    int healthPoint;
    double xSpeed;
    double ySpeed;
    //TODO - physics applied on - receives current coordinates and coordinate of surroundings

    public abstract void statusCheck();
    public abstract void getAttacked(Creature creature);
    @Override
    public abstract void interaction(Player player, List<InteractiveObject> obstacleList);

}
