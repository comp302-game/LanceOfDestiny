package tr.edu.ku;

import java.awt.Rectangle;

import tr.edu.ku.BarrierStrategy.MoveVertical;

public class RewardingBarrier extends Barrier {

    private boolean isBroken = false;

    public RewardingBarrier(int x, int y, int width, int height,  int r, int c) {
        super(x, y, width, height, r, c);
        this.setIsDynamic(false);
    }
    public void Break() {
        this.setDynamicBehavior(new MoveVertical());
        isBroken = true;
    }

    public boolean isBroken() {
        return isBroken;
    }
    
    public Rectangle getHitBox() {
        Rectangle hitbox = new Rectangle((int) this.getX() + 8, (int) this.getY() + 2, 24, 24);
        return hitbox;
    }

}
