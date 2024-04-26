package tr.edu.ku;

import java.awt.*;
import java.io.Serializable;

public class FireBall implements Serializable {

    private double x;
    private double y;
    private double speedX = Constants.FIREBALL_SPEEDX;
	private double speedY = Constants.FIREBALL_SPEEDY;
	private int size = Constants.FIREBALL_SIZE;
	private boolean isVisible= true;

	public FireBall(double x, double y) {
		this.x = x;
		this.y = y;
	}
    

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

    public boolean intersects(Rectangle rect) {
        return new Rectangle((int) x, (int) y, Constants.FIREBALL_SIZE, Constants.FIREBALL_SIZE).intersects(rect);
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

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, Constants.FIREBALL_SIZE, Constants.FIREBALL_SIZE);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean b){
		this.isVisible = b;
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, Constants.FIREBALL_SIZE, Constants.FIREBALL_SIZE);
	}

	public void reset() {
		x = Constants.GAMEPANEL_WIDTH / 2 - Constants.FIREBALL_SIZE / 2;
      	y = 700;
        speedX = Constants.FIREBALL_SPEEDX;
	    speedY = Constants.FIREBALL_SPEEDY;
	}

}