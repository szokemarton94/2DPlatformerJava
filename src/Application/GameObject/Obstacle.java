package Application.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Obstacle extends InteractiveObject {


    public Obstacle(int x, int y, int width, int height) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        super.tempDisplayImage = tempDisplayImageImporter();
        super.hitBox = new Rectangle(x, y, width, height);
    }


    public void draw(Graphics2D gtd) {
        gtd.draw(hitBox);
    }

    private BufferedImage tempDisplayImageImporter() {
        try {
            return ImageIO.read(new File(new File("").getAbsolutePath().concat("/src/Image_Resources/grayBrick.png")));
        } catch (IOException e) {
            System.out.println("invalid");
        }
        return null;
    }


    @Override
    public void interaction(Player player, List<InteractiveObject> obstacleList) {

    }
}
