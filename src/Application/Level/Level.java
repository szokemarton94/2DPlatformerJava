package Application.Level;

import Application.GameObject.*;

import java.awt.*;
import java.util.List;

public abstract class Level {
    protected InitializedInteractiveObject initializedInteractiveObject;
    protected Player player;
    protected List<InteractiveObject> obstacleList;
    protected BackGround backGround;
    protected BackGround foreGround;
    protected List<Creature> creatureList;
    protected BackGround effect;
    public Level() {

    }

    public BackGround getBackGround() {
        return backGround;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract boolean isFinished();

    public abstract void set();

    public abstract void draw(Graphics2D gtd);



}
