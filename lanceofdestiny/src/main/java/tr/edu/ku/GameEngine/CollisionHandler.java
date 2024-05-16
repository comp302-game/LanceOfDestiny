package tr.edu.ku.GameEngine;

import java.util.ArrayList;
import java.awt.*;
import java.util.Iterator;

import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.SimpleBarrier;

public class CollisionHandler {

    private FireBall ball;
    private MagicalStaff paddle;
    private ArrayList<SimpleBarrier> simpleBarriers;
	private ArrayList<ReinforcedBarrier> reinforcedBarriers;
    private ArrayList<ExplosiveBarrier> explosiveBarriers;
    private MovementHandler movement = new MovementHandler(this);

    private ReinforcedBarrier incident_rbarrier; //For safe collision with reinforced barrier

    public CollisionHandler(FireBall fireball, MagicalStaff staff, ArrayList<ReinforcedBarrier>  rbList, ArrayList<SimpleBarrier> sbList, ArrayList<ExplosiveBarrier> ebList){
        this.ball = fireball;
        this.paddle = staff;
        this.reinforcedBarriers = rbList;
        this.simpleBarriers = sbList;
        this.explosiveBarriers = ebList;
    }
    
    public boolean checkAnyCollision() {

        ResolveIncident();
        ResolveStaffCollision();
        boolean inc_score = false; //increment score signal if barrier is destroyed.

        // Check collision between ball and paddle
        if (paddle.getPolygon().intersects(ball.getBounds()) && paddle.isCollideable()) {
            movement.reflect(paddle, ball);
            paddle.setCollideable(false);
        }
        
        // Check collision with simple barriers
        Iterator<SimpleBarrier> simpleBarrierIterator = simpleBarriers.iterator();
        while (simpleBarrierIterator.hasNext()) {
            SimpleBarrier barrier = simpleBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) {  
                simpleBarrierIterator.remove(); // remove the barrier using Iterator
                movement.reflect(barrier, ball);
                inc_score = true;
            }
        }

        // Check collision with reinforced barriers
        Iterator<ReinforcedBarrier> reinforcedBarrierIterator = reinforcedBarriers.iterator();
        while (reinforcedBarrierIterator.hasNext()) {
            ReinforcedBarrier barrier = reinforcedBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) {      	
                movement.reflect(barrier, ball);
                barrier.hit();
                
                if (barrier.getHitsTaken() >= barrier.getMaxHits()) {
                    reinforcedBarrierIterator.remove(); // remove the barrier using Iterator
                    inc_score = true;
                }

                //For safe collision check
                barrier.setCollideable(false);
                incident_rbarrier = barrier;
            }
        }

        // Check collision with explosive barriers
        Iterator<ExplosiveBarrier> explosiveBarrierIterator = explosiveBarriers.iterator();
        while (explosiveBarrierIterator.hasNext()) {
            ExplosiveBarrier barrier = explosiveBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && !barrier.isExploded() && barrier.getCollideable()) {            	
                movement.reflect(barrier, ball);
                barrier.explode();
                inc_score = true;
            }

            if(barrier.isExploded()) {
                if(barrier.getY() >= 900) {
                    explosiveBarrierIterator.remove(); // remove the barrier using Iterator
                }
            }
        }

        return inc_score;
    }


    public int checkStaffCollisions() {
        int type = 0;  //1 for explosive pieces, 2 for spell catch

        // Check collision with explosive falling pieces.
        for (ExplosiveBarrier barrier : explosiveBarriers) {
            for(Rectangle piece : barrier.getHitboxes()) {
                if(paddle.getPolygon().intersects(piece) && barrier.isVisible()) {
                    barrier.setVisible(false);
                    type = 1; 
                }
            }
        }

        //Check for spell catch
        return type;

    }

    
    private void ResolveIncident() {
        if(incident_rbarrier != null && ball.intersects(incident_rbarrier.getBounds()) == false){
            incident_rbarrier.setCollideable(true);
            incident_rbarrier = null;
        }
    }


    private void ResolveStaffCollision() {
        Rectangle bounds = new Rectangle((int) ball.getX()-3, (int) ball.getY()-3, 22, 22);
        if(paddle.getPolygon().intersects(bounds) == false) {
            paddle.setCollideable(true);
        }
    }



    public void checkBarrierCollisions(Barrier barrier) {
        //TO BE IMPLEMENTED
        // Check collision with simple barriers
        Iterator<SimpleBarrier> simpleBarrierIterator = simpleBarriers.iterator();
        while (simpleBarrierIterator.hasNext()) {
            SimpleBarrier sbarrier = simpleBarrierIterator.next();
            if (sbarrier.isVisible() && sbarrier != null && sbarrier.intersects(barrier.getBounds()) && sbarrier.getCollideable()) {  
                movement.reflect(barrier, sbarrier);
            }
        }

        // Check collision with reinforced barriers
        Iterator<ReinforcedBarrier> reinforcedBarrierIterator = reinforcedBarriers.iterator();
        while (reinforcedBarrierIterator.hasNext()) {
            ReinforcedBarrier rbarrier = reinforcedBarrierIterator.next();
            if (rbarrier.isVisible() && rbarrier != null && rbarrier.intersects(barrier.getBounds()) && rbarrier.getCollideable()) {      	
                movement.reflect(barrier, rbarrier);
            }
        }

        // Check collision with explosive barriers
        Iterator<ExplosiveBarrier> explosiveBarrierIterator = explosiveBarriers.iterator();
        while (explosiveBarrierIterator.hasNext()) {
            ExplosiveBarrier ebarrier = explosiveBarrierIterator.next();
            if (ebarrier.isVisible() && ebarrier != null && ebarrier.intersects(barrier.getBounds()) && !ebarrier.isExploded() && ebarrier.getCollideable()) {            	
                movement.reflect(barrier, ebarrier);
            }
        }
    }


}
