package tr.edu.ku.Domain;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import tr.edu.ku.Constants;


public class MagicalStaff implements Serializable{
    private int WIDTH = Constants.STAFF_WIDTH; 
    private int HEIGHT = Constants.STAFF_HEIGHT;
    private double SPEED = Constants.STAFF_SPEED; 
    private double rotationSpeed = Constants.STAFF_ROTATION_SPEED;

    private double rotationAngle = 0.0;
    private double center_x = Constants.GAMEPANEL_WIDTH / 2;
    private double center_y = Constants.GAMEPANEL_HEIGHT - 40;

    private boolean collideable = true;

    public MagicalStaff() {

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



//GETTER SETTER
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

    public void setWidth(int w) {
		this.WIDTH = w;
	}

	public void setHeight(int h) {
		this.HEIGHT = h;
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

}
