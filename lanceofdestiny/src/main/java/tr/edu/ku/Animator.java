package tr.edu.ku;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Animator {

    public void renderMagicalStaff(Graphics2D g2d, MagicalStaff staff) {
        
        double rotationAngle = Math.toRadians(staff.getRotationAngle());

        // Save the original transformation
        AffineTransform originalTransform = g2d.getTransform();
        // Create an AffineTransform for rotation
        AffineTransform tx = new AffineTransform();
        tx.rotate(-rotationAngle, staff.getX(), staff.getY());

        // Apply the transformation to the Graphics2D object
        g2d.setTransform(tx);
        // Draw the rotated image
        int xpos = (int) (staff.getX()-staff.getWidth()/2);
        int ypos = (int) (staff.getY()-staff.getHeight()/2);
        g2d.drawImage(staff.getImage(), xpos, ypos, 160, 20, null);

        g2d.setTransform(originalTransform);
    }

    public void renderFireBall(Graphics2D g2d, FireBall fireBall) {
        g2d.drawImage(fireBall.getImage(), (int) fireBall.getX(), (int) fireBall.getY(), fireBall.getSize(), fireBall.getSize(), null);
    }

    public void renderSimpleBarrier(Graphics2D g2d, SimpleBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(barrier.getImage(), (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    }

    public void renderReinforcedBarrier(Graphics2D g2d, ReinforcedBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(barrier.getImage(), (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16)); // Font name, style, size
            g2d.drawString(""+(3-barrier.getHitsTaken()), (int) (barrier.getX()+13), (int) (barrier.getY()+16));
        }
    }

    public void renderExplosiveBarrier(Graphics2D g2d, ExplosiveBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(barrier.getImage(), (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    } 

    public void renderExplosiveFalling(Graphics2D g2d, ExplosiveBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(barrier.getFallingImage(), (int) barrier.getX(), (int) barrier.getY(), 9, 9, null);
            g2d.drawImage(barrier.getFallingImage(), (int) (barrier.getX() + 10), (int) (barrier.getY()+6), 9, 9, null);
            g2d.drawImage(barrier.getFallingImage(), (int) (barrier.getX() + 20), (int) (barrier.getY()+3), 9, 9, null);
        }     
    }
}
