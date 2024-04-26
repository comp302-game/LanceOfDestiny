package tr.edu.ku;

import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;

public class ReinforcedBarrier extends Barrier {
    private int hitsTaken;
    private static final int MAX_HITS = 3;

    public ReinforcedBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitsTaken = 0;

        try {
            // Load the image
            image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Firm.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }

    }

    public void hit() {
        hitsTaken++;
    }


    public int getHitsTaken() {
        return hitsTaken;
    }
}