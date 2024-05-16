package tr.edu.ku;

import java.awt.Rectangle;
import tr.edu.ku.Constants;

public class Bullet {

    private int size = Constants.BULLET_SIZE;
    private double x;
    private double y;
    private double speedX;
    private double speedY;

    public Bullet (Double x, Double y, Double speedX, Double speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;

    } 

    public boolean intersects(Rectangle rect) {
        return new Rectangle((int) x, (int) y, size, size).intersects(rect);
    }

	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, size, size);
	}





//GETTER SETTER
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

    
}