package tr.edu.ku.Domain.BarrierStrategy;

import java.io.Serializable;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;

public class MoveHorizontal implements DynamicBehavior, Serializable {

    private double speed = Constants.BARRIER_HORIZONTAL_SPEED;
  	private double dx = 0;

    @Override
    public void move(Barrier barrier) {

        barrier.setX(barrier.getX() + speed);
		setDx(dx + speed);
		
        if(dx >= 40 || dx <= -40) {
			setSpeed(speed*-1);
		}
    }


	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double s) {
		this.speed = s;
	}

	public double get_dx() {
		return dx;
	}

	public void setDx(double s) {
		this.dx = s;
	}
    
}