package tr.edu.ku;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import tr.edu.ku.Constants;
import tr.edu.ku.BarrierStrategy.MoveCircular;
import tr.edu.ku.BarrierStrategy.MoveVertical;



public class ExplosiveBarrier extends Barrier{

    private boolean isExploded = false;

    public ExplosiveBarrier(int x, int y, int width, int height, int r, int c) {
        super(x, y, width, height, r, c);

        // Create a Random object
        Random random = new Random();

        // Generate a random number between 0 and 1
    	double randomNumber = random.nextDouble();

 		// With %20 probability the barrier is dynamic
    	if (randomNumber <= 0.20) {
            this.setIsDynamic(true);
			dynamicBehavior = new MoveCircular(); //pass the origin of the circular movement
    	}
        else {
            this.setIsDynamic(false);
        }
    }

    public void explode() {
        dynamicBehavior = new MoveVertical();
        isExploded = true;
    }

    public boolean isExploded() {
        return isExploded;
    }
    
    public ArrayList<Rectangle> getHitboxes() {
        ArrayList<Rectangle> pieces = new ArrayList<>();
        Rectangle piece1 = new Rectangle((int) this.getX(), (int) this.getY(), 9, 9);
        Rectangle piece2 = new Rectangle((int) (this.getX()+10), (int) (this.getY()+6), 9, 9);
        Rectangle piece3 = new Rectangle((int) (this.getX()+20), (int) (this.getY()+3), 9, 9);
        pieces.add(piece1);
        pieces.add(piece2);
        pieces.add(piece3);
        return pieces;
    }

    public Rectangle getPath() {
        return new Rectangle((int) (this.getX() - 224), (int) (this.getY()- 2*Constants.BARRIER_CIRCULAR_RADIUS + 10), 480, 480);
    }

}