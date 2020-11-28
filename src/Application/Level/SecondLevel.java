package Application.Level;



import Application.GameObject.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SecondLevel extends Level {
    public SecondLevel() {
        super.player = new Player(10, 1038);
        super.backGround = new BackGround(2655, 2720, new File("").getAbsolutePath().concat("/src/Image_Resources/Lvl2Back.png"));
        super.foreGround = new BackGround(2655, 2720, new File("").getAbsolutePath().concat("/src/Image_Resources/Lvl2Front.png"));
        super.obstacleList = levelMaker();
        super.creatureList = creatureListCreator();
    }

    @Override
    public boolean isFinished() {
        return player.getX() > 3108;
    }

    @Override
    public void set() {
        for (Creature creature : creatureList) {
            creature.interaction(player, obstacleList);
        }
        player.set(obstacleList, creatureList);

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
        player.draw(gtd);
        foreGround.draw(gtd);
    }

    private List<InteractiveObject> levelMaker() {
        List<InteractiveObject> returnList = new ArrayList<>();
        // plafon
        returnList.add(new Obstacle(0, 966, 110, 5));
        // lezaras
        returnList.add(new Obstacle(0, 971, 5, 161));
        // lepcso le
        returnList.add(new Obstacle(0, 1132, 100, 40));
        returnList.add(new Obstacle(110, 1172, 101, 40));
        // padlo
        returnList.add(new Obstacle(231, 1213, 40, 600));
        returnList.add(new Obstacle(369, 1213, 40, 600));
        returnList.add(new Obstacle(505, 1213, 115, 600));
        returnList.add(new Obstacle(715, 1213, 130, 600));
        returnList.add(new Obstacle(940, 1213, 70, 600));
        // lepcso fel
        returnList.add(new Obstacle(1015, 1172, 70, 40));
        returnList.add(new Obstacle(1090, 1132, 205, 40));
        // plafon2
        returnList.add(new Obstacle(1090, 966, 205, 5));
        // lepcso le
        returnList.add(new Obstacle(1305, 1155, 20, 40));
        // padlo hosszu
        returnList.add(new Obstacle(1340, 1172, 300, 40));
        // lepcso fel
        returnList.add(new Obstacle(1650, 1155, 20, 40));
        returnList.add(new Obstacle(1680, 1132, 205, 40));
        // plafon3
        returnList.add(new Obstacle(1680, 966, 205, 5));
        // lepcso le
        returnList.add(new Obstacle(1905, 1172, 130, 40));
        returnList.add(new Obstacle(2040, 1214, 240, 40));
        returnList.add(new Obstacle(2505, 1214, 200, 40));
        // hatso fal
        returnList.add(new Obstacle(2715, 905, 5, 300));
        // lefele1
        returnList.add(new Obstacle(2300, 1445, 60, 40));
        // mellette fal
        returnList.add(new Obstacle(2275, 1255, 5, 550));
        // lefele2
        returnList.add(new Obstacle(2385, 1650, 115, 40));
        // mellette fal
        returnList.add(new Obstacle(2495, 1254, 5, 500));
        //             also szint
        // padlo
        returnList.add(new Obstacle(2100, 2172, 605, 40));
        // mellette fal
        returnList.add(new Obstacle(2715, 1800, 5, 400));
        // lepcso fel
        returnList.add(new Obstacle(1985, 2132, 100, 40));
        returnList.add(new Obstacle(1685, 2092, 285, 40));
        // plafon
        returnList.add(new Obstacle(1680, 1890, 295, 40));
        // kicsi lepcso le
        returnList.add(new Obstacle(1665, 2110, 20, 20));
        // kicsi lepcso fel
        returnList.add(new Obstacle(1310, 2115, 20, 20));
        returnList.add(new Obstacle(1020, 2090, 285, 40));
        // plafon
        returnList.add(new Obstacle(1020, 1887, 280, 40));
        // padlo
        returnList.add(new Obstacle(910, 2132, 800, 40));
        returnList.add(new Obstacle(0, 2172, 1000, 40));
        // fal
        returnList.add(new Obstacle(0, 1890, 5, 250));
        return returnList;
    }

    private List<Creature> creatureListCreator() {
        List<Creature> creatureList = new ArrayList<>();
//        creatureList.add(new Troll(1900, 190, 1504, 1920));
//        creatureList.add(new Troll(2600, 190, 2330, 3020));
//        creatureList.add(new Troll(1900, 500, 1670, 2775));
//        creatureList.add(new Slider(500,1100));
//        creatureList.add(new Slider(2458,1127));
//        creatureList.add(new Boss(5,1925, 13, 230));//activ 440
        return creatureList;
    }

}
