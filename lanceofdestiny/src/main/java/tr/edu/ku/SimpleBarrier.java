package tr.edu.ku;

import java.util.Random;

import tr.edu.ku.BarrierStrategy.MoveHorizontal;


public class SimpleBarrier extends Barrier {

    public SimpleBarrier(int x, int y, int width, int height, int r, int c) {
        super(x, y, width, height, r, c);
    
        // Create an instance of Random class
    	Random random = new Random();
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
}
