package tr.edu.ku.Domain;

import java.util.Random;

import tr.edu.ku.Domain.BarrierStrategy.MoveHorizontal;

public class ReinforcedBarrier extends Barrier {

    private int hitsTaken;
    private int MAX_HITS = 3;

    public ReinforcedBarrier(int x, int y, int width, int height, int r, int c) {
        super(x, y, width, height, r, c);
        this.hitsTaken = 0;

        // Create a Random object
        Random random = new Random();
        // Generate a random number between 3 and 6 (inclusive)
        MAX_HITS = random.nextInt(4) + 3;


    	// Generate a random number between 0 and 1
    	double randomNumber = random.nextDouble();

 		// With %20 probability the barrier is dynamic
    	if (randomNumber <= 0.20) {
            this.setIsDynamic(true);
			dynamicBehavior = new MoveHorizontal();
    	}
        else {
            this.setIsDynamic(false);
        }
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