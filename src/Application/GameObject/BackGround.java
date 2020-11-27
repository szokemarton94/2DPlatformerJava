package Application.GameObject;
//additional info? height:  //748; //748 //374 width: // 3108; //3108      //1554

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackGround extends GameObject {
    public BackGround(int height, int width, String pathname) {
        super(0, 0, width, height); //super()call to superclass constructor
        super.tempDisplayImage = imageMaker(pathname);
    }


    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(tempDisplayImage, x, y, width, height, null);
    }

    private BufferedImage imageMaker(String pathname) {
        try {
            return (ImageIO.read(new File(pathname)));
        } catch (IOException ignored) {
        }
        return null;
    }
}
