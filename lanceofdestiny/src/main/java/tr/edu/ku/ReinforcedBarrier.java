package tr.edu.ku;

import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class ReinforcedBarrier extends Barrier {
    private int hitsTaken;
    private static final int MAX_HITS = 3;
    private BufferedImage image;

    public ReinforcedBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitsTaken = 0;

        try {
            // Load the image
            image = ImageIO.read(new File("C:/Users/90532/Desktop/lance/lance/lanceofdestiny/Assets/200Firm.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }

    }

    public void hit() {
        hitsTaken++;
        if (hitsTaken >= MAX_HITS) {
            visible = false;
        }
    }


    public BufferedImage getImage() {
		return image;
	}
}