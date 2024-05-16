package tr.edu.ku.GameEngine;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import tr.edu.ku.MathBase;
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
import tr.edu.ku.GameArea.Grid;

public class CollisionHandler {

    private FireBall ball;
    private MagicalStaff paddle;
    private Grid grid;
    private ArrayList<Barrier> allBarriers;
    private ArrayList<Barrier> movingBarriers;

    private MovementHandler movement = new MovementHandler(this);
    private ReinforcedBarrier incident_rbarrier; //For safe collision with reinforced barrier

    public CollisionHandler(FireBall fireball, MagicalStaff staff, Grid grid, ArrayList<Barrier> allBarriers, ArrayList<Barrier> movingBarriers){
        this.allBarriers = allBarriers;
        this.movingBarriers = movingBarriers;
        this.ball = fireball;
        this.paddle = staff;
        this.grid = grid;
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

        Point p = MathBase.getGrid(ball); //Current Grid of the ball.
        int x = (int) p.getX();
        int y = (int) p.getY();
        
        try{
            if(grid.getCells()[x][y].hasBarrier()){
                Barrier barrier = grid.getCells()[x][y].getBarrier();

                if (ball.intersects(barrier.getBounds()) && barrier.getCollideable() && !barrier.isFrozen()) {
                       
                    //If collision with Simple Barrier
                    if(barrier instanceof SimpleBarrier){

                        grid.getCells()[x][y].deleteBarrier();   //delete barrier from grid
                        allBarriers.remove(barrier);             //delete from all barriers
                        inc_score = true;

                        if (SpellController.getInstance().is_OFB_Active() == false) {
                            movement.reflect(barrier, ball);
                        }
                    }

                    //collision with Reinforced Barrier
                    else if(barrier instanceof ReinforcedBarrier){

                        if (SpellController.getInstance().is_OFB_Active() == false) {
                            ((ReinforcedBarrier)barrier).hit();
                            movement.reflect(barrier, ball);

                            //For safe collision check
                            barrier.setCollideable(false);
                            incident_rbarrier = (ReinforcedBarrier)barrier;

                            if (((ReinforcedBarrier)barrier).getHitsTaken() >= ((ReinforcedBarrier)barrier).getMaxHits()) {
                                grid.getCells()[x][y].deleteBarrier();   //delete barrier from grid
                                allBarriers.remove(barrier);             //delete from all barriers
                                inc_score = true;
                            }
                        }

                        else {
                            grid.getCells()[x][y].deleteBarrier();   //delete barrier from grid
                            allBarriers.remove(barrier);             //delete from all barriers
                            inc_score = true;
                        }
                    }

                    //collision with explosive barrier
                    else if(barrier instanceof ExplosiveBarrier){

                        ((ExplosiveBarrier)barrier).explode();
                        grid.getCells()[x][y].deleteBarrier();   //delete barrier from grid
                        movingBarriers.add(barrier);             ////add to moving barriers 
                        inc_score = true;
                            
                        if (SpellController.getInstance().is_OFB_Active() == false) {
                            movement.reflect(barrier, ball);
                        }
                    }

                    //collision with rewarding barrier
                    else if(barrier instanceof RewardingBarrier){

                        ((RewardingBarrier)barrier).Break();
                        grid.getCells()[x][y].deleteBarrier();   //delete barrier from grid
                        movingBarriers.add(barrier);              ////add to moving barriers 
                        inc_score = true;
                         
                        if (SpellController.getInstance().is_OFB_Active() == false) {
                            movement.reflect(barrier, ball);
                        }
                    }

                    //If collision with Hollow Barrier
                    else if(barrier instanceof HollowBarrier){
                        grid.getCells()[x][y].deleteBarrier();   //delete barrier from grid
                        allBarriers.remove(barrier);             //delete from all barriers
                        inc_score = true;

                        if (SpellController.getInstance().is_OFB_Active() == false) {
                            movement.reflect(barrier, ball);
                        }
                    }
                }

                else if(barrier.isFrozen() && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) { //if barrier is frozen
                    movement.reflect(barrier, ball);
                }
            }
        }catch (ArrayIndexOutOfBoundsException e) {} 


    // Check collision with moving barriers separetely
    Iterator<Barrier> BarrierIterator = movingBarriers.iterator();
    while (BarrierIterator.hasNext()) {
        Barrier barrier = BarrierIterator.next();
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable()) {  
                
                if(barrier instanceof SimpleBarrier){ //if simple barrier is moving 
                    BarrierIterator.remove();         //remove it from moving barriers
                    allBarriers.remove(barrier);      //remove it from all barriers
                    inc_score = true;

                    if (SpellController.getInstance().is_OFB_Active() == false) {
                        movement.reflect(barrier, ball);
                    }
                }

                else if(barrier instanceof ReinforcedBarrier){

                    if (SpellController.getInstance().is_OFB_Active() == false) {
                        ((ReinforcedBarrier)barrier).hit();
                        movement.reflect(barrier, ball);

                        //For safe collision check
                        barrier.setCollideable(false);
                        incident_rbarrier = (ReinforcedBarrier)barrier;

                        if (((ReinforcedBarrier)barrier).getHitsTaken() >= ((ReinforcedBarrier)barrier).getMaxHits()) {
                            BarrierIterator.remove();
                            allBarriers.remove(barrier);             //delete from all barriers
                            inc_score = true;
                        }
                    }

                    else {
                        BarrierIterator.remove();
                        allBarriers.remove(barrier);             //delete from all barriers
                        inc_score = true;
                    }
                }


                else if(barrier instanceof ExplosiveBarrier){
                    ExplosiveBarrier eBarrier = (ExplosiveBarrier) barrier;

                    if(!eBarrier.isExploded()) {
                        eBarrier.explode();                 //just explode it, remains to stay in game.(it is still moving)
                        inc_score = true;

                        if (SpellController.getInstance().is_OFB_Active() == false) {
                            movement.reflect(barrier, ball);
                        }
                    }
                }
            }

            else if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds()) && barrier.getCollideable() && barrier.isFrozen()) {  
                movement.reflect(barrier, ball);
            }
        }
    return inc_score;
}



    public boolean checkBulletCollisions() {

        boolean inc_score = false; //increment score signal if barrier is destroyed.

        Iterator<Barrier> barrierIterator = allBarriers.iterator();
        while (barrierIterator.hasNext()) {
            Barrier barrier = barrierIterator.next();

            synchronized (SpellController.getInstance().getLock()) {
            if(SpellController.getInstance().is_HEX_Active()) {
                
                Iterator<Bullet> bulletIterator = SpellController.getInstance().getCurrentHEX().getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();
                    if(bullet.intersects(barrier.getBounds()) && bullet != null && !barrier.isFrozen()) {
                             
                        //bullet collision with simple barrier
                        if(barrier instanceof SimpleBarrier) {
                            barrierIterator.remove();   //remove from all barriers
                            bulletIterator.remove();    //remove bullet
                            inc_score = true;

                            if(barrier.isMoving()) {
                                movingBarriers.remove(barrier);
                            }

                            else {
                                grid.deleteFromGrid(barrier);
                            }
                        }

                        //bullet collision with reinforced barrier
                        if(barrier instanceof ReinforcedBarrier) {
                            ReinforcedBarrier rBarrier = (ReinforcedBarrier)barrier;

                            rBarrier.hit();
                            bulletIterator.remove();

                            if (rBarrier.getHitsTaken() >= rBarrier.getMaxHits()) {
                                barrierIterator.remove();

                                if(barrier.isMoving()) {
                                    movingBarriers.remove(barrier);
                                }

                                else {
                                    grid.deleteFromGrid(barrier);
                                }

                                inc_score = true;
                            }
                        }

                        //bullet collision with explosive barrier
                        if(barrier instanceof ExplosiveBarrier) {
                            ExplosiveBarrier eBarrier = (ExplosiveBarrier) barrier;

                            if(!eBarrier.isExploded()) {

                                bulletIterator.remove();  //remove the bullet
                                eBarrier.explode();
                                inc_score = true;

                                if(!eBarrier.isMoving()) {   //barrier not exploded and not moving (stationary explosive barrier)
                                    movingBarriers.add(eBarrier);
                                    grid.deleteFromGrid(eBarrier);                                    
                                }
                            }
                        }

                        //bullet collision with rewarding barrier
                        if(barrier instanceof RewardingBarrier) {
                            RewardingBarrier wBarrier = (RewardingBarrier) barrier;

                            if(!wBarrier.isBroken()) {
                                bulletIterator.remove();  //remove the bullet
                                wBarrier.Break();         //break the barrier
                                inc_score = true;
                                movingBarriers.add(wBarrier);
                                grid.deleteFromGrid(wBarrier);                                    
                            }
                        }

                        //bullet collision with purple barrier
                        if(barrier instanceof HollowBarrier) {
                            barrierIterator.remove();           //remove from all barriers
                            grid.deleteFromGrid(barrier);       //remove from the grid
                            bulletIterator.remove();    //remove bullet
                            inc_score = true;
                        }
                    }

                    else if(bullet.intersects(barrier.getBounds()) && bullet != null && barrier.isFrozen()) {
                        bulletIterator.remove();    //remove bullet
                    }
                }  
            }
        }
    }

    return inc_score;
} 



    public int checkStaffCollisions() {
        
        int type = 0;  //1 for explosive pieces

        Iterator<Barrier> movingIterator = movingBarriers.iterator();
        while (movingIterator.hasNext()) {
            Barrier barrier = movingIterator.next();

            if(barrier instanceof RewardingBarrier){

                RewardingBarrier rBarrier = ((RewardingBarrier)barrier);
                if(paddle.getPolygon().intersects(rBarrier.getHitBox()) && rBarrier.isVisible() && rBarrier.isBroken()) {
                    allBarriers.remove(rBarrier);
                    movingIterator.remove();
                    type = 2; 
                }

                else if(rBarrier.isBroken() && rBarrier.getY() >= 900) {
                    allBarriers.remove(rBarrier);
                    movingIterator.remove();
                }   
            }

            else if(barrier instanceof ExplosiveBarrier) {
                boolean delete_flag = false;

                ExplosiveBarrier eBarrier = ((ExplosiveBarrier)barrier);
                if(eBarrier.isExploded() && eBarrier.isVisible()) {
                    for(Rectangle piece : eBarrier.getHitboxes()) {
                        if(paddle.getPolygon().intersects(piece)) {
                            delete_flag = true;
                            type = 1; 
                        }
                    }

                    if(delete_flag) {
                        allBarriers.remove(eBarrier);
                        movingIterator.remove();
                    }

                    if(barrier.getY() > 950) {
                        allBarriers.remove(eBarrier);
                        movingIterator.remove();
                    }
                }
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


    public void checkBarrierCollisions(Barrier cbarrier) {
        
        // Check collision with simple barriers
        Iterator<Barrier> BarrierIterator = allBarriers.iterator();
        while (BarrierIterator.hasNext()) {
            Barrier barrier = BarrierIterator.next();

            if (barrier.isVisible() && barrier != null && cbarrier.intersects(barrier.getBounds()) && barrier.getCollideable() && barrier != cbarrier) {

                if(barrier instanceof ExplosiveBarrier) {
                    if(!((ExplosiveBarrier)barrier).isExploded()) {
                        movement.reflect(barrier, cbarrier);
                    }
                }

                else if(barrier instanceof RewardingBarrier) {
                    if(!((RewardingBarrier)barrier).isBroken()) {
                        movement.reflect(barrier, cbarrier);
                    }
                }

                else {
                    movement.reflect(barrier, cbarrier);
                }
            }
        }
    }
}
