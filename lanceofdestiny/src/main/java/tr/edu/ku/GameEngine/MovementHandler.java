package tr.edu.ku.GameEngine;

import java.util.ArrayList;
import java.util.Iterator;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.BarrierStrategy.MoveCircular;
import tr.edu.ku.Domain.Bullet;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.Domain.Spell.SpellController;
import tr.edu.ku.GameArea.Grid;

public class MovementHandler {
	private CollisionHandler collision;

	public MovementHandler(CollisionHandler colHandler) {
		collision = colHandler;
	}

	
    public void reflect(Barrier barrier, FireBall ball) {

		if(barrier.isMoving() == false) { //static collision

			double centerBallx= ball.getX() + (ball.getSize()/ 2);
    		double centerBally= ball.getY() + (ball.getSize()/ 2);
    		double centerBarrier_x= barrier.getX() + (barrier.getWidth() / 2);
    		double centerBarrier_y= barrier.getY() + (barrier.getHeight() / 2);
    	
    		double dx = Math.abs(centerBallx-centerBarrier_x);
    		double dy = Math.abs(centerBally-centerBarrier_y);
    	
    		if(dx == 0) {
    			dx = (float) 0.001;
    		}
   	
    		double slope = dy/dx;

			
        	// Determine collision side based on ball's movement direction
        	if (slope > 0.72) {
        	    // Collision from top and bottom
        	    ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
				
        	} 
        
        	else if (slope < 0.72) {
        	    // Collision from the sides
        	    ball.setSpeedX(ball.getSpeedX() * -1); // Reverse horizontal direction
        	} 

        	else {
        	    // Collision from top or bottom side
        	    ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
        	    ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
        	}

		}


		else{

			if((barrier.getDynamicBehavior().getSpeed()* ball.getSpeedX()) > 0){

				double centerBallx= ball.getX() + (ball.getSize()/ 2);
    			double centerBally= ball.getY() + (ball.getSize()/ 2);
    			double centerBarrier_x= barrier.getX() + (barrier.getWidth() / 2);
    			double centerBarrier_y= barrier.getY() + (barrier.getHeight() / 2);
    	
    			double dx = Math.abs(centerBallx-centerBarrier_x);
    			double dy = Math.abs(centerBally-centerBarrier_y);
    	
    			if(dx == 0) {
    				dx = (float) 0.001;
    			}
			
    			double slope = dy/dx;
    	
        		// Determine collision side based on ball's movement direction
        		if (slope > 0.72) {
        			// Collision from top and bottom
        			ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
        		} 
        
        		else if (slope < 0.72) {
        			// Collision from the sides
       				ball.setSpeedX(ball.getSpeedX() * -1); // Reverse horizontal direction
        		}	 

        		else {
        			// Collision from top or bottom side
        			ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
        			ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
        		}

				double currentSpeed = Math.sqrt(Math.pow(ball.getSpeedX(), 2) + Math.pow(ball.getSpeedY(), 2));
				double ratio = (currentSpeed + 0.045) / currentSpeed; //add 5px/s to the speed
				ball.setSpeedX(ball.getSpeedX()*ratio);
				ball.setSpeedY(ball.getSpeedY()*ratio);
			}

			else if((barrier.getDynamicBehavior().getSpeed()* ball.getSpeedX()) < 0) { //dynamic collision case 2 
				ball.setSpeedX(ball.getSpeedX()*-1);
				ball.setSpeedY(ball.getSpeedY()*-1);
			}

			else if(ball.getSpeedX() == 0) { //dynamic collision case 3 
				double new_speed = Math.abs(ball.getSpeedY()) / Math.sqrt(2);
				double x_sign = barrier.getDynamicBehavior().getSpeed() / barrier.getDynamicBehavior().getSpeed();
				double y_sign = -1 * (ball.getSpeedY() / ball.getSpeedY()); 

				ball.setSpeedX(x_sign * new_speed);
				ball.setSpeedY(y_sign * new_speed);
			}
		}
	}
	

