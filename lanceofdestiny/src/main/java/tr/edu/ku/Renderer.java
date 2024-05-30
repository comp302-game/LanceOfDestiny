package tr.edu.ku.GameEngine.Render;

import java.awt.Graphics2D;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.Bullet;
import tr.edu.ku.Domain.Spell.SpellController;
import tr.edu.ku.GameArea.EditingArea;
import tr.edu.ku.GameArea.GameArea;

public class Renderer {

    private Animator animator;

    public Renderer () {
        animator = new Animator();
    }


    public void renderRunning(Graphics2D g, GameArea gameArea) {

        synchronized (gameArea.getLock()) {
            animator.renderFireBall(g, gameArea.getBall());
            animator.renderMagicalStaff(g, gameArea.getStaff());
        

            for(Barrier barrier : gameArea.getAllBarriers()) {
                animator.renderBarrier(g, barrier);
            }
        }
        
        
        if(SpellController.getInstance().is_HEX_Active()) {
            synchronized (SpellController.getInstance().getCurrentHEX().getLock()) {
                if(SpellController.getInstance().getCurrentHEX().getBullets() != null) {
                    for (Bullet bullet : SpellController.getInstance().getCurrentHEX().getBullets()) {
                        if(bullet != null){
                            animator.renderBullets(g, bullet);
                        }
                    }
                }
            }
        }
    }

    





//EDITING MODE RENDERER
    public void renderEditing(Graphics2D g, EditingArea editingArea) {
        
        //PAINT THE LABELS THAT USER CAN CHOOSE FROM
        g.drawImage(Constants.simple_image, 1605, 20, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, null);
        g.drawImage(Constants.firm_image, 1605, 70, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, null);
        g.drawImage(Constants.explosive_image, 1605, 120, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, null);
        g.drawImage(Constants.rewarding_image, 1605, 170, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, null);

        
        // Draw simple barriers from editor
        for (int i = 0; i < Constants.ROW_NUMBER; i++) {
            for (int j = 0; j < Constants.COLUMN_NUMBER; j++) {
                if(editingArea.getGrid().getCells()[i][j].hasBarrier()){
                    animator.renderBarrier(g, editingArea.getGrid().getCells()[i][j].getBarrier());
                }
            }
        }

    }
}