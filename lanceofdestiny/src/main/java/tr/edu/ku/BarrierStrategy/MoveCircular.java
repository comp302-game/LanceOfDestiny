package tr.edu.ku.Domain.BarrierStrategy;

import java.io.Serializable;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;

public class MoveCircular implements Serializable, DynamicBehavior{

    private double speed = Constants.BARRIER_CIRCULAR_SPEED;
    private int radius = Constants.BARRIER_CIRCULAR_RADIUS;
    private double angle = 270; //starting angle is 270 degrees

    //Circular Movement origin
    private int originX;
    private int originY;

    
    @Override
    public void move(Barrier barrier) {
        setAngle(angle + speed);
		double origin_angle = Math.toRadians(angle);

		int barrierOriginX = originX + (int) (radius * Math.cos(origin_angle));
		int barrierOriginY = originY - (int) (radius * Math.sin(origin_angle));

		barrier.setX(barrierOriginX-16); 
		barrier.setY(barrierOriginY-10);

        checkWallCollision(barrier);
    }


    private void checkWallCollision(Barrier barrier) {
        if(barrier.getX() < 0 || barrier.getX() > 1568 || barrier.getY() < 0 || barrier.getY() > 880) {
            setSpeed(getSpeed()*-1);
        }
    }




//GETTER SETTER 
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double s) {
		this.speed = s;
	}

    public double getAngle() {
        return angle;
    }

    public void setAngle(double a) {
        this.angle = a;
    }

    public int getRadius() {
        return radius;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginX(int x) {
        this.originX = x;
    }

    public void setOriginY(int y) {
        this.originY = y;
    }
}
