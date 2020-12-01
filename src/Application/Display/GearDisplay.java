package Application.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GearDisplay {
    List<BufferedImage> imageList;

    public GearDisplay() {
        this.imageList = leftMovementMaker();
    }

    public List<BufferedImage> getImageList() {
        return imageList;
    }

    private List<BufferedImage> leftMovementMaker() {
        List<BufferedImage> returnList = new ArrayList<>();
        try {

            //File e = new File("gear.png");
            returnList.add(ImageIO.read(new File(new File("").getAbsolutePath().concat("/src/Image_Resources/gear.png"))));


        } catch (IOException e) {
            System.out.println("PNG not found");
        }
        return returnList;
    }
}
