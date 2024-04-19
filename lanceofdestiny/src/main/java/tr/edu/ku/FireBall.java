package tr.edu.ku;

import java.awt.*;
import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class FireBall implements Cloneable {
    private static final int SIZE = 16;
    private static final int SPEED_X = 0;
    private static final int SPEED_Y = -2; //Start with vertical speed

    private double x = GamePanel.WIDTH / 2 - SIZE / 2;
    private double y = 800;
    private double speedX = SPEED_X;
	private double speedY = SPEED_Y;

	private boolean isVisible= true;
	private BufferedImage image;

	public FireBall() {
		try {
            // Load the image
            image = ImageIO.read(getClass().getResourceAsStream("/Assets/200Fireball.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }
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

	public int getSize() {
		return SIZE;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

    public boolean intersects(Rectangle rect) {
        return new Rectangle((int) x, (int) y, SIZE, SIZE).intersects(rect);
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
		return new Rectangle((int) x, (int) y, SIZE, SIZE);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean b){
		this.isVisible = b;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, SIZE, SIZE);
	}

	public void reset() {
		x = GamePanel.WIDTH / 2 - SIZE / 2;
      	y = 700;
        speedX = SPEED_X;
	    speedY = SPEED_Y;
	}


	@Override
	 public Object clone() throws CloneNotSupportedException {
	 return super.clone();
	 }

}