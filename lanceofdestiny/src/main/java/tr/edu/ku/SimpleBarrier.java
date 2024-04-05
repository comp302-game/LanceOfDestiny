package tr.edu.ku;

import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class SimpleBarrier extends Barrier {

    private BufferedImage image;

    public SimpleBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);

        try {
            // Load the image
            image = ImageIO.read(new File("C:/Users/90532/Desktop/lance/lance/lanceofdestiny/Assets/200Bluegem.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
		return image;
	}
    
}
