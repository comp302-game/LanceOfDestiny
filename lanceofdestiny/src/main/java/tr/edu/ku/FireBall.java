package tr.edu.ku;

import java.awt.*;
import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class FireBall {
    private static final int SIZE = 16;
    private static final int SPEED_X = 3;
    private static final int SPEED_Y = -3;

    private int x = GamePanel.WIDTH / 2 - SIZE / 2;
    private int y = GamePanel.HEIGHT / 2 - SIZE / 2;
    private double speedX = SPEED_X;
	private double speedY = SPEED_Y;

	private boolean isVisible= true;
	private BufferedImage image;

	public FireBall() {
		try {
            // Load the image
            image = ImageIO.read(new File("C:/Users/90532/Desktop/lance/lance/lanceofdestiny/Assets/200Fireball.png"));
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
        return new Rectangle(x, y, SIZE, SIZE).intersects(rect);
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, SIZE, SIZE);
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

}