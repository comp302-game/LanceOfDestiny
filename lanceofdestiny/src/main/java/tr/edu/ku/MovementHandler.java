package tr.edu.ku;

import java.util.ArrayList;

public class MovementHandler {
	private CollisionHandler collision;

	public MovementHandler(CollisionHandler colHandler) {
		collision = colHandler;
	}

	
    public void reflect(SimpleBarrier barrier, FireBall ball) {

		if(barrier.isMoving == false) { //static collision

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

			//calculate boundary slope
			double dx2 = barrier.getWidth()/2 + ball.getSize()/2;
			double dy2 = barrier.getHeight()/2 + ball.getSize()/2;
    		double cornerSlope = Math.abs(dy2/dx2);
			
        	// Determine collision side based on ball's movement direction
        	if (slope > cornerSlope) {
        	    // Collision from top and bottom
        	    ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
				
        	} 
        
        	else if (slope < cornerSlope) {
        	    // Collision from the sides
        	    ball.setSpeedX(ball.getSpeedX() * -1); // Reverse horizontal direction
        	} 

        	else {
        	    // Collision from top or bottom side
        	    ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
        	    ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
        	}

		}


		else if(barrier.isMoving && (barrier.getSpeed()* ball.getSpeedX()) > 0) { //dynamic collision case 1 

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

			//calculate boundary slope
			double dx2 = barrier.getWidth()/2 + ball.getSize()/2;
			double dy2 = barrier.getHeight()/2 + ball.getSize()/2;
    		double cornerSlope = Math.abs(dy2/dx2);
    	
        	// Determine collision side based on ball's movement direction
        	if (slope > cornerSlope) {
        		// Collision from top and bottom
        		ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
        	} 
        
        	else if (slope < cornerSlope) {
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


		else if(barrier.isMoving && (barrier.getSpeed()* ball.getSpeedX()) < 0) { //dynamic collision case 2 
			ball.setSpeedX(ball.getSpeedX()*-1);
			ball.setSpeedY(ball.getSpeedY()*-1);
		}


		else if(barrier.isMoving && ball.getSpeedX() == 0) { //dynamic collision case 3 
			double new_speed = Math.abs(ball.getSpeedY()) / Math.sqrt(2);
			double x_sign = barrier.getSpeed() / barrier.getSpeed();
			double y_sign = -1 * (ball.getSpeedY() / ball.getSpeedY()); 

			ball.setSpeedX(x_sign * new_speed);
			ball.setSpeedY(y_sign * new_speed);
		}

	}
	
	
	public void reflect(ReinforcedBarrier barrier, FireBall ball) {

		if(barrier.isMoving == false) { //static collision

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

			//calculate boundary slope
			double dx2 = barrier.getWidth()/2 + ball.getSize()/2;
			double dy2 = barrier.getHeight()/2 + ball.getSize()/2;
    		double cornerSlope = Math.abs(dy2/dx2);
    	
        	// Determine collision side based on ball's movement direction
        	if (slope > cornerSlope) {
        	    // Collision from top and bottom
        	    ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            
        	} 
        
        	else if (slope < cornerSlope) {
        	// Collision from the sides
           	 	ball.setSpeedX(ball.getSpeedX() * -1); // Reverse horizontal direction
        	} 

        	else {
            // Collision from top or bottom side
        	    ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
        	    ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
        	}
		
		}
		

		else if(barrier.isMoving && (barrier.getSpeed()* ball.getSpeedX()) > 0) { //dynamic collision case 1 

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

			//calculate boundary slope
			double dx2 = barrier.getWidth()/2 + ball.getSize()/2;
			double dy2 = barrier.getHeight()/2 + ball.getSize()/2;
    		double cornerSlope = Math.abs(dy2/dx2);
    	
        	// Determine collision side based on ball's movement direction
        	if (slope > cornerSlope) {
        	    // Collision from top and bottom
        	    ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            
        	} 
        
        	else if (slope < cornerSlope) {
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


		else if(barrier.isMoving && (barrier.getSpeed()* ball.getSpeedX()) < 0) { //dynamic collision case 2 
			ball.setSpeedX(ball.getSpeedX()*-1);
			ball.setSpeedY(ball.getSpeedY()*-1);
		}


		else if(barrier.isMoving && ball.getSpeedX() == 0) { //dynamic collision case 3 
			double new_speed = Math.abs(ball.getSpeedY()) / Math.sqrt(2);
			double x_sign = barrier.getSpeed() / barrier.getSpeed();
			double y_sign = -1 * (ball.getSpeedY() / ball.getSpeedY()); 

			ball.setSpeedX(x_sign * new_speed);
			ball.setSpeedY(y_sign * new_speed);
		}


	}



	public void reflect(ExplosiveBarrier barrier, FireBall ball) {
		
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

		//calculate boundary slope
		double dx2 = barrier.getWidth()/2 + ball.getSize()/2;
		double dy2 = barrier.getHeight()/2 + ball.getSize()/2;
    	double cornerSlope = Math.abs(dy2/dx2);
    	
        // Determine collision side based on ball's movement direction
        if (slope > cornerSlope) {
            // Collision from top and bottom
            ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            
        } 
        
        else if (slope < cornerSlope) {
            // Collision from the sides
            ball.setSpeedX(ball.getSpeedX() * -1); // Reverse horizontal direction
        } 

        else {
            // Collision from top or bottom side
            ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
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
	
	


	public void updateStaff(MagicalStaff staff) {
		 if (KeyboardInputHandler.getLeftPressed() && staff.getX() > staff.getWidth()/2) {
	        staff.setX(staff.getX() - staff.getSpeed());
	        }
	     if (KeyboardInputHandler.getRightPressed() && staff.getX() < GamePanel.WIDTH - staff.getWidth()/2) {
	    	 staff.setX(staff.getX() + staff.getSpeed());
	        }
		if (KeyboardInputHandler.getA_Pressed() && staff.getRotationAngle() < 45) {
	    	 staff.setRotationAngle(staff.getRotationAngle() + staff.getRotationSpeed()); // Adjust rotation 
	        }
		if (KeyboardInputHandler.getD_Pressed() && staff.getRotationAngle() > -45) {
	    	 staff.setRotationAngle(staff.getRotationAngle() - staff.getRotationSpeed()); // Adjust rotation 
	        }
	}
	
	

	
	public int updateFireBall(FireBall ball) {

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

		if (ball.getY() >= GamePanel.HEIGHT) { //Fireball falls below condition
			fireball_status = 1;
			KeyboardInputHandler.setXPressed(false);
		}

		return fireball_status;

	}



	private void bounceFromWall(FireBall ball) {
	
		if (ball.getX() <= 0 || ball.getX() >= GamePanel.WIDTH - ball.getSize()) {
			ball.setSpeedX(ball.getSpeedX()* -1);

		}
		if (ball.getY() <= 0) {
			ball.setSpeedY(ball.getSpeedY()* -1);

		}
	}

	

	
	
	public void updateBarriers(ArrayList<ReinforcedBarrier> reinforcedBarriers, ArrayList<SimpleBarrier> simpleBarriers, ArrayList<ExplosiveBarrier> explosiveBarriers) {

		setBarrierMovement(reinforcedBarriers, simpleBarriers, explosiveBarriers);

		for(SimpleBarrier sBarrier : simpleBarriers) {  //Horizontal back and forth movement for simple dynamic barriers. 
			if(sBarrier.isMoving){
				sBarrier.setX(sBarrier.getX() + sBarrier.getSpeed());
				sBarrier.setDx(sBarrier.get_dx() + sBarrier.getSpeed());
				if(sBarrier.get_dx() >= 32 || sBarrier.get_dx() <= -32) {
					sBarrier.setSpeed(sBarrier.getSpeed()*-1);
				}
			}
		}

		for(ReinforcedBarrier rBarrier : reinforcedBarriers) {  //Horizontal back and forth movement for reinforced dynamic barriers.
			if(rBarrier.isMoving){
				rBarrier.setX(rBarrier.getX() + rBarrier.getSpeed());
				rBarrier.setDx(rBarrier.get_dx() + rBarrier.getSpeed());
				if(rBarrier.get_dx() >= 32 || rBarrier.get_dx() <= -32) {
					rBarrier.setSpeed(rBarrier.getSpeed()*-1);
				}
			}
		}
		
		for(ExplosiveBarrier eBarrier : explosiveBarriers) {  //Update the falling pieces of the explosive barriers. 
			if(eBarrier.isExploded()){
				eBarrier.setY((int) (eBarrier.getY() + eBarrier.getSpeedY()));
			}
		}

	} 



	private void setBarrierMovement(ArrayList<ReinforcedBarrier> reinforcedBarriers, ArrayList<SimpleBarrier> simpleBarriers, ArrayList<ExplosiveBarrier> explosiveBarriers) { //Start moving the barriers if the space allows

		for(SimpleBarrier sBarrier : simpleBarriers) {
			if(sBarrier.isDynamic() && sBarrier.isMoving == false){

				boolean path_check = true;

				for(SimpleBarrier barrier : simpleBarriers) {
					if(sBarrier != barrier && sBarrier.getPath().intersects(barrier.getBounds())){
						path_check = false;
					}
				}

				for(ReinforcedBarrier rbarrier : reinforcedBarriers) {
					if(sBarrier.getPath().intersects(rbarrier.getBounds())){
						path_check = false;
					}
				}

				for(ExplosiveBarrier ebarrier : explosiveBarriers) {
					if(sBarrier.getPath().intersects(ebarrier.getBounds())){
						path_check = false;
					}
				}

				sBarrier.setIsMoving(path_check);

			}
		}


		for(ReinforcedBarrier rBarrier : reinforcedBarriers) {
			if(rBarrier.isDynamic() && rBarrier.isMoving == false){
				
				boolean path_check = true;

				for(ReinforcedBarrier barrier : reinforcedBarriers) {
					if(rBarrier != barrier && rBarrier.getPath().intersects(barrier.getBounds())){
						path_check = false;
					}
				}

				for(SimpleBarrier sbarrier : simpleBarriers) {
					if(rBarrier.getPath().intersects(sbarrier.getBounds())){
						path_check = false;
					}
				}

				
				for(ExplosiveBarrier ebarrier : explosiveBarriers) {
					if(rBarrier.getPath().intersects(ebarrier.getBounds())){
						path_check = false;
					}
				}


				rBarrier.setIsMoving(path_check);
			}
			
		}
	}




}

