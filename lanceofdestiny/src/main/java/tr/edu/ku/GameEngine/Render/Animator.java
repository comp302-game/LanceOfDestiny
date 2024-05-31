package tr.edu.ku.GameEngine.Render;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.Bullet;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.HollowBarrier;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.Domain.Spell.SpellController;

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
        g2d.drawImage(Constants.staff_image, xpos, ypos, staff.getWidth(), staff.getHeight(), null);

        if (SpellController.getInstance().is_HEX_Active()) {
            int ypos2 = (int) (staff.getY()-staff.getHeight()/2 - 40);

            if(SpellController.getInstance().is_MSE_Active()) {
                g2d.drawImage(Constants.canonbig, xpos, ypos2, 320, 120, null);  
            }
            else {
                g2d.drawImage(Constants.canon, xpos, ypos2, 160, 60, null);
            }
        }

        g2d.setTransform(originalTransform);

        
    }

    public void renderFireBall(Graphics2D g2d, FireBall fireBall) {
        g2d.drawImage(Constants.fireball_image, (int) fireBall.getX(), (int) fireBall.getY(), fireBall.getSize(), fireBall.getSize(), null);
    }

    private void renderSimpleBarrier(Graphics2D g2d, SimpleBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.simple_image, (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    }

    private void renderReinforcedBarrier(Graphics2D g2d, ReinforcedBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.firm_image, (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 20)); // Font name, style, size
            g2d.drawString(""+(barrier.getMaxHits()-barrier.getHitsTaken()), (int) (barrier.getX()+12), (int) (barrier.getY()+18));
        }
    }

    private void renderExplosiveBarrier(Graphics2D g2d, ExplosiveBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.explosive_image, (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    }


    private void renderHollowBarrier(Graphics2D g2d, HollowBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.purple, (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    }
    

    private void renderRewardingBarrier(Graphics2D g2d, RewardingBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.rewarding_image, (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
    }


    private void renderExplosiveFalling(Graphics2D g2d, ExplosiveBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.falling_image, (int) barrier.getX(), (int) barrier.getY(), 9, 9, null);
            g2d.drawImage(Constants.falling_image, (int) (barrier.getX() + 10), (int) (barrier.getY()+6), 9, 9, null);
            g2d.drawImage(Constants.falling_image, (int) (barrier.getX() + 20), (int) (barrier.getY()+3), 9, 9, null);
        }     
    }


    private void renderRewardingFalling(Graphics2D g2d, RewardingBarrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.reward, (int) barrier.getX()+8, (int) barrier.getY()+2, 30, 30, null);
        }
             
    }

    private void renderFrozenBarrier(Graphics2D g2d, Barrier barrier) {
        if (barrier.isVisible()) {
            g2d.drawImage(Constants.frozen, (int) barrier.getX(), (int) barrier.getY(), barrier.getWidth(), barrier.getHeight(), null);
        }
             
    }


    public void renderBullets(Graphics2D g2d, Bullet bullet) {
        g2d.drawImage(Constants.falling_image, (int) bullet.getX(), (int) bullet.getY(), 9, 9, null); 
    }



    public void renderBarrier(Graphics2D g2d, Barrier barrier) {

        if(barrier.isFrozen()){
            renderFrozenBarrier(g2d, barrier);
        }

        else {
        if (barrier instanceof SimpleBarrier) {
            renderSimpleBarrier(g2d, (SimpleBarrier)barrier);
        }

        else if (barrier instanceof ReinforcedBarrier) {
            renderReinforcedBarrier(g2d, (ReinforcedBarrier)barrier);
        }

        else if (barrier instanceof ExplosiveBarrier) {
            if (barrier.isVisible()){
                if(((ExplosiveBarrier)barrier).isExploded() == false) {
                    renderExplosiveBarrier(g2d, (ExplosiveBarrier) barrier);
                }

                else {
                    renderExplosiveFalling(g2d, (ExplosiveBarrier) barrier);
                }
            } 
        }

        else if (barrier instanceof RewardingBarrier) {
            if (barrier.isVisible()){
                if(((RewardingBarrier)barrier).isBroken() == false) {
                    renderRewardingBarrier(g2d, (RewardingBarrier) barrier);
                }

                else {
                    renderRewardingFalling(g2d, (RewardingBarrier) barrier);
                }
            } 
        }

        else if (barrier instanceof HollowBarrier) {
            renderHollowBarrier(g2d, (HollowBarrier)barrier);
        }
        }
    }
}
