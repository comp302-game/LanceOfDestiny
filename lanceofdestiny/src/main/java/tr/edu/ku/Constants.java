package tr.edu.ku;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Constants {

    public static final int GAMEPANEL_WIDTH = 1600;
    public static final int GAMEPANEL_HEIGHT = 900;

    public static final int EDITORPANEL_WIDTH = 1670;
    public static final int EDITORPANEL_HEIGHT = 900;

    public static final int STAFF_WIDTH = 160; //%10 of screen width
    public static final int STAFF_HEIGHT = 20;
    public static final int STAFF_ENLARGED_WIDTH = 320;
    public static final int STAFF_ENLARGED_HEIGHT = 40;
    public static final double STAFF_ROTATION_SPEED = 0.167;  //20 degrees per second
    public static final double STAFF_ROTATION_RETURN_SPEED = 0.375;
    public static final double STAFF_SPEED = 2; //CHANGE LATER

    public static final int BARRIER_WIDTH = 32;
    public static final int BARRIER_HEIGHT = 20;
    public static final double BARRIER_HORIZONTAL_SPEED = 0.28; // L/4
    public static final double BARRIER_CIRCULAR_SPEED = 0.05;
    public static final double BARRIER_VERTICAL_SPEED = 3; 
    public static final int BARRIER_CIRCULAR_RADIUS = 240;

    public static final int FIREBALL_SIZE = 16;
    public static final int FIREBALL_SIZE_ENLARGED = 32;
    public static final int FIREBALL_SPEEDX = 0;
    public static final int FIREBALL_SPEEDY = -2;

    public static final int HEX_SPEED = 4;
    public static final int BULLET_SIZE = 9;

    public static final int CELL_WIDTH = 64;
    public static final int CELL_HEIGHT = 50;
    public static final int COLUMN_NUMBER = 25;
    public static final int ROW_NUMBER = 12; //MAX TOTAL 300 BARRIERS


    public static BufferedImage simple_image;
    public static BufferedImage firm_image;
    public static BufferedImage explosive_image;
    public static BufferedImage rewarding_image;
    public static BufferedImage fireball_image;
    public static BufferedImage staff_image;
    public static BufferedImage falling_image;
    public static BufferedImage reward;
    public static BufferedImage background;
    public static BufferedImage heart_icon;
    public static BufferedImage bluegem;
    public static BufferedImage firm;
    public static BufferedImage redgem;
    public static BufferedImage greengem;
    public static BufferedImage purple;

    public static BufferedImage fireball2x;
    public static BufferedImage player2x;
    public static BufferedImage hex;
    public static BufferedImage felix;

    public static BufferedImage canon;


    Constants() {

        try {
            // Load the image
            simple_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Bluegem.png"));
            firm_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Firm.png"));
            explosive_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Redgem.png"));
            rewarding_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Greengem.png"));
            fireball_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Fireball.png"));
            staff_image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Player.png"));
            falling_image = ImageIO.read(getClass().getResourceAsStream("/Assets/ExplosiveFalling.png"));
            reward = ImageIO.read(getClass().getResourceAsStream("/Assets/reward.png"));
            background = ImageIO.read(getClass().getResourceAsStream("/Assets/200Background.png"));
            heart_icon = ImageIO.read(getClass().getResourceAsStream("/Assets/200Heart.png"));

            bluegem = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconbluegem.png"));
            firm = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconfirm.png"));
            redgem = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconredgem.png"));
            greengem = ImageIO.read(getClass().getResourceAsStream("/Assets/200icongreengem.png"));

            fireball2x = ImageIO.read(getClass().getResourceAsStream("/Assets/overwhelm.png"));
            player2x = ImageIO.read(getClass().getResourceAsStream("/Assets/2xstaff.png"));
            hex = ImageIO.read(getClass().getResourceAsStream("/Assets/hex.png"));
            felix = ImageIO.read(getClass().getResourceAsStream("/Assets/felix.png"));
            canon = ImageIO.read(getClass().getResourceAsStream("/Assets/canon.png"));

            purple = ImageIO.read(getClass().getResourceAsStream("/Assets/PurpleBarrier.png"));
            

            System.out.println("Images loaded succesfuly.");
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }

    }

}
