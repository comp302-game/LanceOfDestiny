package tr.edu.ku;

import java.awt.*;

public class Animator {

    public void renderMagicalStaff(Graphics2D g2d, MagicalStaff staff) {
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(staff.getPolygon());
        
    }

    public void renderFireBall(Graphics2D g2d, FireBall fireBall) {
        g2d.drawImage(fireBall.getImage(), (int) fireBall.getX(), (int) fireBall.getY(), fireBall.getSize(), fireBall.getSize(), null);
    }


    public void renderSimpleBarrier(Graphics2D g2d, SimpleBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(barrier.getImage(), barrier.getX(), barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    }

    public void renderReinforcedBarrier(Graphics2D g2d, ReinforcedBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(barrier.getImage(), barrier.getX(), barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    }



    
}
