package tr.edu.ku;

import java.awt.*;

class Renderer {

    private Animator animator;

    public Renderer () {
        animator = new Animator();
    }


    public void renderRunning(Graphics2D g, GameArea gamePanel) {

        animator.renderFireBall(g, gamePanel.getBall());
        animator.renderMagicalStaff(g, gamePanel.getPaddle());

        // Draw simple barriers
        for (SimpleBarrier sbarrier : gamePanel.getSimpleBarriers()) {
            if (sbarrier.isVisible()) {
                animator.renderSimpleBarrier(g, sbarrier);
            }
        }

        // Draw reinforced barriers
        for (ReinforcedBarrier rbarrier : gamePanel.getReinforcedBarriers()) {
            if (rbarrier.isVisible()) {
                animator.renderReinforcedBarrier(g, rbarrier);
            }
        }

        // Draw explosive barriers
        for (ExplosiveBarrier ebarrier : gamePanel.getExplosiveBarriers()) {
            if (ebarrier.isVisible() && ebarrier.isExploded() == false) {
                animator.renderExplosiveBarrier(g, ebarrier);
            }

            else if (ebarrier.isExploded()) {
                animator.renderExplosiveFalling(g, ebarrier);
            }


        }
    }





//EDITING MODE RENDERER
    public void renderEditing(Graphics2D g, EditingArea editingArea) {
        
        //PAINT THE LABELS THAT USER CAN CHOOSE FROM
        SimpleBarrier newSBarrier = new SimpleBarrier(1605, 20, 32, 20); 
        g.drawImage(newSBarrier.getImage(), (int) newSBarrier.getX(), (int) newSBarrier.getY(), newSBarrier.getWidth(), newSBarrier.getHeight(), null);

        ReinforcedBarrier newRBarrier = new ReinforcedBarrier(1605, 70, 32, 20); 
        g.drawImage(newRBarrier.getImage(), (int) newRBarrier.getX(), (int) newRBarrier.getY(), newRBarrier.getWidth(), newRBarrier.getHeight(), null);

        ExplosiveBarrier newEBarrier = new ExplosiveBarrier(1605, 120, 32, 20); 
        g.drawImage(newEBarrier.getImage(), (int) newEBarrier.getX(), (int) newEBarrier.getY(), newEBarrier.getWidth(), newEBarrier.getHeight(), null);


        
        // Draw simple barriers from editor
        for (SimpleBarrier sbarrier : editingArea.getSimpleBarriers()) {
            if (sbarrier.isVisible()) {
                animator.renderSimpleBarrier(g, sbarrier);
            }
        }

        // Draw reinforced barriers from editor
        for (ReinforcedBarrier rbarrier : editingArea.getReinforcedBarriers()) {
            if (rbarrier.isVisible()) {
                animator.renderReinforcedBarrier(g, rbarrier);
            }
        }

        // Draw explosive barriers from editor
        for (ExplosiveBarrier eBarrier : editingArea.getExplosiveBarriers()) {
            if (eBarrier.isVisible()) {
                animator.renderExplosiveBarrier(g, eBarrier);
            }
        }

    }
}