/**package GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;


public class ImageLoad {

    private BufferedImage image;
    public static String menubackground="/menubackground.png";

    public ImageLoad(String path) {try {
        image = ImageIO.read(Objects.requireNonNull(ImageLoad.class.getResourceAsStream(path)));

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public BufferedImage getImage(){
        return image;
    }
}

package GUI;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoad {
    private BufferedImage img;
    public static String menubackground = "/menubackground.png";



    public ImageLoad(String path) {
        try {
            img = ImageIO.read(ImageLoad.class.getResourceAsStream(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getImage() {
        return img;
    }


    public BufferedImage getSubImage(int section) {
        return img.getSubimage(0, section*25, 50, 25);
    }

}**/