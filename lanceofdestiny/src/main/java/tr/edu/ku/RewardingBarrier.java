package tr.edu.ku.Domain;

import java.awt.Rectangle;

public class RewardingBarrier extends Barrier {

    private boolean isBroken = false;
    private double speedY = 0; //barrier at 0 speed initially

    public RewardingBarrier(int x, int y, int width, int height) {
        super(x, y, width, height);
        speedX = 0.05;
    }

    public void Break() {
        isBroken = true;
        speedY = 2;
    }

    public boolean isBroken() {
        return isBroken;
    }
    
    public double getSpeedY() {
        return speedY;
    }

    public Rectangle getHitBox() {
        Rectangle hitbox = new Rectangle((int) this.getX() + 8, (int) this.getY() + 2, 24, 24);
        return hitbox;
    }

}