	public void reflect(MagicalStaff staff, FireBall ball) {

		double staffCenterX = staff.getX();
        double staffCenterY = staff.getY();
		double centerBallx= ball.getX() + (ball.getSize()/ 2);
    	double centerBally= ball.getY() + (ball.getSize()/ 2);
        
		// Calculate the normal vector of the top
        double normalX = Math.cos(Math.toRadians(staff.getRotationAngle() + 90));
        double normalY = -Math.sin(Math.toRadians(staff.getRotationAngle() + 90));

        // Calculate the normal vector of the base
        double normalX1 = Math.cos(Math.toRadians(staff.getRotationAngle()));
        double normalY1 = -Math.sin(Math.toRadians(staff.getRotationAngle()));
        
		// Vector from staff center to ball
		double vecX = centerBallx - staffCenterX;
		double vecY = centerBally - staffCenterY;

		//calculate boundary slope
		double dx2 = staff.getWidth()/2 + ball.getSize()/2;
		double dy2 = staff.getHeight()/2 + ball.getSize()/2;
		double limit = Math.abs(dy2/dx2);

		//Calculate the angle between base vector and collision vector
		double dotp = vecX * normalX1 + vecY * normalY1;
		double magnitude1 = Math.sqrt(Math.pow(vecX, 2) + Math.pow(vecY, 2));
		double magnitude2 = Math.sqrt(Math.pow(normalX1, 2) + Math.pow(normalY1, 2));

		double cos_angle = dotp / (magnitude1 * magnitude2);
		double angle = Math.acos(cos_angle);

		//Calculate slope
		double slope = Math.abs(Math.tan(angle));

		if(slope < limit) { //Reflect from sides
       	 	// Calculate the dot product of the ball-to-staff-center vector and the normal vector
        	double dotProduct = ball.getSpeedX() * normalX1 + ball.getSpeedY() * normalY1;
        
        	// Calculate the reflected direction vector
        	double reflectedDirX = ball.getSpeedX() - 2 * dotProduct * normalX1;
        	double reflectedDirY = ball.getSpeedY() - 2 * dotProduct * normalY1;

			// Update the ball's speed components based on the reflected direction
			ball.setSpeedX(reflectedDirX);
			ball.setSpeedY(reflectedDirY);
	
		}

		else if (slope > limit) { //Reflect from top
        	// Calculate the dot product of the ball-to-staff-center vector and the normal vector
        	double dotProduct = ball.getSpeedX() * normalX + ball.getSpeedY() * normalY;
        
        	// Calculate the reflected direction vector
        	double reflectedDirX = ball.getSpeedX() - 2 * dotProduct * normalX;
        	double reflectedDirY = ball.getSpeedY() - 2 * dotProduct * normalY;

			// Update the ball's speed components based on the reflected direction
			ball.setSpeedX(reflectedDirX);
			ball.setSpeedY(reflectedDirY);

		}

		else{
			//Collision from corner in case it happens
            ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
        }
	}
	
	
	public void reflect(Barrier barrier1, Barrier barrier2) {

		if(barrier1.isMoving()){
			barrier1.getDynamicBehavior().setSpeed(barrier1.getDynamicBehavior().getSpeed() * -1);}
		
		if(barrier2.isMoving()){
			barrier2.getDynamicBehavior().setSpeed(barrier2.getDynamicBehavior().getSpeed() * -1);}
	}


	public void updateStaff(MagicalStaff staff) {
		
		if (KeyboardInputHandler.getLeftPressed() && staff.getX() > staff.getWidth()/2) {
	        staff.setX(staff.getX() - staff.getSpeed());
	        }

	    else if (KeyboardInputHandler.getRightPressed() && staff.getX() < Constants.GAMEPANEL_WIDTH- staff.getWidth()/2) {
	    	 staff.setX(staff.getX() + staff.getSpeed());
	        }

		if (KeyboardInputHandler.getD_Pressed() && staff.getRotationAngle() > -45) {
	    	 staff.setRotationAngle(staff.getRotationAngle() - staff.getRotationSpeed()); // Adjust rotation 
	        }

		else if (KeyboardInputHandler.getA_Pressed() && staff.getRotationAngle() < 45) {
	    	 staff.setRotationAngle(staff.getRotationAngle() + staff.getRotationSpeed()); // Adjust rotation 
	        }

		else {
			if (staff.getRotationAngle() < 0) {
				staff.setRotationAngle(staff.getRotationAngle() + Constants.STAFF_ROTATION_RETURN_SPEED);
				if(staff.getRotationAngle() > -0.4) {
					staff.setRotationAngle(0);
				}
			}

			else if (staff.getRotationAngle() > 0) {
				staff.setRotationAngle(staff.getRotationAngle() - Constants.STAFF_ROTATION_RETURN_SPEED);
				if(staff.getRotationAngle() < 0.4) {
					staff.setRotationAngle(0);
				}
			}
		}
	}
	
	
	public int updateFireBall(FireBall ball) {
		// Requires: non-null ball
		// Modifies ball
		// Effects: If ball hits and destroys a barrier returns 2, if the ball falls below Constants.GAMEPANEL_HEIGHT the method returns 1 and sets KeyboardInputHandler.X_Pressed to false, else it returns 0

		int fireball_status = 0; //0 for no incident, 1 for fall below, 2 for successfuly acquiring points
		
		// Calculate the maximum distance the ball can move in one frame
		double maxMoveDistance = Math.sqrt(ball.getSpeedX() * ball.getSpeedX() + ball.getSpeedY() * ball.getSpeedY());

		// Calculate the fraction based on the maximum distance and 0.25 pixel threshold (At most 0.25 pixel steps are taken before detecting collision -- For smooth collision detection)
		double steps = 4*Math.ceil(maxMoveDistance);
		double fraction = 1.0 / steps;

		for (int i=0; i< steps; i++) {
			ball.setX(ball.getX() + ball.getSpeedX()*fraction);
			ball.setY(ball.getY() + ball.getSpeedY()*fraction);
			if(collision.checkAnyCollision()){
				fireball_status = 2;
			} 
			bounceFromWall(ball);
		}

		if (ball.getY() >= Constants.GAMEPANEL_HEIGHT) { //Fireball falls below condition
			fireball_status = 1;
			KeyboardInputHandler.setXPressed(false);
		}

		return fireball_status;

	}


