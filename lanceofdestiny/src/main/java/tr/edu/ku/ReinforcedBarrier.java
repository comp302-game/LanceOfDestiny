package tr.edu.ku;

import java.util.Random;

public class ReinforcedBarrier extends Barrier {
    private int hitsTaken;
    private int MAX_HITS = 3;

    public ReinforcedBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hitsTaken = 0;

        // Create a Random object
        Random random = new Random();
        // Generate a random number between 0 and 3 (inclusive)
        MAX_HITS = random.nextInt(4) + 3;
    }

    public void hit() {
        hitsTaken++;
    }


    public int getHitsTaken() {
        return hitsTaken;
    }

    public int getMaxHits() {
        return MAX_HITS;
    }
}