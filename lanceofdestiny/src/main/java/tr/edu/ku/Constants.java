package tr.edu.ku;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Constants {

    public static final int GAMEPANEL_WIDTH = 1600;
    public static final int GAMEPANEL_HEIGHT = 900;

    public static final int EDITORPANEL_WIDTH = 1670;
    public static final int EDITORPANEL_HEIGHT = 900;

    public static final int STAFF_WIDTH = 160;
    public static final int STAFF_HEIGHT = 20;

    public static final int BARRIER_WIDTH = 32;
    public static final int BARRIER_HEIGHT = 20;

    public static final int FIREBALL_SIZE = 16;
    public static final int FIREBALL_SPEEDX = 0;
    public static final int FIREBALL_SPEEDY = -2;


    public static BufferedImage simple_image;
    public static BufferedImage firm_image;
    public static BufferedImage explosive_image;
    public static BufferedImage fireball_image;
    public static BufferedImage staff_image;
    public static BufferedImage falling_image;
    public static BufferedImage background;
    public static BufferedImage heart_icon;
    public static BufferedImage bluegem;
    public static BufferedImage firm;
    public static BufferedImage redgem;


    Constants() {

        try {
            // Load the image
            simple_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Bluegem.png"));
            firm_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Firm.png"));
            explosive_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Redgem.png"));
            fireball_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Fireball.png"));
            staff_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Player.png"));
            falling_image = ImageIO.read(getClass().getResourceAsStream("/Assets/ExplosiveFalling.png"));
            background = ImageIO.read(getClass().getResourceAsStream("/Assets/200Background.png"));
            heart_icon = ImageIO.read(getClass().getResourceAsStream("/Assets/200Heart.png"));

            bluegem = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconbluegem.png"));
            firm = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconfirm.png"));
            redgem = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconredgem.png"));

            System.out.println("Images loaded succesfuly.");
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }

    }

}
