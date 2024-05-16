package tr.edu.ku;

import java.awt.Rectangle;
import java.io.Serializable;

import tr.edu.ku.Constants;


public class FireBall implements Serializable {

	private static final long serialVersionUID = 5L;

    private double x;
    private double y;
    private double speedX = Constants.FIREBALL_SPEEDX;
	private double speedY = Constants.FIREBALL_SPEEDY;
	private int size = Constants.FIREBALL_SIZE;
	private boolean isVisible= true;
	private boolean collideable = true;

	public FireBall(double x, double y) {
		this.x = x;
		this.y = y;
	}
    


	public void reset() {
		x = Constants.GAMEPANEL_WIDTH / 2 - Constants.FIREBALL_SIZE / 2;
      	y = 700;
        speedX = Constants.FIREBALL_SPEEDX;
	    speedY = Constants.FIREBALL_SPEEDY;
	}


	public boolean intersects(Rectangle rect) {
        return new Rectangle((int) x, (int) y, size, size).intersects(rect);
    }

	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, size, size);
	}






//GETTER SETTER
	public int getSize() {
		return size;
	}

	public void setSize(int s) {
		this.size = s;
	}
    
    public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean b){
		this.isVisible = b;
	}

	public boolean isCollideable() {
		return collideable;
	}

	public void setCollideable(boolean b){
		this.collideable = b;
	}

}