package tr.edu.ku;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;


public class MagicalStaff implements Serializable{
    private int WIDTH = 160; //%10 of screen width
    private int HEIGHT = 20;
    private double SPEED = 2; // not 160 pixels per second
    private double rotationAngle = 0.0;
    private double rotationSpeed = 0.375; //45 degrees per second

    private double center_x = Constants.GAMEPANEL_WIDTH / 2;
    private double center_y = Constants.GAMEPANEL_HEIGHT - 40;

    private boolean collideable = true;

    public MagicalStaff() {

    }

	public double getX() {
		return center_x;
	}

	public void setX(double x) {
		this.center_x = x;
	}

	public double getY() {
		return center_y;
	}

	public void setY(double y) {
		this.center_y = y;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public double getSpeed() {
		return SPEED;
	}

    public double getRotationAngle() {
        return rotationAngle;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationAngle(double angle) {
        this.rotationAngle = angle;
    }

    public boolean isCollideable() {
		return collideable;
	}

    public void setCollideable(boolean b) {
        collideable = b;
	}



    public Polygon getPolygon() {
        
        int[] xPoints = {(int) (center_x - WIDTH/2) , (int) (center_x + WIDTH/2), (int) (center_x + WIDTH/2) , (int) (center_x - WIDTH/2)};
        int[] yPoints = {(int) (center_y - HEIGHT/2) , (int) (center_y - HEIGHT/2), (int) (center_y + HEIGHT/2) , (int) (center_y + HEIGHT/2)};

        AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(-rotationAngle), center_x, center_y);
        
        // Apply rotation to each point
        Point2D.Double[] rotatedPoints = new Point2D.Double[4];
        for (int i = 0; i < 4; i++) {
            rotatedPoints[i] = new Point2D.Double(xPoints[i], yPoints[i]);
            rotation.transform(rotatedPoints[i], rotatedPoints[i]);
        }
        
        // Draw rotated polygon
        int[] rotatedXPoints = new int[4];
        int[] rotatedYPoints = new int[4];
        for (int i = 0; i < 4; i++) {
            rotatedXPoints[i] = (int) rotatedPoints[i].getX();
            rotatedYPoints[i] = (int) rotatedPoints[i].getY();
        }

        return new Polygon(rotatedXPoints, rotatedYPoints, 4);
    }


    public void reset() {
        rotationAngle = 0;
        center_x = Constants.GAMEPANEL_WIDTH / 2;
        center_y = Constants.GAMEPANEL_HEIGHT- 40;
    }

    
}