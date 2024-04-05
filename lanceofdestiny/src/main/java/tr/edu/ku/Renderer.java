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
    }





//EDITING MODE RENDERER
    public void renderEditing(Graphics2D g, EditingArea editingArea) {
        
        //PAINT THE LABELS THAT USER CAN CHOOSE FROM
        SimpleBarrier newSBarrier = new SimpleBarrier(1540, 20, 32, 20); 
        g.drawImage(newSBarrier.getImage(), newSBarrier.getX(), newSBarrier.getY(), newSBarrier.getWidth(), newSBarrier.getHeight(), null);

        ReinforcedBarrier newRBarrier = new ReinforcedBarrier(1540, 50, 32, 20); 
        g.drawImage(newRBarrier.getImage(), newRBarrier.getX(), newRBarrier.getY(), newRBarrier.getWidth(), newRBarrier.getHeight(), null);

        
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

    }
}