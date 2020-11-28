package Application.Level;

import Application.GameObject.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FirstLevel extends Level {

    public FirstLevel() {
        super.player = new Player(1500, 500);
        super.backGround = new BackGround(1554, 3108, new File("").getAbsolutePath().concat("/src/Image_Resources/Lvl1Back.png"));
        super.foreGround = new BackGround(1554, 3108, new File("").getAbsolutePath().concat("/src/Image_Resources/Lvl1Front.png"));
        super.effect = new BackGround(748, 3075, new File("").getAbsolutePath().concat("/src/Image_Resources/effect1.png"));
        super.obstacleList = levelMaker();
        super.initializedInteractiveObject = new InitializedInteractiveObject(2100, 150);
        super.creatureList = creatureListCreator();
    }

    @Override
    public boolean isFinished() {
        return super.player.getX() > 3050;
    }

    @Override
    public void set() {
        for (Creature creature : creatureList) {
            creature.interaction(player, obstacleList);
        }
        player.set(obstacleList, creatureList);
        initializedInteractiveObject.interaction(player, obstacleList);
    }

    @Override
    public void draw(Graphics2D gtd) {
        for (InteractiveObject obstacle : obstacleList) {
            obstacle.draw(gtd);
        }
        backGround.draw(gtd);
        for (Creature creature : creatureList) {
            creature.draw(gtd);
        }
        initializedInteractiveObject.draw(gtd);
        player.draw(gtd);
        foreGround.draw(gtd);
        effect.draw(gtd);
    }

    private List<InteractiveObject> levelMaker() {
        List<InteractiveObject> returnList = new ArrayList<>();
        //FirstRoom
        returnList.add(new Obstacle(50, 500, 50, 620));
        returnList.add(new Obstacle(0, 1015, 450, 50));
        returnList.add(new Obstacle(460, 982, 170, 50));
        // plafon
        returnList.add(new Obstacle(460, 730, 170, 50));
        returnList.add(new Obstacle(640, 1015, 260, 50));
        returnList.add(new Obstacle(910, 954, 260, 50));
        // plafon2
        returnList.add(new Obstacle(910, 697, 202, 50));
        // fuggoleges
        returnList.add(new Obstacle(1068, 387, 50, 310));
        returnList.add(new Obstacle(900, 954, 260, 50));
        //Stair
        returnList.add(new Obstacle(1165, 944, 15, 15));
        returnList.add(new Obstacle(1178, 927, 15, 15));
        returnList.add(new Obstacle(1193, 909, 17, 17));
        returnList.add(new Obstacle(1210, 892, 17, 17));
        returnList.add(new Obstacle(1227, 875, 17, 17));
        returnList.add(new Obstacle(1242, 860, 120, 30));
        returnList.add(new Obstacle(1365, 838, 15, 15));
        returnList.add(new Obstacle(1380, 822, 15, 15));
        returnList.add(new Obstacle(1395, 807, 15, 15));
        returnList.add(new Obstacle(1412, 789, 15, 15));
        returnList.add(new Obstacle(1432, 767, 20, 20));
        returnList.add(new Obstacle(1450, 750, 15, 15));
        returnList.add(new Obstacle(1465, 735, 15, 15));
        returnList.add(new Obstacle(1480, 717, 15, 15));
        returnList.add(new Obstacle(1500, 700, 15, 15));
        //UpperLevel
        returnList.add(new Obstacle(1520, 700, 460, 50));
        returnList.add(new Obstacle(1990, 737, 55, 30));
        returnList.add(new Obstacle(2355, 700, 750, 50));
        returnList.add(new Obstacle(2290, 737, 60, 30));
        // Exit1
        returnList.add(new Obstacle(3110, 387, 5, 293));
        // LowerLevel
        returnList.add(new Obstacle(1865, 727, 5, 250));
        returnList.add(new Obstacle(1865, 991, 950, 50));
        returnList.add(new Obstacle(3055, 987, 65, 50));
        //Exit2
        returnList.add(new Obstacle(3111, 837, 5, 150));
        // verem
        returnList.add(new Obstacle(2810, 1041, 5, 100));
        returnList.add(new Obstacle(3055, 1041, 5, 100));
        return returnList;
    }


    private List<Creature> creatureListCreator() {
        List<Creature> creatureList = new ArrayList<>();
//        creatureList.add(new Troll(1900, 190, 1504, 1920));
//        creatureList.add(new Troll(2600, 190, 2330, 3020));
//        creatureList.add(new Troll(1900, 500, 1880, 2775));
        return creatureList;
    }
}
