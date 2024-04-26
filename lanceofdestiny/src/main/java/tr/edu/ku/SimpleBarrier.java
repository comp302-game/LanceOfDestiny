package tr.edu.ku;

import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;

public class SimpleBarrier extends Barrier {

    public SimpleBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);

        try {
            // Load the image
            image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Bluegem.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }
    }
}
