package tr.edu.ku.Domain.BarrierStrategy;

import java.io.Serializable;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;

public class MoveVertical implements DynamicBehavior, Serializable {

    private double fall_speed = Constants.BARRIER_VERTICAL_SPEED;

    @Override
    public void move(Barrier barrier) {
        barrier.setY((int) (barrier.getY() + fall_speed));
    }


    public double getSpeed() {
		  return fall_speed;
	  }

    public void setSpeed(double s) {
		  this.fall_speed = s;
	  }
    
}
