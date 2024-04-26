package tr.edu.ku;

import java.awt.Rectangle;
import java.util.ArrayList;


public class ExplosiveBarrier extends Barrier{

    private boolean isExploded = false;
    private double speedY = 0; //barrier at 0 speed initially
    
    private double angle = 270; //starting angle is 270 degrees
    private int radius = 240;

    private int originX;
    private int originY;

    public ExplosiveBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);
        speedX = 0.05;
    }

    public void explode() {
        isExploded = true;
        speedY = 2;
    }

    public boolean isExploded() {
        return isExploded;
    }
    
    public double getSpeedY() {
        return speedY;
    }


    public ArrayList<Rectangle> getHitboxes() {
        ArrayList<Rectangle> pieces = new ArrayList<>();
        Rectangle piece1 = new Rectangle((int) this.getX(), (int) this.getY(), 9, 9);
        Rectangle piece2 = new Rectangle((int) (this.getX()+10), (int) (this.getY()+6), 9, 9);
        Rectangle piece3 = new Rectangle((int) (this.getX()+20), (int) (this.getY()+3), 9, 9);
        pieces.add(piece1);
        pieces.add(piece2);
        pieces.add(piece3);
        return pieces;
    }

    public Rectangle getPath() {
        return new Rectangle((int) (this.getX() - 224), (int) (this.getY()- 2*radius + 10), 480, 480);
    }

    public double getAngle() {
        return angle;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setAngle(double a) {
        this.angle = a;
    }

    public int getRadius() {
        return radius;
    }

    public void setOriginX(int x) {
        this.originX = x;
    }

    public void setOriginY(int y) {
        this.originY = y;
    }

}
