package tr.edu.ku.GameEngine;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.Bullet;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.SpellController;

public class CollisionHandler {

    private FireBall ball;
    private MagicalStaff paddle;
    private ArrayList<SimpleBarrier> simpleBarriers;
	private ArrayList<ReinforcedBarrier> reinforcedBarriers;
    private ArrayList<ExplosiveBarrier> explosiveBarriers;
    private ArrayList<RewardingBarrier> rewardingBarriers;
    

    private MovementHandler movement = new MovementHandler(this);
    private ReinforcedBarrier incident_rbarrier; //For safe collision with reinforced barrier

    public CollisionHandler(FireBall fireball, MagicalStaff staff, ArrayList<ReinforcedBarrier>  rbList, ArrayList<SimpleBarrier> sbList, ArrayList<ExplosiveBarrier> ebList, ArrayList<RewardingBarrier> wbList){
        this.ball = fireball;
        this.paddle = staff;
        this.reinforcedBarriers = rbList;
        this.simpleBarriers = sbList;
        this.explosiveBarriers = ebList;
        this.rewardingBarriers = wbList;

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


        if (SpellController.is_OVM_Active() == false) {
        
        // Check collision with simple barriers
        Iterator<SimpleBarrier> simpleBarrierIterator = simpleBarriers.iterator();
        while (simpleBarrierIterator.hasNext()) {
            SimpleBarrier barrier = simpleBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) {  
                simpleBarrierIterator.remove(); // remove the barrier using Iterator
                movement.reflect(barrier, ball);
                inc_score = true;
            }

            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null) {
                        simpleBarrierIterator.remove(); // remove the barrier using Iterator
                        inc_score = true;
                        bulletIterator.remove();
                    }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }
                }
            }

        }

        // Check collision with reinforced barriers
        Iterator<ReinforcedBarrier> reinforcedBarrierIterator = reinforcedBarriers.iterator();
        while (reinforcedBarrierIterator.hasNext()) {
            ReinforcedBarrier barrier = reinforcedBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) {      	
                movement.reflect(barrier, ball);
                barrier.hit();

                //For safe collision check
                barrier.setCollideable(false);
                incident_rbarrier = barrier;
            }

            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null) {
                        barrier.hit();
                        bulletIterator.remove();
                        }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }

                    }
                }


            if (barrier.getHitsTaken() >= barrier.getMaxHits()) {
                    reinforcedBarrierIterator.remove(); // remove the barrier using Iterator
                    inc_score = true;
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

            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null && !barrier.isExploded()) {
                        barrier.explode();
                        inc_score = true;
                        bulletIterator.remove();
                        }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }
                    }
                }

        }


        // Check collision with rewarding barriers
        Iterator<RewardingBarrier> rewardingBarrierIterator = rewardingBarriers.iterator();
        while (rewardingBarrierIterator.hasNext()) {
            RewardingBarrier barrier = rewardingBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && !barrier.isBroken() && barrier.getCollideable()) {            	
                movement.reflect(barrier, ball);
                barrier.Break();
                inc_score = true;
            }

            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null && !barrier.isBroken()) {
                        
                        barrier.Break();
                        inc_score = true;
                        bulletIterator.remove();
                        }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }
                    
                }
            }
        }
    }



    else {
        // Check collision with simple barriers
        Iterator<SimpleBarrier> simpleBarrierIterator = simpleBarriers.iterator();
        while (simpleBarrierIterator.hasNext()) {
            SimpleBarrier barrier = simpleBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) {  
                simpleBarrierIterator.remove();
                inc_score = true;
            }

            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null) {
                        simpleBarrierIterator.remove(); // remove the barrier using Iterator
                        inc_score = true;
                        bulletIterator.remove();
                    }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }
                }
            }
        }

        // Check collision with reinforced barriers
        Iterator<ReinforcedBarrier> reinforcedBarrierIterator = reinforcedBarriers.iterator();
        while (reinforcedBarrierIterator.hasNext()) {
            ReinforcedBarrier barrier = reinforcedBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) {      	
                barrier.hit();
                inc_score = true;
            }

            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null) {
                        barrier.hit();
                        bulletIterator.remove();
                        }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }
                }
            }

            if (barrier.getHitsTaken() >= barrier.getMaxHits()) {
                reinforcedBarrierIterator.remove(); // remove the barrier using Iterator
                inc_score = true;
                }
        }

        // Check collision with explosive barriers
        Iterator<ExplosiveBarrier> explosiveBarrierIterator = explosiveBarriers.iterator();
        while (explosiveBarrierIterator.hasNext()) {
            ExplosiveBarrier barrier = explosiveBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && !barrier.isExploded() && barrier.getCollideable()) {
                barrier.explode();
                inc_score = true;
            }

            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null && !barrier.isExploded()) {
                        barrier.explode();
                        inc_score = true;
                        bulletIterator.remove();
                        }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }
                }
            }

        }

    
        // Check collision with rewarding barriers
        Iterator<RewardingBarrier> rewardingBarrierIterator = rewardingBarriers.iterator();
        while (rewardingBarrierIterator.hasNext()) {
            RewardingBarrier barrier = rewardingBarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && !barrier.isBroken() && barrier.getCollideable()) {
                barrier.Break();
                inc_score = true;
            }


            if(SpellController.is_HEX_Active()) {
                Iterator<Bullet> bulletIterator = SpellController.getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null && !barrier.isBroken()) {
                        
                        barrier.Break();
                        inc_score = true;
                        bulletIterator.remove();
                        }

                    else if(bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0) {
                        bulletIterator.remove();
                    }
                    
                }
            }
        }
    }


    return inc_score;
}



    public int checkStaffCollisions() {
        int type = 0;  //1 for explosive pieces


        // Check collision with explosive falling pieces
        Iterator<ExplosiveBarrier> explosiveBarrierIterator = explosiveBarriers.iterator();
        while (explosiveBarrierIterator.hasNext()) {
            ExplosiveBarrier barrier = explosiveBarrierIterator.next();
            if(barrier.isExploded() && barrier.isVisible()) {
                for(Rectangle piece : barrier.getHitboxes()) {
                    if(paddle.getPolygon().intersects(piece)) {
                        explosiveBarrierIterator.remove();
                        type = 1; 
                    }
                }
            }

            else if(barrier.getY() >= 900 && barrier.isVisible()) {
                explosiveBarrierIterator.remove();
            }
        }


        // Check collision with spell catch
        Iterator<RewardingBarrier> rewardingBarrierIterator = rewardingBarriers.iterator();
        while (rewardingBarrierIterator.hasNext()) {
            RewardingBarrier barrier = rewardingBarrierIterator.next();
            if(paddle.getPolygon().intersects(barrier.getHitBox()) && barrier.isVisible()) {
                    rewardingBarrierIterator.remove();
                    type = 2; 
            }

            else if(barrier.getY() >= 900 && barrier.isVisible() && barrier.isBroken()) {
                rewardingBarrierIterator.remove();
            }
        }
        

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
        
        // Check collision with simple barriers
        Iterator<SimpleBarrier> simpleBarrierIterator = simpleBarriers.iterator();
        while (simpleBarrierIterator.hasNext()) {
            SimpleBarrier sbarrier = simpleBarrierIterator.next();
            if (sbarrier.isVisible() && sbarrier != null && sbarrier.intersects(barrier.getBounds()) && sbarrier.getCollideable() && barrier != sbarrier) {  
                movement.reflect(barrier, sbarrier);
            }
        }

        // Check collision with reinforced barriers
        Iterator<ReinforcedBarrier> reinforcedBarrierIterator = reinforcedBarriers.iterator();
        while (reinforcedBarrierIterator.hasNext()) {
            ReinforcedBarrier rbarrier = reinforcedBarrierIterator.next();
            if (rbarrier.isVisible() && rbarrier != null && rbarrier.intersects(barrier.getBounds()) && rbarrier.getCollideable() && barrier != rbarrier) {      	
                movement.reflect(barrier, rbarrier);
            }
        }

        // Check collision with explosive barriers
        Iterator<ExplosiveBarrier> explosiveBarrierIterator = explosiveBarriers.iterator();
        while (explosiveBarrierIterator.hasNext()) {
            ExplosiveBarrier ebarrier = explosiveBarrierIterator.next();
            if (ebarrier.isVisible() && ebarrier != null && ebarrier.intersects(barrier.getBounds()) && !ebarrier.isExploded() && ebarrier.getCollideable() && barrier != ebarrier) {            	
                movement.reflect(barrier, ebarrier);
            }
        }


        // Check collision with rewarding barriers
        Iterator<RewardingBarrier> rewardingBarrierIterator = rewardingBarriers.iterator();
        while (rewardingBarrierIterator.hasNext()) {
            RewardingBarrier wbarrier = rewardingBarrierIterator.next();
            if (wbarrier.isVisible() && wbarrier != null && wbarrier.intersects(barrier.getBounds()) && !wbarrier.isBroken() && wbarrier.getCollideable() && barrier != wbarrier) {            	
                movement.reflect(barrier, wbarrier);
            }
        }
    }

}
