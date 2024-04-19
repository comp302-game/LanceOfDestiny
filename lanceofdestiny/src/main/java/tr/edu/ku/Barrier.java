package tr.edu.ku;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

//SUPERCLASS FOR BARRIERS
public class Barrier implements Cloneable{
  protected double x;
  protected double y;
  protected int width;
  protected int height;
  protected boolean visible;
  protected BufferedImage image;
  protected Boolean collideable;
  protected double speedX = 0.28; //Aprrox L/4 pixels /s
  protected boolean isDynamic;
  protected boolean isMoving;
  protected double dx;

  public Barrier(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.visible = true;
	this.collideable = true;
	this.dx = 0.0;
	this.isMoving = false;

	// Create an instance of Random class
    Random random = new Random();

    // Generate a random number between 0 and 1
    double randomNumber = random.nextDouble();

 	// With %20 probability the barrier is dynamic
    if (randomNumber < 0.2) {
		isDynamic = true;
    }
}


  public Rectangle getBounds() {
      return new Rectangle((int) x, (int) y, width, height);
  }

  public boolean isVisible() {
      return visible;
  }

  public void setVisible(boolean visible) {
      this.visible = visible;
  }

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean intersects(Rectangle rect) {
        return new Rectangle((int) x, (int) y, width, height).intersects(rect);
    }

	public BufferedImage getImage() {
		return image;
	}

	@Override
	 public Object clone() throws CloneNotSupportedException {
	 return super.clone();
	 }


	public boolean getCollideable() {
		return collideable;
	}

	public void setCollideable(Boolean b) {
		this.collideable = b;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public Rectangle getPath() {
		return new Rectangle((int) x-32, (int) y, 96, 20);
	}

	public void setIsMoving(boolean b) {
		this.isMoving = b;
	}

	public double getSpeed() {
		return speedX;
	}

	public void setSpeed(double s) {
		this.speedX = s;
	}

	public double get_dx() {
		return dx;
	}

	public void setDx(double s) {
		this.dx = s;
	}

}