	private void bounceFromWall(FireBall ball) {
	
		if (ball.getX() <= 0 || ball.getX() >= Constants.GAMEPANEL_WIDTH - ball.getSize()) {
			ball.setSpeedX(ball.getSpeedX()* -1);
			if (ball.getX() < 0){
				ball.setX(-ball.getX());
			}

			if (ball.getX() > Constants.GAMEPANEL_WIDTH - ball.getSize()){
				ball.setX(Constants.GAMEPANEL_WIDTH - ball.getSize() - (ball.getX() - (Constants.GAMEPANEL_WIDTH - ball.getSize())));
			}

		}
		if (ball.getY() <= 0) {
			ball.setSpeedY(ball.getSpeedY()* -1);
			if (ball.getY() < 0){
				ball.setY(-ball.getY());
			}
		}
	}

	
	public void updateBarriers(ArrayList<Barrier> movingBarriers, ArrayList<Barrier> allBarriers, Grid grid) {

		setBarrierMovement(movingBarriers, allBarriers, grid);

		Iterator<Barrier> iterator = movingBarriers.iterator();
			while (iterator.hasNext()) {
    			Barrier barrier = iterator.next();
				barrier.MoveBarrier();
				
				if(barrier instanceof ExplosiveBarrier) {
                    if(!((ExplosiveBarrier)barrier).isExploded()) {
                        collision.checkBarrierCollisions(barrier);
                    }
                }

                else if(barrier instanceof RewardingBarrier) {
                    if(!((RewardingBarrier)barrier).isBroken()) {
                        collision.checkBarrierCollisions(barrier);
                    }
                }

                else {
                    collision.checkBarrierCollisions(barrier);
                }
			}	
		} 




	public void updateBullets() {

		if(SpellController.getInstance().getCurrentHEX().getBullets() != null && SpellController.getInstance().is_HEX_Active()) {
			synchronized (SpellController.getInstance().getLock()) {
			Iterator<Bullet> bulletIterator = SpellController.getInstance().getCurrentHEX().getBullets().iterator();
            	while (bulletIterator.hasNext()) {
                	Bullet bullet = bulletIterator.next();
                	bullet.setX(bullet.getX() + bullet.getSpeedX());
					bullet.setY(bullet.getY() + bullet.getSpeedY());
				
					if((bullet.getX()> 1600 || bullet.getX()< 0 || bullet.getY()> 900 || bullet.getX()< 0)) {
						bulletIterator.remove();
					}
            	}
			}
		}
		}



	private void setBarrierMovement(ArrayList<Barrier> movingBarriers, ArrayList<Barrier> allBarriers, Grid grid) {
    	// Iterate over all barriers to determine which ones should start moving
    	for (Barrier barrier : allBarriers) {
        	// Check if the barrier is dynamic and not already moving
        	if (barrier.isDynamic() && !barrier.isMoving()) {
            	boolean pathCheck = true;
            
            	// Check if the barrier's path intersects with any other barrier
            	for (Barrier otherBarrier : allBarriers) {
                	if (barrier != otherBarrier && barrier.getPath().intersects(otherBarrier.getBounds())) {
                    	pathCheck = false;
                    	break; // Exit the inner loop early since we found an intersection
                	}
            	}

            	// If the path is clear, set the barrier to moving
            	if (pathCheck) {
                	// Additional logic for explosive barriers
                	if (barrier instanceof ExplosiveBarrier) {
                    	ExplosiveBarrier explosiveBarrier = (ExplosiveBarrier) barrier;
                    	if (!explosiveBarrier.isExploded()) {
                			barrier.setIsMoving(true);
                			grid.deleteFromGrid(barrier); // Remove barrier from the grid
                			movingBarriers.add(barrier);  // Add barrier to the moving list
                        	MoveCircular dynamicBehavior = (MoveCircular) explosiveBarrier.getDynamicBehavior();
                        	dynamicBehavior.setOriginX((int) barrier.getX() + 16);
                        	dynamicBehavior.setOriginY((int) barrier.getY() + 10 - Constants.BARRIER_CIRCULAR_RADIUS);
                    	}
                	}

					else if (barrier instanceof SimpleBarrier || barrier instanceof ReinforcedBarrier) {	
                		barrier.setIsMoving(true);
                		grid.deleteFromGrid(barrier); // Remove barrier from the grid
                		movingBarriers.add(barrier);  // Add barrier to the moving list
					}
            	}
        	}
    	}
	}
}
